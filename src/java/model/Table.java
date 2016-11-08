package model;

import java.util.ArrayList;

/**
 * Created by Morgan on 2016-11-08.
 */
public class Table extends ArrayList<Row>{
    private AttributeList attributes;
    private final String SUCCESS_ATTRIBUTE;
    private final String SUCCESS_VALUE;

    public Table(AttributeList attributes, String successAttribute, String successValue) {
        this.attributes = attributes;
        SUCCESS_ATTRIBUTE = successAttribute;
        SUCCESS_VALUE = successValue;
    }

    public AttributeList getAttributes() {
        return attributes;
    }

    public boolean isPositive(Row row) {
        String outcome = row.getValueByAttribute(SUCCESS_ATTRIBUTE);
        return SUCCESS_VALUE.equals(outcome);
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
}
