package com.company.algorithm;

/**
 * Created by Morgan on 2016-11-06.
 */
public class ID3Result {
    private Node resultRoot;

    public ID3Result(Node root) {
        resultRoot = root;
    }

    public String printResult() {
        if (resultRoot != null) {
            return "this is the printed tree";
        }
        return "No result to print";

    }
}
