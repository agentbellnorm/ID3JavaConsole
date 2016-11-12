package model;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Created by Morgan on 2016-11-08.
 */
public class AttributeList extends ArrayList<String> {
    public AttributeList subListWithout(String attributeToRemove) {
        return this.stream()
                .filter(currentAttribute -> !currentAttribute.equals(attributeToRemove))
                .collect(Collectors.toCollection(AttributeList::new));
    }

    //TODO: maybe not necessary?
}
