package stopics;

import java.math.BigInteger;
import java.util.HashSet;

public class DiscreteLogEngine {

    public static String computeUsingDLogTable(int b, int m, int v) {
        BigInteger base = new BigInteger(String.valueOf(b));
        BigInteger modulus = new BigInteger(String.valueOf(m));
        BigInteger val = new BigInteger(String.valueOf(v));

        BigInteger pow = new BigInteger("1");
        BigInteger result = base.modPow(pow, modulus);
        while (! result.equals(val)) {

            pow = pow.add(BigInteger.ONE);
            result = base.modPow(pow, modulus);

            if (result.equals(BigInteger.ONE) || result.equals(BigInteger.ZERO)) {
                return "-1";
            }

        }

        return pow.toString();
    }

    public static boolean isPrimitiveRoot(int m, int b) {
        BigInteger modulus = new BigInteger(String.valueOf(m));
        BigInteger base = new BigInteger(String.valueOf(b));

        HashSet<BigInteger> set = new HashSet<>();
        BigInteger pow = BigInteger.ZERO;
        BigInteger val;
        BigInteger goUpto = modulus.subtract(BigInteger.TWO);
        while (pow.compareTo(goUpto) < 0) {
            pow = pow.add(BigInteger.ONE);
            val = base.modPow(pow, modulus);

            set.add(val);
        }

        if ((set.size()==(m-2)) && base.modPow(modulus.subtract(BigInteger.ONE), modulus).equals(BigInteger.ONE)) {
            return true;
        } else {
            return false;
        }
    }

}
