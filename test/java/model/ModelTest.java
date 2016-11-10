package model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Morgan on 2016-11-08.
 */
public class ModelTest {

    String dataPath = "model/test/all_positive.txt";
    String successAttribute = "outcome";
    String positiveValue = "positive";
    String negativeValue = "negative";

   @Test
    public void modelCreated() {
       Model model = new Model(dataPath, successAttribute, positiveValue, negativeValue);
       Assert.assertNotNull(model);
   }

}