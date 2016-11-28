package algorithm;

import model.AttributeList;
import model.Model;
import model.Table;

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

        for (String value : table.possibleValues(attributeWithMaxGain.attribute)) {
            Table filteredTable = table.filteredSubTable(attributeWithMaxGain.attribute, value);

            if (filteredTable.isEmpty()) {
                root.addChild(new Node(table.mostPositiveOrNegative(), value));
            }
            else {
                root.addChild(id3(filteredTable, attributes.subListWithout(root.getAttribute()), value));
            }
        }
        return root;
    }

    private GainTuple maxGainAttribute(Table table, AttributeList attributes) {
        return attributes.stream()
                .map(attribute -> Id3Calculations.gainByAttribute(table, attribute))
                .sorted()
                .findFirst()
                .get();
    }
}
