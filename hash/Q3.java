package hash;

import util.CryptoTools;

import java.security.NoSuchAlgorithmException;

public class Q3 {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        // Problem: Compute the HMAC of the message using SHA1 (with the key=K)
        byte[] m = "Mainly cloudy with 40 percent chance of showers".getBytes();
        byte[] key = "This is an ultra-secret key".getBytes();
        byte[] hmac = HMACEngine.computeHMAC(m, key, "SHA-1");
        System.out.println(CryptoTools.bytesToHex(hmac));
    }
}
