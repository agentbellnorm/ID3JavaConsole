package agentbellnorm.algorithm;

/**
 * Created by Morgan on 2016-11-06.
 */
public class Id3Result {
    private Node resultRoot;

    public Id3Result(Node root) {
        resultRoot = root;
    }

    public String printResult() {
        if (resultRoot != null) {
            return resultRoot.getJson();
        }
        return "No result to print";

    }



}
