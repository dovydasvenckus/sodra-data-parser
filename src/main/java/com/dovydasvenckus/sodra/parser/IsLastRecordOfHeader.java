package com.dovydasvenckus.sodra.parser;

import org.apache.commons.csv.CSVRecord;

import java.util.function.Predicate;
import java.util.stream.IntStream;

public class IsLastRecordOfHeader implements Predicate<CSVRecord> {

    private static final String[] headerRecord = {
            "", "", "(V1)*", "(M1)**", "(K1)**", "(K1)**", "(S1)**",
            "(V3)*", "(M3)**", "(K3)**", "(K3)**", "(S3)**",
            "(V2)*", "(M2)**", "(K2)**", "(K2)**", "(S2)**", "*"
    };

    @Override
    public boolean test(CSVRecord record) {
        return IntStream.range(0, 18).allMatch(i -> headerRecord[i].equals(record.get(i)));
    }
}
