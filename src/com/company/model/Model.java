package com.company.model;

import java.util.List;
import java.util.function.Function;

/**
 * Created by Morgan on 2016-11-06.
 */
public interface Model {
    public List<String> getAttributes();
    public List<Row> getTable();
    public boolean isPositive(Row row);
}
