package model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Morgan on 2016-11-08.
 */
public class TableTest {

    private String outcomeDeterminator = "outcome";
    private String positiveOutcome = "positive";
    private String negativeOutcome = "negative";

    @Test
    public void allPositive() {
        Model model = new Model(testData("all_positive"), outcomeDeterminator, positiveOutcome, negativeOutcome);
        Table table = model.getTable();

        Assert.assertTrue(table.allPositive());
    }

    @Test
    public void allNegative() {
        Model model = new Model(testData("all_negative"), outcomeDeterminator, positiveOutcome, negativeOutcome);
        Table table = model.getTable();

        Assert.assertTrue(table.allNegative());
    }

    @Test
    public void mixed() {
        Model model = new Model(testData("mixed"), outcomeDeterminator, positiveOutcome, negativeOutcome);
        Table table = model.getTable();

        Assert.assertFalse(table.allNegative());
        Assert.assertFalse(table.allPositive());
    }

    @Test
    public void onePositive() {
        Model model = new Model(testData("one_positive"), outcomeDeterminator, positiveOutcome, negativeOutcome);
        Table table = model.getTable();

        Assert.assertTrue(table.isPositive(table.get(0)));
    }

    @Test
    public void oneNegative() {
        Model model = new Model(testData("one_negative"), outcomeDeterminator, positiveOutcome, negativeOutcome);
        Table table = model.getTable();

        Assert.assertFalse(table.isPositive(table.get(0)));
    }

    @Test
    public void subTable() {
        Model model = new Model(testData(""), outcomeDeterminator, positiveOutcome, negativeOutcome);
        Table table = model.getTable();


    }

    @Test
    public void mostPositive() {
        Model model = new Model(testData("more_positive"), outcomeDeterminator, positiveOutcome, negativeOutcome);
        Table table = model.getTable();
        Assert.assertEquals(positiveOutcome, table.mostPositiveOrNegative());
    }

    @Test
    public void mostNegative() {
        Model model = new Model(testData("more_negative"), outcomeDeterminator, positiveOutcome, negativeOutcome);
        Table table = model.getTable();
        Assert.assertEquals(negativeOutcome, table.mostPositiveOrNegative());
    }

    @Test
    public void sameNegativeAndPositive() {
        Model model = new Model(testData("one_neg_one_pos"), outcomeDeterminator, positiveOutcome, negativeOutcome);
        Table table = model.getTable();
        String output = table.mostPositiveOrNegative();
        Assert.assertTrue(output.contains(positiveOutcome) && output.contains(negativeOutcome));
    }

    private String testData(String name) {
        return "model/test/"+name+".txt";
    }
}