package model;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Created by Morgan on 2016-11-08.
 */
public class AttributeList extends ArrayList<String> {
    public AttributeList subtract(AttributeList attributesToRemove) {
        return this.stream()
                .filter(currentAttribute -> !attributesToRemove.contains(currentAttribute))
                .collect(Collectors.toCollection(AttributeList::new));
    }
}
