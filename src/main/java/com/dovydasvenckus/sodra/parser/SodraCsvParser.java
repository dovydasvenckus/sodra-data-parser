package com.dovydasvenckus.sodra.parser;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.IntStream;

class SodraCsvParser {

    private static final Charset sodraFileEncoding = Charset.forName("ISO-8859-13");
    private static final CSVFormat csvFormat = CSVFormat.newFormat(';');

    private static final String[] headerRecord = {
            "", "", "(V1)*", "(M1)**", "(K1)**", "(K1)**", "(S1)**",
            "(V3)*", "(M3)**", "(K3)**", "(K3)**", "(S3)**",
            "(V2)*", "(M2)**", "(K2)**", "(K2)**", "(S2)**", "*"
    };

    private final CSVParser parser;

    SodraCsvParser(Path filePath) throws IOException {
        parser = csvFormat.parse(Files.newBufferedReader(filePath, sodraFileEncoding));
    }

    SodraFileHeader readHeader() {
        SodraFileHeader sodraFileHeader = new SodraFileHeader();

        for (CSVRecord record : parser) {
            sodraFileHeader.appendHeaderRecord(record);

            if (allMatch(record)) {
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

    private boolean allMatch(CSVRecord record) {
        if (record.size() != headerRecord.length) {
            return false;
        }

        return IntStream.range(0, 18).allMatch(i -> headerRecord[i].equals(record.get(i)));
    }
}
