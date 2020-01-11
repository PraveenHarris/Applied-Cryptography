package hash;

import util.CryptoTools;
import util.MyTools;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

/**
 * Compute HMAC of a given message using key
 */
public class HMACEngine {

    // block sizes in bytes
    static final HashMap<String, Integer> blockSizes = new HashMap<>() {{
        put("SHA-1", 64);
        put("SHA-256", 64);
        put("SHA-512", 128);
        put("SHA3-256", 136);
        put("SHA3-512", 72);
    }};

    /**
     * Compute HMAC of a message using key
     *
     * @param m         message
     * @param key       key
     * @param algorithm hashing algorithm
     * @return computed HMAC of the message
     * @throws NoSuchAlgorithmException when the given algorithm is not supported
     */
    public static byte[] computeHMAC(byte[] m, byte[] key, String algorithm) throws NoSuchAlgorithmException {
        byte[] opad = CryptoTools.hexToBytes("5c");
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
        byte[] xor_outer = MyTools.XORByteArrays(key, opad);
        byte[] xor_inner = MyTools.XORByteArrays(key, ipad);

        byte[] h1 = digest.digest(MyTools.concatenateByteArrays(xor_inner, m));
        byte[] h2 = digest.digest(MyTools.concatenateByteArrays(xor_outer, h1));

        return h2;
    }

}
