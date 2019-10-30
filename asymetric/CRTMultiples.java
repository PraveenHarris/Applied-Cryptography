package asymetric;

import java.math.BigInteger;

import static util.MathUtil.gcd;

public class CRTMultiples {

    private static BigInteger compute(BigInteger[][] pairs) {
        /*
            Use this method for examples like:
                x = 3 (mod 5)
                x = 1 (mod 7)
                x = 6 (mod 8)

            Algorithm:
                1. Compute N by multiplying all BI_i_modValue
                2. Compute n_i by computing N / n_i
                3. Compute x_i by n_i mod BI_i_modValue
                4. Compute s_i by BI_i_fullValue * n_i * x_i
                5. Compute (Sum all values of s) mod N
         */

        // 1. Compute n_i
        BigInteger[] nValues = new BigInteger[pairs.length];
        BigInteger N = pairs[0][1];
        for (int i=1; i<pairs.length; i++) {
            N = N.multiply(pairs[i][1]);
        }
        for (int i=0; i<pairs.length; i++) {
            nValues[i] = N.divide(pairs[i][1]);
        }

        // 2. Compute x_i
        BigInteger[] xValues = new BigInteger[pairs.length];
        for (int i=0; i<pairs.length; i++) {
            xValues[i] = nValues[i].modInverse(pairs[i][1]);
        }

        // 3. Compute s_i
        BigInteger[] sValues = new BigInteger[pairs.length];
        for (int i=0; i<pairs.length; i++) {
            sValues[i] = pairs[i][0].multiply(nValues[i]).multiply(xValues[i]);
        }

        // 4. Compute sum and mod
        BigInteger x = new BigInteger("0");
        for (int i=0; i<pairs.length; i++) {
            x = x.add(sValues[i]);
        }
        x = x.mod(N);

        return x;
    }

    private static BigInteger computeWhenXInRightSide(BigInteger[][] pairs) {
        return compute(pairs);
    }

    private static BigInteger computeWhenXInLeftSide(BigInteger[][] pairs) {
        /*
            Use this method for examples like:
                x (mod 13) = 2
                x (mod 11) = 8
         */

        // change to the form: 2 (mod 13) = x
        for (int i=0; i<pairs.length; i++) {
            pairs[i] = new BigInteger[] {pairs[i][1], pairs[i][0]};
        }

        BigInteger x = compute(pairs);

        return x;
    }

    private static BigInteger computeWhenXInMiddle(BigInteger[][] pairs) {
        BigInteger currentGCD = pairs[0][0].subtract(pairs[0][1]);
        for (int i=1; i<pairs.length; i++) {
            currentGCD = currentGCD.gcd(pairs[i][0].subtract(pairs[i][1]));
        }

        return currentGCD;
    }

    public static void main(String[] args) {

        // initial set up: 3 (mod 5) = x => BI(3), BI(5)
        BigInteger[][] pairs1 = {
                {new BigInteger("3"), new BigInteger("5")},
                {new BigInteger("1"), new BigInteger("7")},
                {new BigInteger("6"), new BigInteger("8")},
        };
        BigInteger x1 = CRTMultiples.computeWhenXInRightSide(pairs1);
        System.out.println("p1: " + x1);

        // initial set up: x (mod 13) = 2 => BI(13), BI(2)
        BigInteger[][] pairs2 = {
                {new BigInteger("13"), new BigInteger("2")},
                {new BigInteger("11"), new BigInteger("8")},
        };
        BigInteger x2 = CRTMultiples.computeWhenXInLeftSide(pairs2);
        System.out.println("p2: " + x2);

        // initial set up: 44 (mod x) = 2 => BI(44), BI(2)
        BigInteger[][] pairs3 = {
                {new BigInteger("327"), new BigInteger("7")},
                {new BigInteger("858"), new BigInteger("10")},
                {new BigInteger("1487"), new BigInteger("15")},
        };
        BigInteger x3 = CRTMultiples.computeWhenXInMiddle(pairs3);
        System.out.println("p3: " + x3);

    }

}











