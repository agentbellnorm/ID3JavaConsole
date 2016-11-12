package algorithm;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIDeclaration;
import model.Row;
import model.Table;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by Morgan on 2016-11-11.
 */
public abstract class Id3Calculations {


    public static GainTuple gainByAttribute(Table table, String attribute) {
        Predicate<Row> noFilter = (row -> true);

        BigDecimal gainForWholeTable = entropy(table, noFilter);

        BigDecimal sumOfGainOfClasses = table
                .possibleValues(attribute).stream()
                .map(valueClass -> gainOfClass(table, attribute, valueClass))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal gainOfAttribute = gainForWholeTable.subtract(sumOfGainOfClasses);

        return new GainTuple(attribute, gainOfAttribute);

    }

    private static BigDecimal gainOfClass(Table table, String attribute, String value) {
        Predicate<Row> filterPredicate = (row -> value.equals(row.getValueByAttribute(attribute)));

        return table.weightOfClassOfAttribute(value,attribute).multiply(entropy(table, filterPredicate));
    }

    private static BigDecimal probability(Table table, Predicate<Row> filterPredicate)
    {
        Predicate<Row> isPositivePredicate = (row -> table.isPositive(row));
        Predicate<Row> isPositiveAndFilteredPredicate = filterPredicate.and(isPositivePredicate);

        BigDecimal total = BigDecimal.valueOf(table.stream().filter(filterPredicate).count());
        BigDecimal positive = BigDecimal.valueOf(table.stream().filter(isPositiveAndFilteredPredicate).count());

        return positive.divide(total, 5, BigDecimal.ROUND_HALF_UP);
    }

    private static BigDecimal entropy(Table table, Predicate<Row> filterPredicate) {
        BigDecimal probability = probability(table, filterPredicate);

        if (BigDecimal.ZERO.compareTo(probability) == 0) {
            return BigDecimal.ZERO;
        }
        else if (BigDecimal.ONE.compareTo(probability) == 0) {
            return BigDecimal.ZERO;
        }
        else {
            return e_vlad(probability);
        }
    }

    private static BigDecimal e_vlad(BigDecimal probability)
    {
        // (prob * Math.Log(1 / prob, 10)) + ((1 - prob) * Math.Log(1 / (1 - prob), 10));

        return
                probability
            .multiply(
                log10(BigDecimal.ONE.divide(probability, 5, BigDecimal.ROUND_HALF_UP)))
        .add(
                BigDecimal.ONE.subtract(probability)
            .multiply(
                log10(      BigDecimal.ONE
                        .divide(
                                BigDecimal.ONE
                            .subtract(
                                probability), 5, BigDecimal.ROUND_HALF_UP))));
    }

    private static BigDecimal log10(BigDecimal a) {
        return BigDecimal.valueOf(Math.log10(a.doubleValue()));
    }

    private static BigDecimal e_internet(BigDecimal prob)
    {
        // return -(prob * Math.Log(prob, 2)) - ((1 - prob) * Math.Log((1 - prob), 2));
        throw new NotImplementedException();
    }
}
