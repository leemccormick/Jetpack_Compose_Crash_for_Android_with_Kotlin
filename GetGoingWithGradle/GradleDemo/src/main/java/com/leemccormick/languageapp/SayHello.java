package com.leemccormick.languageapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class SayHello {
    public static void main(String[] args) throws IOException {
        String language = args[0];
        InputStream resourceStream = SayHello.class.getClassLoader
                ().getResourceAsStream(language + ".txt");
        assert resourceStream != null;
        BufferedReader bufferedInputStream = new BufferedReader(new InputStreamReader
                (resourceStream, StandardCharsets.UTF_8));

        StringBuilder content = new StringBuilder();
        String line;

        while ((line = bufferedInputStream.readLine()) != null) {
            content.append(line);
            content.append(System.lineSeparator());
        }

        System.out.println(content.toString());
    }
}