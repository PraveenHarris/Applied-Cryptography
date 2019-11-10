package asymetric;

import util.MillerRabinTopDown;

import java.math.BigInteger;

public class Q5 {
    public static void main(String[] args) {
        BigInteger n = new BigInteger("1033931178476059651954862004553");
        String primality = MillerRabinTest.checkPrimality(n);
        System.out.println(primality);
//        (new MillerRabinTopDown(n)).test(new BigInteger("2"), true);
    }
}
