package agentbellnorm.config;

import agentbellnorm.config.Config;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Morgan on 2016-11-28.
 */
public class ConfigTest {

    @Test
    public void load() {
        Config config = Config.load("test");
        Assert.assertEquals("id", config.getIdentifier());
        Assert.assertEquals(",", config.getDelimeter());
        Assert.assertEquals("negative", config.getNegativeOutcome());
        Assert.assertEquals("positive", config.getPositiveOutcome());
        Assert.assertEquals("outcome", config.getOutcomeAttribute());
        Assert.assertEquals("data.txt", config.getDataFileName());
        Assert.assertTrue(!config.getPredictiveColumns().isEmpty());
    }

}
