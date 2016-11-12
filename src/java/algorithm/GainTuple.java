package algorithm;

import java.math.BigDecimal;

/**
 * Created by Morgan on 2016-11-09.
 */
public class GainTuple implements Comparable<GainTuple>{
    public String attribute;
    public BigDecimal gain;

    public GainTuple(String attribute, BigDecimal gain) {
        this.attribute = attribute;
        this.gain = gain;
    }

    public int compareTo(GainTuple otherTuple) {
        return -1 * this.gain.compareTo(otherTuple.gain);
    }

}
