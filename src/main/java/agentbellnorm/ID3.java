package agentbellnorm;

import agentbellnorm.algorithm.Id3Trainer;
import agentbellnorm.algorithm.Id3Tree;
import agentbellnorm.config.Config;
import agentbellnorm.model.Model;

public class ID3 {

    public static void main(String[] args) {
        Model golfModel = new Model(Config.load(args[0]));
        Id3Trainer id3Trainer = new Id3Trainer(golfModel);

        Id3Tree id3Tree = id3Trainer.train();

        System.out.println(id3Tree.printTree());
    }
}
