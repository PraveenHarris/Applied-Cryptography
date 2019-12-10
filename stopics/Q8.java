package stopics;

import util.CryptoTools;

import java.math.BigInteger;

public class Q8 {
    public static void main(String[] args) {
        BigInteger p = new BigInteger("341769842231234673709819975074677605139");
        BigInteger g = new BigInteger("37186859139075205179672162892481226795");
        BigInteger aX = new BigInteger("83986164647417479907629397738411168307");
        BigInteger bX = new BigInteger("140479748264028247931575653178988397140");

        // 1. Find public DH key
        BigInteger aY = g.modPow(aX, p);
        BigInteger bY = g.modPow(bX, p);

        // 2. Use public DH keys to obtain session key
        BigInteger sessionKeyA = bY.modPow(aX, p);
        BigInteger sessionKeyB = aY.modPow(bX, p);

        System.out.println(CryptoTools.bytesToHex(sessionKeyA.toByteArray()));
        String keyAHex = CryptoTools.bytesToHex(sessionKeyA.toByteArray());
        String keyBHex = CryptoTools.bytesToHex(sessionKeyB.toByteArray());
        System.out.println("Are the generated session keys equal? " +  keyAHex.equals(keyBHex));
        String answer = "00C15A519D8BB2050044D9E7F9803CCF66";
        System.out.println("Are the generated session keys equal to the desired answer? " +  keyAHex.equals(answer));

    }
}
