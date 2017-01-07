package agentbellnorm.model;


import agentbellnorm.config.Config;

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
    private final Config config;
    private final String FILE_PATH;

    public Model(Config config) {
        this.config = config;
        ClassLoader classLoader = getClass().getClassLoader();
        FILE_PATH = classLoader.getResource(config.getDataFileName()).getPath();
    }

    public String getPositive() {
        return config.getPositiveOutcome();
    }

    public String getNegative() {
        return config.getNegativeOutcome();
    }

    public Table getTable() {
        return createNewTable();
    }

    private Table createNewTable() { //TODO: This method is nasty.
        final int FIRST_LINE = 1;
        Stream<String> rowStream = readFile();
        AttributeList allAttributes = new AttributeList();
        AttributeList attributesWithoutIdAndOutcome;
        allAttributes.addAll(Arrays.asList(rowStream.findFirst().get().split(config.getDelimeter())));

        attributesWithoutIdAndOutcome = allAttributes
                                            .subListWithout(config.getIdentifier())
                                            .subListWithout(config.getOutcomeAttribute());


        Table table = new Table(attributesWithoutIdAndOutcome,
                                config.getOutcomeAttribute(),
                                config.getPositiveOutcome(),
                                config.getNegativeOutcome());

        table.addAll(readFile()
                .skip(FIRST_LINE)
                .map(row -> createNewRow(row, allAttributes))
                .collect(Collectors.toList()));


        return table;
    }

    private Row createNewRow(String row, List<String> attributes) {
        List<String> rowValues = Arrays.asList(row.split(config.getDelimeter()));
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
            throw new RuntimeException("Could not read file");
        }
    }
}
