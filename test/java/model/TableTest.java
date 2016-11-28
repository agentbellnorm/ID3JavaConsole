package model;

import com.google.gson.Gson;
import config.Config;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Morgan on 2016-11-08.
 */
public class TableTest {

    private Config config;

    private Model model;
    private Table table;

    @Test
    public void allPositive() {
        configure("all_positive");

        Assert.assertTrue(table.allPositive());
    }

    @Test
    public void allNegative() {
        configure("all_negative");

        Assert.assertTrue(table.allNegative());
    }

    @Test
    public void mixed() {
        configure("mixed");

        Assert.assertFalse(table.allNegative());
        Assert.assertFalse(table.allPositive());
    }

    @Test
    public void onePositive() {
        configure("one_positive");

        Assert.assertTrue(table.isPositive(table.get(0)));
    }

    @Test
    public void oneNegative() {
        configure("one_negative");

        Assert.assertFalse(table.isPositive(table.get(0)));
    }

    @Test
    public void subTable() {
    }

    @Test
    public void mostPositive() {
        configure("more_positive");

        Assert.assertEquals(config.getPositiveOutcome(), table.mostPositiveOrNegative());
    }

    @Test
    public void mostNegative() {
        configure("more_negative");

        Assert.assertEquals(config.getNegativeOutcome(), table.mostPositiveOrNegative());
    }

    @Test
    public void sameNegativeAndPositive() {
        configure("one_neg_one_pos");

        String output = table.mostPositiveOrNegative();

        Assert.assertTrue(output.contains(config.getPositiveOutcome()) && output.contains(config.getNegativeOutcome()));
    }

    @Test
    public void weightIsOne() {
        configure("weight_of_class_is_one");

        Assert.assertTrue(BigDecimal.ONE.compareTo(table.weightOfClassOfAttribute("aaa", "a1")) == 0);
    }

    @Test
    public void weightIsZero() {
        configure("weight_of_class_is_zero");
        BigDecimal ZERO = new BigDecimal("0");

        Assert.assertTrue(BigDecimal.ZERO.compareTo(table.weightOfClassOfAttribute("aaa", "a1")) == 0);
    }

    @Test
    public void weightIsHalf() {
        configure("weight_of_class_is_point_five");
        BigDecimal POINT_FIVE = new BigDecimal("0.5");


        Assert.assertTrue(POINT_FIVE.compareTo(table.weightOfClassOfAttribute("aaa", "a1")) == 0);
    }

    @Test
    public void possibleValues() {
        configure("mixed");
        int NUMBER_OF_DISTINCT_VALUES_IN_DATA = 2;

        List<String> possibleValues = table.possibleValues("a1");

        Assert.assertTrue(possibleValues.contains("aaa") && possibleValues.contains("bbb"));
        Assert.assertEquals(NUMBER_OF_DISTINCT_VALUES_IN_DATA, possibleValues.size());
    }

    @Test
    public void filteredSubTable() {
        configure("mixed");

        Table filteredTableWithA = table.filteredSubTable("a1", "aaa");
        Table filteredTableWithB = table.filteredSubTable("a1", "bbb");

        Assert.assertTrue(table.size() == 3 && filteredTableWithA.size() == 1);
        Assert.assertFalse(filteredTableWithA.contains("bbb"));

        Assert.assertTrue(table.size() == 3 && filteredTableWithB.size() == 2);
        Assert.assertFalse(filteredTableWithB.contains("aaa"));

    }

    private void configure(String dataFile) {
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
        model = new Model(config);
        table = model.getTable();
    }
}