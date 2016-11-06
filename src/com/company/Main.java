package com.company;

import com.company.algorithm.ID3Performer;
import com.company.algorithm.ID3Result;
import com.company.model.GolfModel;
import com.company.model.Model;

public class Main {

    public static void main(String[] args) {
        Model golfModel = new GolfModel();
        ID3Performer id3Performer = new ID3Performer(golfModel);
        ID3Result id3Result = id3Performer.perform();
        System.out.println(id3Result.printResult());


    }
}
