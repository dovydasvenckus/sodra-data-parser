package com.dovydasvenckus.sodra.parser;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class SodraParser {
    private static final Charset sodraFileEncoding = Charset.forName("ISO-8859-13");
    private static final CSVFormat csvFormat = CSVFormat.newFormat(';');

    public static void main(String[] args) throws IOException {
        Arrays.stream(args).forEach(System.out::println);

        List<CSVRecord> records =csvFormat.parse(Files.newBufferedReader(Paths.get(args[0]), sodraFileEncoding)).getRecords();
        records.stream().limit(100).forEach(System.out::println);
    }
}
