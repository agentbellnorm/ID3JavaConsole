package com.company.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by Morgan on 2016-11-06.
 */
public class GolfModel implements Model{

    private List<String> attributes;
    private List<Row> table;
    private final String SUCCESS_KEY = "buys_computer";
    private final String SUCCESS_VALUE = "yes";
    private final String FILE_PATH;

    public GolfModel() {
        attributes = new ArrayList<>();
        table = new ArrayList<>();

        ClassLoader classLoader = getClass().getClassLoader();
        FILE_PATH = classLoader.getResource("model/golf/data.txt").getPath();
        initiate();
    }

    private void initiate() {
        try (Stream<String> stream = Files.lines(Paths.get(FILE_PATH))) {
            stream.forEach(row -> buildRow(row));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Row> getTable() {
        return table;
    }

    public List<String> getAttributes() {
        return attributes;
    }

    public boolean isPositive(Row row) {
        String outcome = row.getValueByName(SUCCESS_KEY);
        return SUCCESS_VALUE.equals(outcome);
    }

    private void buildRow(String row) {
        List<String> rowValues = Arrays.asList(row.split(","));

        if (attributes.size() == 0) {
            attributes = rowValues;
        }
        else {
            for(int i = 0; i < row.length(); i++) {
                table.add(new Row(attributes.get(i), rowValues.get(i)));
            }
        }
    }
}
