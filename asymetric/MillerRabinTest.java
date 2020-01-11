package asymetric;

import java.math.BigInteger;

/**
 * The Miller-Rabin Test to test primality of a number
 */
public class MillerRabinTest {

    /**
     * Miller-Rabin test bottom up approach
     *
     * @param n number to be tested for primality
     * @return "Probably Prime" or "COMPOSITE"
     */
    public static String checkPrimality(BigInteger n) {
        BigInteger one = BigInteger.ONE;
        BigInteger base = BigInteger.TWO;
        BigInteger nMinusOne = n.subtract(BigInteger.ONE);

        // find k and m
        BigInteger[] KM = findKM(n);

        // find b0
        BigInteger b0 = base.modPow(KM[1], n);
        if (b0.equals(one) || b0.equals(nMinusOne))
            return "Probably Prime";

        // find b1
        BigInteger b1 = b0;
        do {
            System.out.println("FROM asymmetric/MillerRabinTest.java:\t" + b1);
            b1 = b1.pow(2).mod(n);
            if (b1.equals(nMinusOne))
                return "Probably Prime";

        } while (b1.equals(one));

        return "COMPOSITE";
    }

    private static BigInteger[] findKM(BigInteger n) {
        BigInteger n0 = n.subtract(BigInteger.ONE);
        BigInteger base = new BigInteger("2");
        int exp;

        BigInteger[] quotientAndRemainder;
        BigInteger m = BigInteger.ONE;

        for (exp = 0; Integer.MIN_VALUE < exp; exp += 1) {
            quotientAndRemainder = n0.divideAndRemainder(base.pow(exp));

            if (!quotientAndRemainder[1].equals(BigInteger.ZERO))
                break;

            m = quotientAndRemainder[0];
        }

        return new BigInteger[]{new BigInteger(String.valueOf(exp - 1)), m};
    }
}
