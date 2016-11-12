package model;

import org.w3c.dom.Attr;

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
    private final Map<String, String> config;
    private final String OUTCOME_ATTRIBUTE_KEY = "outcome";
    private final String POSITIVE_OUTCOME_KEY = "positiveoutcome";
    private final String NEGATIVE_OUTCOME_KEY = "negativeoutcome";
    private final String DELIMETER_KEY = "delimeter";
    private final String FILE_PATH_KEY = "filepath";
    private final String IDENTIFIER_KEY = "identifier";
    private final String FILE_PATH;

    public Model(Map<String, String> config) {
        this.config = config;
        ClassLoader classLoader = getClass().getClassLoader();
        FILE_PATH = classLoader.getResource(config.get(FILE_PATH_KEY)).getPath();
    }

    public String getPositive() {
        return config.get(POSITIVE_OUTCOME_KEY);
    }

    public String getNegative() {
        return config.get(NEGATIVE_OUTCOME_KEY);
    }

    public Table getTable() {
        return createNewTable();
    }

    private Table createNewTable() {
        final int FIRST_LINE = 1;
        Stream<String> rowStream = readFile();
        AttributeList allAttributes = new AttributeList();
        AttributeList attributesWithoutIdAndOutcome = new AttributeList();
        allAttributes.addAll(Arrays.asList(rowStream.findFirst().get().split(config.get(DELIMETER_KEY))));

        attributesWithoutIdAndOutcome = allAttributes
                                            .subListWithout(config.get(IDENTIFIER_KEY))
                                            .subListWithout(config.get(OUTCOME_ATTRIBUTE_KEY));


        Table table = new Table(attributesWithoutIdAndOutcome,
                                config.get(OUTCOME_ATTRIBUTE_KEY),
                                config.get(POSITIVE_OUTCOME_KEY),
                                config.get(NEGATIVE_OUTCOME_KEY));

        table.addAll(readFile().skip(FIRST_LINE).map(row -> createNewRow(row, allAttributes)).collect(Collectors.toList()));


        return table;
    }

    private Row createNewRow(String row, List<String> attributes) {
        List<String> rowValues = Arrays.asList(row.split(config.get(DELIMETER_KEY)));
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
