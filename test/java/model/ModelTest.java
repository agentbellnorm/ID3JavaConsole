package model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Morgan on 2016-11-08.
 */
public class ModelTest {

    Map<String, String> config;

    @Before
    public void before() {
        config = new HashMap<>();
        config.put("filepath", "model/test/all_positive.txt");
        config.put("outcome", "Play");
        config.put("positiveoutcome", "Yes");
        config.put("negativeoutcome", "No");
        config.put("identifier", "Day");
        config.put("delimeter", ",");
    }

   @Test
    public void modelCreated() {
       Model model = new Model(config);
       Assert.assertNotNull(model);
   }

}