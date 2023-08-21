package com.leemccormick.languageapp;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class SayHelloTest {
    @Test
    public void testSayHello() throws IOException {
        SayHello.main(new String[]{"en"});
        SayHello.main(new String[]{"es"});
        SayHello.main(new String[]{"th"});
        SayHello.main(new String[]{"cn"});
    }

    @Test
    public void testSayHelloInEnglish() throws IOException {
        SayHello.main(new String[]{"en"});
    }

    @Test
    public void testSayHelloInSpanish() throws IOException {
        SayHello.main(new String[]{"es"});
    }

    @Test
    public void testSayHelloInChinese() throws IOException {
        SayHello.main(new String[]{"cn"});
    }

    @Test
    public void testSayHelloInThai() throws IOException {
        SayHello.main(new String[]{"th"});
    }
}