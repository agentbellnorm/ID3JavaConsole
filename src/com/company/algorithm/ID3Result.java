package com.company.algorithm;

/**
 * Created by Morgan on 2016-11-06.
 */
public class ID3Result {
    private ID3Performer performer = new ID3Performer();
    private Node resultRoot;

    public ID3Result() {

    }

    public String printResult() {
        resultRoot = performer.perform();
        if (resultRoot != null) {
            return "this is the printed tree";
        }
        return "No result to print";

    }
}
