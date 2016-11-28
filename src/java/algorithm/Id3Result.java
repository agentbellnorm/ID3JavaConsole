package algorithm;

import java.util.List;

/**
 * Created by Morgan on 2016-11-06.
 */
public class Id3Result {
    private Node resultRoot;

    private int width = 176;

    public Id3Result(Node root) {
        resultRoot = root;
    }

    public String printResult() {
        if (resultRoot != null) {
            return(resultRoot.getJson());
        }
        return "No result to print";

    }



}
