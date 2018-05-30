package com.dovydasvenckus.sodra.parser;

import java.io.IOException;
import java.nio.file.Paths;

public class SodraParser {

    public static void main(String[] args) throws IOException {
        SodraCsvParser sodraCsvParser = new SodraCsvParser(Paths.get(args[0]), new IsLastRecordOfHeader());
        sodraCsvParser.readHeader();
    }
}