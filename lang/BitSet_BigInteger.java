package prepared.lang;

import java.math.BigInteger;
import java.util.BitSet;

/**
 * Created by student on 12/26/16.
 */
public class BitSet_BigInteger {
    //Dont use BitSet; has no >> << operations; use BigInteger, is as fast and as compact
    public static void conversions() {
        BitSet bs = new BitSet(12);
        bs.set(6);
        BigInteger bi = new BigInteger(bs.toByteArray());
        bi.shiftLeft(12);
        bs = BitSet.valueOf(bi.toByteArray());
    }

    public static void main(String[] args) {
        BigInteger bs = BigInteger.ONE;
        bs = bs.shiftLeft(8).subtract(BigInteger.ONE);  //10 ones
        System.out.println(bs.toString(2));
        bs = bs.xor(bs.shiftRight(4));
        System.out.println(bs.toString(2));
        bs = bs.xor(bs.shiftRight(2));
        System.out.println(bs.toString(2));
        bs = bs.xor(bs.shiftRight(1));
        System.out.println(bs.toString(2));
    }
}
