package org.example.parser;

import java.util.ArrayList;
import java.util.List;


public class Parser {
    private MethodParser method = MethodParser.GET;
    private String url;
    private final List<String> headers = new ArrayList<>();
    private String body = null;
    private boolean isVerbose = false;
    private boolean isRedirect = false;


    public Parser(String[] parsedInput) {
        parse(parsedInput);
    }

    private void parse(String[] parsedInput){
        for (int i=0; i<parsedInput.length; i++){
            switch (parsedInput[i]){
                case "-X":
                    if (i + 1 <parsedInput.length){
                        method = MethodParser.valueOf(parsedInput[++i].toUpperCase());
                    }
                    break;
                case "-H":
                    if (i + 1 <parsedInput.length){
                        String rawHeader = parsedInput[++i];
                        headers.add(rawHeader.replaceAll("^\"|\"$", ""));                    }
                    break;
                case "-d":
                    if (i + 1 <parsedInput.length){
                        body = parsedInput[++i];
                        method = MethodParser.POST;
                    }
                    break;
                case "-v":
                    isVerbose = true;
                    break;
                case "-L":
                    isRedirect = true;
                    break;
                case "-F":break;
                default:
                    if (!parsedInput[i].startsWith("-")){
                        url = parsedInput[i];
                    }
                    break;
            }
        }
    }
    public MethodParser getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public List<String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    public boolean isVerbose() {
        return isVerbose;
    }

    public boolean isRedirect() {
        return isRedirect;
    }

}