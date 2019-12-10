package stopics;

import util.MyTools;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;

public class Q9 {
    public static void main(String[] args) {
        BigInteger secret = new BigInteger("291639075201575653178417");
        byte[][] shares = generateShares(secret, 5, 80);

    }

    static byte[][] generateShares(BigInteger secret, int numberOfShares, int bitLength) {
        int byteLength = bitLength / 8;
        /*
        bitString[0]: secret
        bitString[1:bitString.length-1]: randomly generated share (in binary form)
        bitString[bitString.length-1]: configured share (in binary form)
         */
        String[] bitStrings = new String[numberOfShares+1];
        bitStrings[0] = MyTools.byteArrayToBinaryString(secret.toByteArray());
        System.out.println("sec:\t" + bitStrings[0]);

        // generate numberOfShares-1 shares
        BigInteger candidate;
        for (int i=0; i<numberOfShares-1; i++) {
            candidate = new BigInteger(bitLength, new Random());
            while (candidate.equals(BigInteger.ZERO)) {
                candidate = new BigInteger(bitLength, new Random());
            }

            byte[] share = candidate.toByteArray();
            // trim share if the bitLength is one more
            if (share.length == byteLength+1) {
                share = Arrays.copyOfRange(share, 1, share.length);
            }

            bitStrings[i+1] = MyTools.byteArrayToBinaryString(share);
            System.out.println("bStr" + (i+1) + ":\t" + bitStrings[i+1]);
        }

        // generate last share
        String lastShare = "";
        for (int bitIndex=0; bitIndex<bitLength; bitIndex++) {
            boolean x = bitStrings[0].charAt(bitIndex) == '1';
            for (int i=1; i<bitStrings.length-1; i++) {
                x = x ^ (bitStrings[i].charAt(bitIndex) == '1');
            }
            lastShare += x ? "1" : "0";
        }
        bitStrings[bitStrings.length-1] = lastShare;
        System.out.println("bStrN:\t" + bitStrings[bitStrings.length-1]);


        // compare xor of all shares with secret to validate
        System.out.println("\n\n---CONFIRM");
        String actual = "";
        for (int bitIndex=0; bitIndex<bitLength; bitIndex++) {
            boolean x = bitStrings[1].charAt(bitIndex) == '1';
            for (int i=2; i<bitStrings.length; i++) {
                x = x ^ (bitStrings[i].charAt(bitIndex) == '1');
            }
            actual += x ? "1" : "0";
        }
        System.out.println("exp:\t" + bitStrings[0]);
        System.out.println("actual:\t" + actual);
        System.out.println("Are they the same? " + bitStrings[0].equals(actual));

        byte[][] shares = new byte[numberOfShares][byteLength];
        for (int i=0; i<numberOfShares; i++) {
            shares[i] = (new BigInteger(bitStrings[i+1], 2)).toByteArray();
        }

        return shares;
    }

}
