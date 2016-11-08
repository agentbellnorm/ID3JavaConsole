package model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Morgan on 2016-11-08.
 */
public class TableTest {

    private String allPositiveFile = "model/test/all_positive.txt";
    private String allNegativeFile = "model/test/all_negative.txt";
    private String onePositiveFile = "model/test/one_positive.txt";
    private String oneNegativeFile = "model/test/one_negative.txt";
    private String mixedFile = "model/test/mixed.txt";
    private String outcomeDeterminator = "outcome";
    private String positiveOutcome = "positive";

    @Test
    public void allPositive() {
        Model model = new Model(allPositiveFile, outcomeDeterminator, positiveOutcome);
        Table table = model.getTable();

        Assert.assertTrue(table.allPositive());
    }

    @Test
    public void allNegative() {
        Model model = new Model(allNegativeFile, outcomeDeterminator, positiveOutcome);
        Table table = model.getTable();

        Assert.assertTrue(table.allNegative());
    }

    @Test
    public void mixed() {
        Model model = new Model(mixedFile, outcomeDeterminator, positiveOutcome);
        Table table = model.getTable();

        Assert.assertFalse(table.allNegative());
        Assert.assertFalse(table.allPositive());
    }

    @Test
    public void onePositive() {
        Model model = new Model(onePositiveFile, outcomeDeterminator, positiveOutcome);
        Table table = model.getTable();

        Assert.assertTrue(table.isPositive(table.get(0)));
    }

    @Test
    public void oneNegative() {
        Model model = new Model(oneNegativeFile, outcomeDeterminator, positiveOutcome);
        Table table = model.getTable();

        Assert.assertFalse(table.isPositive(table.get(0)));
    }
}