package com.dovydasvenckus.sodra.parser;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Predicate;

class SodraCsvParser {

    private static final Charset sodraFileEncoding = Charset.forName("ISO-8859-13");
    private static final CSVFormat csvFormat = CSVFormat.newFormat(';');

    private final CSVParser parser;

    private final Predicate<CSVRecord> isLastRecordOfHeader;

    SodraCsvParser(Path filePath, Predicate<CSVRecord> isLastRecordOfHeader) throws IOException {
        parser = csvFormat.parse(Files.newBufferedReader(filePath, sodraFileEncoding));
        this.isLastRecordOfHeader = isLastRecordOfHeader;
    }

    SodraFileHeader readHeader() {
        SodraFileHeader sodraFileHeader = new SodraFileHeader();

        for (CSVRecord record : parser) {
            sodraFileHeader.appendHeaderRecord(record);

            if (isLastRecordOfHeader.test(record)) {
                return sodraFileHeader;
            }
        }

        throw new IllegalStateException("Header not found");
    }

    Optional<CSVRecord> getNextRecord() {
        if (parser.iterator().hasNext()) {
            return Optional.of(parser.iterator().next());
        }
        return Optional.empty();
    }

}
