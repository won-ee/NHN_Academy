package org.example.builder;

public class ResponseBuilder {
    private final StringBuilder headerBuilder;
    private final StringBuilder bodyBuilder;
    private int statusCode;
    private String location;
    private int contentLength;

    public ResponseBuilder() {
        headerBuilder = new StringBuilder();
        bodyBuilder = new StringBuilder();
        statusCode = 0;
        location = null;
        contentLength = 0;
    }


    public void headerBuilder(String line){
        if (statusCode == 0 && line.startsWith("HTTP")) {
            statusCode = Integer.parseInt(line.split(" ")[1]);
        }
        if (line.toLowerCase().startsWith("location:")) {
            location = line.substring(9).trim();
        }

        headerBuilder.append("< ").append(line).append("\n");
    }

    public void bodyBuilder(String line){
        bodyBuilder.append(line).append("\n");
    }

    public String getHeader(){
        return headerBuilder.toString();
    }

    public String getBody(){
        return bodyBuilder.toString();
    }
    public int getStatusCode(){
        return statusCode;
    }
    public String getLocation(){
        return location;
    }
}
