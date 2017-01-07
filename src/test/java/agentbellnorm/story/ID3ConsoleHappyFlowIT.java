package agentbellnorm.story;

import agentbellnorm.algorithm.Id3Trainer;
import agentbellnorm.algorithm.Id3Tree;
import agentbellnorm.config.Config;
import agentbellnorm.model.Model;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;
import org.junit.Before;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Morgan on 2017-01-07.
 */

public class ID3ConsoleHappyFlowIT {

    private Id3Tree id3Tree;
    private String output;

    @Given("A training dataset from $filename that creates a decision tree")
    public void given(@Named("filename") String filename){

        Model golfModel = new Model(Config.load("golf"));
        Id3Trainer id3Trainer = new Id3Trainer(golfModel);
        id3Tree = id3Trainer.train();

    }

    @When("performing a prediction on a days $variables")
    public void when(@Named("variables") String variables){
        Map<String, String> testData = constructMap(variables);
        output = id3Tree.predict(testData);

    }

    @Then("the outcome should be predicted as $outcome")
    public void then(@Named("outcome") String outcome){
        Assert.assertEquals(outcome, output);
    }

    private Map<String,String> constructMap(String variables) {
        Map<String, String> testData = new HashMap<>();
        List<String> pairs = Arrays.asList(variables.split(";"));
        for(String pair : pairs) {
            String[] keyAndValue = pair.split("->");
            testData.put(keyAndValue[0], keyAndValue[1]);
        }
        return testData;
    }
}
