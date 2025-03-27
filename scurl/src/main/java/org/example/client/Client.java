package org.example.client;

import org.example.builder.RequestBuilder;
import org.example.builder.ResponseBuilder;
import org.example.parser.Parser;

import java.io.*;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Client implements Runnable{
    private final Parser parser;

    public Client(String[] parsedInput){
        this.parser = new Parser(parsedInput);
    }

    @Override
    public void run() {
        String currentUrl = parser.getUrl();
        while (true){
            try {
                URL parsedUrl = new URL(currentUrl);
                String host = parsedUrl.getHost();
                String path = parsedUrl.getPath();

                if (path.isEmpty()){
                    path = "/";
                }

                int port = parsedUrl.getPort();

                if (port == -1){
                    port = 80;
                }

                String request = RequestBuilder.build(parser,host,path);
                System.out.println(request);
                try (Socket socket = new Socket(host,port);
                     BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
                     BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));){
                    writer.write(request);
                    writer.flush();

                    String line;
                    boolean isBody = false;
                    ResponseBuilder responseBuilder = new ResponseBuilder();
                    while ((line = reader.readLine()) != null){
                        if (line.isEmpty()){
                            isBody = true;
                            continue;
                        }

                        if (!isBody) {
                            responseBuilder.headerBuilder(line);
                        } else {
                            responseBuilder.bodyBuilder(line);
                        }
                    }

                    if (parser.isVerbose()) {
                        for (String header : responseBuilder.getHeader().split("\r\n")) {
                            System.out.println("> " + header);
                        }
                    }

                    System.out.println(responseBuilder.getBody());


                    if (parser.isRedirect() && redirect(responseBuilder.getStatusCode())) {
                        String location = responseBuilder.getLocation();
                        if (location != null) {
                            if (!location.startsWith("http")) {
                                location = parsedUrl.getProtocol() + "://" + host + location;
                            }
                            currentUrl = location;
                            continue;
                        } else {
                            break;
                        }
                    }

                    break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }

    }
    private boolean redirect(int statusCode) {
        return statusCode == 301 || statusCode == 302 || statusCode == 307 || statusCode == 308;
    }
}

