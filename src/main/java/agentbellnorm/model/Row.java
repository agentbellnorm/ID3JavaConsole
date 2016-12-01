package agentbellnorm.model;

import java.util.Map;

/**
 * Created by Morgan on 2016-11-06.
 */
public class Row{
    private final Map<String, String> values;

    public Row(Map<String, String> values) {
        this.values = values;
    }

    public String getValueByAttribute(String attribute) {
        if(values.containsKey(attribute)) {
            return values.get(attribute);
        }
        throw new RuntimeException("Could not get row value by attribute"); //Fail fast.
    }
}
