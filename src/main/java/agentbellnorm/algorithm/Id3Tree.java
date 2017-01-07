package agentbellnorm.algorithm;

import java.util.Map;

/**
 * Created by Morgan on 2016-11-06.
 */
public class Id3Tree {
    private Node root;

    public Id3Tree(Node root) {
        this.root = root;
    }

    public String printTree() {
        if (root != null) {
            return root.getJson();
        }
        return "No tree to print";
    }

    public String predict(Map<String, String> testData) {
        return traverseToFindOutcome(root, testData);
    }

    private String traverseToFindOutcome(Node node, Map<String, String> testData) {
        String resultString = "";

        if(node.isLeaf()) {
            resultString += node.getLabel();
            return resultString;
        }

        for(Node child : node.getChildren()) {
            if(child.getValue().equals(testData.get(node.getAttribute()))) {
                resultString += traverseToFindOutcome(child, testData);
            }
        }

        return resultString;
    }
}
