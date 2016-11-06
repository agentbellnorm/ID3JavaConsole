package com.company.algorithm;

import com.company.model.Model;

/**
 * Created by Morgan on 2016-11-06.
 */
public class ID3Performer {

    private Model model;

    public ID3Performer(Model model) {
        this.model = model;
    }

    public ID3Result perform(){
        return new ID3Result(id3());
    }

    private Node id3() {
        return new Node();
    }
}
