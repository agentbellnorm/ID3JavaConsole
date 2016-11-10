package algorithm;

import model.AttributeList;
import model.Model;
import model.Table;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Morgan on 2016-11-06.
 */
public class ID3Performer {

    private Model model;

    public ID3Performer(Model model) {
        this.model = model;
    }

    public ID3Result perform(){
        Table table = model.getTable();
        return new ID3Result(id3(table, table.getAttributes(), "root"));
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

        /* for (String value : possibleValues(table, attributeWithMaxGain.attribute)) {
            Table filteredTable = table.subTable(attributesrow -> value.equals(row.getValueByAttribute(attributeWithMaxGain.attribute)))
                    .collect(Collectors.toCollection());
        }*/

        return new Node("", "");
    }

    private GainTuple maxGainAttribute(Table table, AttributeList attributes) {
        return attributes.stream()
                .map(attribute -> gain(table, attribute))
                .sorted()
                .findFirst()
                .get();
    }


    /* private double Prob(List<Model> set, Func<Model, Boolean> predicate)
    {
        throw new NotImplementedException();
    }

    private double Entropy(List<Model> set, Func<Model,Boolean> predicate) {
        throw new NotImplementedException();
    } */

    private double e_vlad(double prob)
    {
        throw new NotImplementedException();
    }

    private double e_internet(double prob)
    {
        throw new NotImplementedException();
    }



    private GainTuple gain(Table table, String attr) {
        throw new NotImplementedException();
    }

    private List<String> possibleValues(Table table, String attr){
        return table.stream().map(row -> row.getValueByAttribute(attr)).distinct().collect(Collectors.toList());
    }
}
