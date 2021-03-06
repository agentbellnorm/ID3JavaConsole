package agentbellnorm.algorithm;

import com.google.gson.*;
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

    public Node(GainTuple gainTuple, String value) {
        children = new ArrayList<>();
        this.attribute = gainTuple.attribute;
        this.gain = gainTuple.gain;
        this.value = value;
    }

    public Node (String label, String value) {
        children = new ArrayList<>();
        this.label = label;
        this.value = value;
    }

    public void addChild(Node child) {
        children.add(child);
    }

    public boolean isLeaf() {
        return this.children.isEmpty();
    }

    public String getAttribute() {
        return attribute;
    }


    public String getValue() {
        return value;
    }

    public String getLabel() {
        return this.label;
    }

    public List<Node> getChildren() {
        return this.children;
    }

    public String getJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }


}
