package hash;

import util.Binary;
import util.CryptoTools;
import util.MyTools;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class HMACEngine {

    static final HashMap<String, Integer> blockSizes = new HashMap<>(){{
        // block sizes in bytes
        put("SHA-1", 64);
        put("SHA-256", 64);
        put("SHA-512", 128);
        put("SHA3-256", 136);
        put("SHA3-512", 72);
    }};

    public static byte[] computeHMAC(byte[] m, byte[] key, String algorithm) throws NoSuchAlgorithmException {
        byte[] opad = CryptoTools.hexToBytes( "5c");
        byte[] ipad = CryptoTools.hexToBytes("36");

        MessageDigest digest = MessageDigest.getInstance(algorithm);
        int blockSize = blockSizes.get(algorithm);

        // multiply pads n times
        opad = (new String(opad)).repeat(blockSize).getBytes();
        ipad = (new String(ipad)).repeat(blockSize).getBytes();

        int keyLength = key.length;
        if (keyLength > blockSizes.get(algorithm)) {
            // hash the key
            key = digest.digest(key);
        } else if (keyLength < blockSize) {
            // pad the key
            int deltaLength = blockSize - keyLength;
            byte[] deltaBytes = new byte[deltaLength];
            key = MyTools.concatenateByteArrays(key, deltaBytes);
        }

        // xor with key
        byte[] xor_outer = Binary.xor(key, opad);
        byte[] xor_inner = Binary.xor(key, ipad);

        byte[] h1 = digest.digest(MyTools.concatenateByteArrays(xor_inner, m));
        byte[] h2 = digest.digest(MyTools.concatenateByteArrays(xor_outer, h1));

        return h2;
    }

//    public static void main(String[] args) throws NoSuchAlgorithmException {
//        // Problem: Compute the HMAC of the message using SHA1 (with the key=K)
//        byte[] m =  "The secret key is a unique piece of information that is used to compute the HMAC and is known both by the sender and the receiver of the message. This key will vary in length depending on the algorithm that you use.".getBytes();
//        byte[] key = "This is an ultra-secret key".getBytes();
//        byte[] hmac = HMACEngine.computeHMAC(m, key, "SHA-256");
//        System.out.println(CryptoTools.bytesToHex(hmac));
//    }
}





















