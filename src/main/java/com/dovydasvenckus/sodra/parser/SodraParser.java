package com.dovydasvenckus.sodra.parser;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class SodraParser {

    public static void main(String[] args) throws IOException {
        Arrays.stream(args).forEach(System.out::println);

        Files.lines(Paths.get(args[0]), Charset.forName("ISO-8859-1"))
                .limit(10)
                .forEach(System.out::println);
    }
}
