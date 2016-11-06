package com.company.model;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Created by Morgan on 2016-11-06.
 */
public class Row implements RowInterface{
    private Map<String, String> values;

    public Row(String name, String value) {
        values = new HashMap<>();
        values.put(name, value);
    }


    public String getValueByName(String name) {
        return values.get(name);
    }
}
