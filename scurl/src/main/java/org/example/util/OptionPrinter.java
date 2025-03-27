package org.example.util;

public class OptionPrinter {
    public static void print(){
        System.out.println("Usage: scurl [option] url");
        System.out.println("Options:");
        System.out.println("-v                  verbose, 요청, 응답 헤더를 출력한다.");
        System.out.println("-H <line>           임의의 헤더를 서버로 전송한다.");
        System.out.println("-d <data>           POST, PUT 등에 데이터를 전송한다.");
        System.out.println("-X <command>        사용할 method를 지정한다. 지정되지 않으면 기본값은 GET");
        System.out.println("-L                  서버의 응답이 30x 계열이면 다음 응답을 따라 간다.");
        System.out.println("-F <name=content>   multipart/form-data를 구성하여 전송한다. content 부분에 @filename을 사용할 수 있다.");
    }
}
