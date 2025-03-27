package org.example;

import org.example.client.Client;
import org.example.tokenizer.Tokenizer;
import org.example.util.OptionPrinter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        br.close();
        String[] parsedInput = Tokenizer.tokenize(input);

        if (parsedInput.length > 0 && parsedInput[0].equals("[option]")) {
            OptionPrinter.print();
            return;
        }

        new Thread(new Client(parsedInput)).start();
    }
}
