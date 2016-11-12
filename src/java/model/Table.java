package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by Morgan on 2016-11-08.
 */
public class Table extends ArrayList<Row>{
    private AttributeList attributes;
    private final String OUTCOME_ATTRIBUTE;
    private final String POSITIVE_VALUE;
    private final String NEGATIVE_VALUE;

    public Table(AttributeList attributes, String outcomeAttribute, String positiveValue, String negativeValue) {
        this.attributes = attributes;
        OUTCOME_ATTRIBUTE = outcomeAttribute;
        POSITIVE_VALUE = positiveValue;
        NEGATIVE_VALUE = negativeValue;
    }

    public AttributeList getAttributes() {
        return attributes;
    }

    public boolean isPositive(Row row) {
        String outcome = row.getValueByAttribute(OUTCOME_ATTRIBUTE);
        return POSITIVE_VALUE.equals(outcome);
    }

    public boolean allPositive() {
        for(Row row : this) {
            if(!isPositive(row)) {
                return false;
            }
        }
        return true;
    }

    public boolean allNegative() {
        for (Row row : this) {
            if (isPositive(row)){
                return false;
            }
        }
        return true;
    }

    public String mostPositiveOrNegative() {
        int positive = (int) this.stream().filter(row -> isPositive(row)).count();
        int negative = this.size() - positive;

        if (positive > negative) {
            return POSITIVE_VALUE;
        }
        else if (negative > positive) {
            return NEGATIVE_VALUE;
        } else {
            return POSITIVE_VALUE+" and "+NEGATIVE_VALUE+" are equaly likely";
        }
    }

    public Table filteredSubTable(String attribute, String value) {
        Table newTable = new Table(this.attributes, OUTCOME_ATTRIBUTE, POSITIVE_VALUE, NEGATIVE_VALUE);
        newTable.addAll(this.stream().filter(row -> value.equals(row.getValueByAttribute(attribute))).collect(Collectors.toList()));
        return newTable;
    }

    public List<String> possibleValues(String attr){
        return this.stream().map(row -> row.getValueByAttribute(attr)).distinct().collect(Collectors.toList());
    }

    public BigDecimal weightOfClassOfAttribute(String className, String attribute) {
        return new BigDecimal(this.stream().filter(row -> className.equals(row.getValueByAttribute(attribute))).count()).divide(new BigDecimal(this.size()), 5, BigDecimal.ROUND_HALF_UP);
    }
}
