package algorithm;

import java.math.BigDecimal;

/**
 * Created by Morgan on 2016-11-09.
 */
public class GainTuple implements Comparable<GainTuple>{
    public String attribute;
    public BigDecimal gain;

    public int compareTo(GainTuple otherTuple) {
        return this.gain.compareTo(otherTuple.gain);
    }

}
