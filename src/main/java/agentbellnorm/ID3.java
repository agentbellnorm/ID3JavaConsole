package agentbellnorm;

import agentbellnorm.algorithm.Id3Performer;
import agentbellnorm.algorithm.Id3Result;
import agentbellnorm.config.Config;
import agentbellnorm.model.Model;

public class ID3 {

    public static void main(String[] args) {
        Model golfModel = new Model(Config.load(args[0]));
        Id3Performer id3Performer = new Id3Performer(golfModel);

        Id3Result id3Result = id3Performer.perform();

        System.out.println(id3Result.printResult());
    }
}
