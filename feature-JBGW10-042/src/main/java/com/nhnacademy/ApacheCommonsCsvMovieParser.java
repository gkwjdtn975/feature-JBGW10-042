package com.nhnacademy;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.Arrays;

public class ApacheCommonsCsvMovieParser implements MovieParser {

    @Override
    public List<Movie> parse() throws IOException {
        List<Movie> movieList = new ArrayList<>();

        try (InputStream is = getMovieFileAsStream();
             InputStreamReader reader = new InputStreamReader(is);
             CSVParser csvParser = CSVParser.parse(reader, CSVFormat.DEFAULT)) {

            List<CSVRecord> records = csvParser.getRecords();

            for (int i = 1; i < records.size(); i++) {
                CSVRecord record = records.get(i);
                long movieId = Long.parseLong(record.get(0));
                String title = record.get(1);
                Set<String> genres = Arrays.stream(record.get(2).split("\\|"))
                        .collect(Collectors.toSet());

                movieList.add(new Movie(movieId, title, genres));
            }
        }

        return movieList;
    }
}