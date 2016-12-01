package agentbellnorm.model;

import com.google.gson.Gson;
import agentbellnorm.config.Config;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Morgan on 2016-11-08.
 */
public class ModelTest {

    Config config;

    @Before
    public void before() {
        setup("all_positive");
    }

   @Test
    public void modelCreated() {
       Model model = new Model(config);
       Assert.assertNotNull(model);
   }

    private void setup(String dataFile) {
        String configJson = "{\n" +
                "  \"dataFileName\": \"model/test/"+dataFile+".txt\",\n" +
                "  \"outcomeAttribute\": \"outcome\",\n" +
                "  \"positiveOutcome\": \"positive\",\n" +
                "  \"negativeOutcome\": \"negative\",\n" +
                "  \"identifier\": \"id\",\n" +
                "  \"delimeter\": \",\",\n" +
                "  \"predictiveColumns\": [\"a1\"]\n" +
                "}";
        config = new Gson().fromJson(configJson, Config.class);
    }

}