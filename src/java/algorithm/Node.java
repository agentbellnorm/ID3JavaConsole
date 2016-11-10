package algorithm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Morgan on 2016-11-06.
 */
public class Node {
    private String attribute;
    private BigDecimal gain;
    private String label;
    private String value;
    private List<Node> children;

    public Node(GainTuple gainTuple, String label, String value) {
        children = new ArrayList<>();
        this.attribute = gainTuple.attribute;
        this.gain = gainTuple.gain;
        this.label = label;
        this.value = value;
    }

    public Node (String label, String value) {
        children = new ArrayList<>();
        this.label = label;
        this.value = value;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        attribute = attribute;
    }

    public BigDecimal getGain() {
        return gain;
    }

    public void setGain(BigDecimal gain) {
        this.gain = gain;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<Node> getChildren() {
        return children;
    }
}
