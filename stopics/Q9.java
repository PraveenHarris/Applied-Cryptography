package stopics;

import java.math.BigInteger;
import java.util.Random;

public class Q9 {
    public static void main(String[] args) {
        BigInteger secret = new BigInteger("291639075201575653178417");
        BigInteger[] shares = generateShares(secret, 5, 80);

        // shares generated
        System.out.println("---SHARES GENERATED");
        BigInteger actual = BigInteger.ZERO;
        for (int i=0; i<shares.length; i++) {
            System.out.printf("%dth share:\t%s\n", i, shares[i]);
            actual = actual.xor(shares[i]);
        }

        //
        System.out.println("\n---VERIFICATION");
        System.out.println("exp:\t" + secret);
        System.out.println("actual:\t" + actual);
        System.out.println("Are they the same? " + secret.equals(actual));


    }

    static BigInteger[] generateShares(BigInteger secret, int numberOfShares, int bitLength) {
        /*
        shares[0:shares.length-1]: randomly generated share
        shares[shares.length-1]: configured share
         */
        BigInteger[] shares = new BigInteger[numberOfShares];

        // generate numberOfShares-1 shares
        BigInteger candidate, lastShare = secret;
        for (int i=0; i<numberOfShares-1; i++) {
            do {
                candidate = new BigInteger(bitLength, new Random());
            } while (candidate.equals(BigInteger.ZERO));

            shares[i] = candidate;
            lastShare = lastShare.xor(candidate);
        }

        // configure last share
        shares[shares.length-1] = lastShare;

        // compare xor of all shares with secret to validate
        BigInteger actual = shares[0];
        for (int i=1; i<numberOfShares; i++) {
            actual = actual.xor(shares[i]);
        }

        return shares;
    }

}
