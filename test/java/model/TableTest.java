package model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Morgan on 2016-11-08.
 */
public class TableTest {

    private Map<String, String> config;

    private Model model;
    private Table table;

    @Before
    public void before() {
        config = new HashMap<>();
        config.put("outcome", "outcome");
        config.put("positiveoutcome", "positive");
        config.put("negativeoutcome", "negative");
        config.put("identifier", "id");
        config.put("delimeter", ",");
    }

    @Test
    public void allPositive() {
        setup("all_positive");

        Assert.assertTrue(table.allPositive());
    }

    @Test
    public void allNegative() {
        setup("all_negative");

        Assert.assertTrue(table.allNegative());
    }

    @Test
    public void mixed() {
        setup("mixed");

        Assert.assertFalse(table.allNegative());
        Assert.assertFalse(table.allPositive());
    }

    @Test
    public void onePositive() {
        setup("one_positive");

        Assert.assertTrue(table.isPositive(table.get(0)));
    }

    @Test
    public void oneNegative() {
        setup("one_negative");

        Assert.assertFalse(table.isPositive(table.get(0)));
    }

    @Test
    public void subTable() {
    }

    @Test
    public void mostPositive() {
        setup("more_positive");

        Assert.assertEquals(config.get("positiveoutcome"), table.mostPositiveOrNegative());
    }

    @Test
    public void mostNegative() {
        setup("more_negative");

        Assert.assertEquals(config.get("negativeoutcome"), table.mostPositiveOrNegative());
    }

    @Test
    public void sameNegativeAndPositive() {
        setup("one_neg_one_pos");

        String output = table.mostPositiveOrNegative();

        Assert.assertTrue(output.contains(config.get("positiveoutcome")) && output.contains(config.get("negativeoutcome")));
    }

    @Test
    public void weightIsOne() {
        setup("weight_of_class_is_one");
        BigDecimal ONE = new BigDecimal("1");

        Assert.assertEquals(ONE, table.weightOfClassOfAttribute("aaa", "a1"));
    }

    @Test
    public void weightIsZero() {
        setup("weight_of_class_is_zero");
        BigDecimal ZERO = new BigDecimal("0");

        Assert.assertEquals(ZERO, table.weightOfClassOfAttribute("aaa", "a1"));
    }

    @Test
    public void weightIsHalf() {
        setup("weight_of_class_is_point_five");
        BigDecimal POINT_FIVE = new BigDecimal("0.5");


        Assert.assertEquals(POINT_FIVE, table.weightOfClassOfAttribute("aaa", "a1"));
    }

    @Test
    public void possibleValues() {
        setup("mixed");
        int DISTINCT_VALUES_IN_DATA = 2;

        List<String> possibleValues = table.possibleValues("a1");

        Assert.assertTrue(possibleValues.contains("aaa") && possibleValues.contains("bbb"));
        Assert.assertEquals(DISTINCT_VALUES_IN_DATA, possibleValues.size());
    }

    @Test
    public void filteredSubTable() {
        setup("mixed");

        Table filteredTableWithA = table.filteredSubTable("a1", "aaa");
        Table filteredTableWithB = table.filteredSubTable("a1", "bbb");

        Assert.assertTrue(table.size() == 3 && filteredTableWithA.size() == 1);
        Assert.assertFalse(filteredTableWithA.contains("bbb"));

        Assert.assertTrue(table.size() == 3 && filteredTableWithB.size() == 2);
        Assert.assertFalse(filteredTableWithB.contains("aaa"));

    }

    private void setup(String dataFile) {
        config.put("filepath", testData(dataFile));
        model = new Model(config);
        table = model.getTable();
    }

    private String testData(String name) {
        return "model/test/"+name+".txt";
    }
}