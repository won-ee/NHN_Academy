package org.example.builder;

import org.example.parser.Parser;

import java.nio.charset.StandardCharsets;

public class RequestBuilder {
    public static String build(Parser parser,String host,String path){
        StringBuilder request = new StringBuilder();
        request.append(parser.getMethod()).append(" ").append(path).append(" HTTP/1.1\r\n");
        request.append("Host: ").append(host).append("\r\n");
        request.append("User-Agent: scurl/1.0\r\n");
        request.append("Accept: */*\r\n");

        for (String header : parser.getHeaders()){
            request.append(header).append("\r\n");
        }

        String body = parser.getBody();
        if (body != null){
            request.append("Content-Length: ").append(body.getBytes(StandardCharsets.UTF_8).length).append("\r\n");
        }
        request.append("Connection: close\r\n");
        request.append("\r\n");

        if (body != null) {
            request.append(body);
        }
        return request.toString();
    }
}
