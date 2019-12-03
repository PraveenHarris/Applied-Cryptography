package test.TestWork.T1.T2;

import asymetric.MillerRabinTest;

import java.math.BigInteger;

public class Q23 {
    public static void main(String[] args) {
        // Problem: Can we find p and q?
        BigInteger n = new BigInteger("1886551174440596431363530829856821585399702388566818912805438570822614968272903435658873334130205328539");
        BigInteger e = new BigInteger("101");
        BigInteger d = new BigInteger("1494297959952947668406757043668665878753149067546266865968740955277419236413038416419087591492839525101");
        // p * q = n
        BigInteger p = new BigInteger("326381517916544711692805413548551072490161194041659956935074301284527633641460125602245968948166587590");
      System.out.println(MillerRabinTest.checkPrimality(n));
        System.out.println(n);
        BigInteger[] divide = n.divideAndRemainder(p);
        System.out.println("divide + remainder:" + divide[0] + " " + divide[1]);
//        (new MillerRabinTopDown(n)).test(BigInteger.TWO, true);

    }

}
