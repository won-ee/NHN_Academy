package org.example.tokenizer;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
    public static String[] tokenize(String input){
        List<String> tokens = new ArrayList<>();
        boolean flag = false;
        StringBuilder sb = new StringBuilder();

        for (int i=0; i<input.length(); i++){
            char cha = input.charAt(i);
            if (cha =='"'){
                flag = !flag;
                sb.append(cha);
            }else if(Character.isWhitespace(cha) && !flag){
                if (!sb.isEmpty()){
                    tokens.add(sb.toString());
                    sb.setLength(0);
                }
            }else {
                sb.append(cha);
            }
        }
        if (!sb.isEmpty()){
            tokens.add(sb.toString());
        }
        return tokens.toArray(new String[0]);
    }
}