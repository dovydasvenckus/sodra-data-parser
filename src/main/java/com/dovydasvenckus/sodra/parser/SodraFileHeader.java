package com.dovydasvenckus.sodra.parser;

import org.apache.commons.csv.CSVRecord;

import java.util.ArrayList;
import java.util.Collection;

class SodraFileHeader {
    private final Collection<CSVRecord> headerRecords = new ArrayList<>();

    void appendHeaderRecord(CSVRecord record) {
        headerRecords.add(record);
    }

}