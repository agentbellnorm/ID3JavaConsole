package algorithm;

import model.AttributeList;
import model.Model;
import model.Table;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Morgan on 2016-11-06.
 */
public class Id3Performer {

    private Model model;

    public Id3Performer(Model model) {
        this.model = model;
    }

    public Id3Result perform(){
        Table table = model.getTable();
        return new Id3Result(id3(table, table.getAttributes(), "root"));
    }

    private Node id3(Table table, AttributeList attributes, String branchLabel) {

        if (table.allPositive()) {
            return new Node(model.getPositive(), branchLabel);
        }

        if (table.allNegative()) {
            return new Node(model.getNegative(), branchLabel);
        }

        if (attributes.isEmpty()) {
            return new Node(table.mostPositiveOrNegative(), branchLabel);
        }

        GainTuple attributeWithMaxGain = maxGainAttribute(table, attributes);

        Node root = new Node(attributeWithMaxGain, branchLabel);

        for (String value : possibleValues(table, attributeWithMaxGain.attribute)) {
            Table filteredTable = table.filteredSubTable(row -> value.equals(row.getValueByAttribute(attributeWithMaxGain.attribute)));

            if (filteredTable.isEmpty()) {
                root.getChildren().add(new Node(table.mostPositiveOrNegative(), value));
            }
            else {
                root.getChildren().add(id3(filteredTable, attributes.subListWithout(root.getAttribute()), value));
            }
        }
        return root;
    }

    private GainTuple maxGainAttribute(Table table, AttributeList attributes) {
        return attributes.stream()
                .map(attribute -> gain(table, attribute))
                .sorted()
                .findFirst()
                .get();
    }

    private List<String> possibleValues(Table table, String attr){
        return table.stream().map(row -> row.getValueByAttribute(attr)).distinct().collect(Collectors.toList());
    }
}
