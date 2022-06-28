package com.rebecacorp.aplicacaokafkacsv.util;


import java.nio.file.Files;
import java.nio.file.Paths;

import com.opencsv.CSVWriter;

import java.io.IOException;
import java.io.Writer;

public class WriterGenerator {


    public static CSVWriter generatewriter() throws IOException{

        Writer writer = Files.newBufferedWriter(Paths.get("pessoas.csv"));
        CSVWriter csvWriter = new CSVWriter(writer);
        return csvWriter;

    }
}
