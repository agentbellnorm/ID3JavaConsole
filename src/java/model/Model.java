package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Morgan on 2016-11-06.
 */
public class Model {
    private final String OUTCOME_KEY;
    private final String POSITIVE_VALUE;
    private final String NEGATIVE_VALUE;
    private final String DELIMETER = ",";
    private final String FILE_PATH;

    public Model(String path, String outcomeKey, String positiveValue, String negativeValue) {
        ClassLoader classLoader = getClass().getClassLoader();

        FILE_PATH = classLoader.getResource(path).getPath();
        OUTCOME_KEY = outcomeKey;
        POSITIVE_VALUE = positiveValue;
        NEGATIVE_VALUE = negativeValue;
    }

    public String getPositive() {
        return POSITIVE_VALUE;
    }

    public String getNegative() {
        return NEGATIVE_VALUE;
    }

    public Table getTable() {
        return createNewTable();
    }

    private Table createNewTable() {
        final int FIRST_LINE = 1;
        Stream<String> rowStream = readFile();
        AttributeList attributes = new AttributeList();

        attributes.addAll(Arrays.asList(rowStream.findFirst().get().split(DELIMETER)));

        Table table = new Table(attributes, OUTCOME_KEY, POSITIVE_VALUE, NEGATIVE_VALUE);

        table.addAll(readFile().skip(FIRST_LINE).map(row -> createNewRow(row, attributes)).collect(Collectors.toList()));

        return table;
    }

    private Row createNewRow(String row, List<String> attributes) {
        List<String> rowValues = Arrays.asList(row.split(DELIMETER));
        Map<String, String> rowMap = new HashMap<>();
        for(int i = 0; i < rowValues.size(); i++) {
            rowMap.put(attributes.get(i), rowValues.get(i));
        }
        return new Row(rowMap);
    }

    private Stream<String> readFile() {
        try {
            return Files.lines(Paths.get(FILE_PATH));
        }
        catch (IOException e) {
            throw new RuntimeException("Could not read data from file");
        }
    }
}
