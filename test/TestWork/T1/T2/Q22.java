package test.TestWork.T1.T2;

import util.CryptoTools;
import util.Hex;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Q22 {
    public static byte[] computeYMAC(byte[] x, byte[] k) throws NoSuchAlgorithmException {
        // concatenate value for SHA-1
        ByteBuffer buffer = ByteBuffer.allocate(k.length*2 + x.length);
        buffer.put(k);
        buffer.put(x);
        buffer.put(k);

        // hash the message
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        byte[] sha1Hash = digest.digest(buffer.array());

        return sha1Hash;
    }
    public static void main(String[] args) throws NoSuchAlgorithmException {
        /*
       (A)  Meet at 6:30 pm on 20.
       (B)  Buy 270 RY at MarketP.
       (C)  Temperature 28 in YYZ.
       (D)  Approach runway 24 SW.
         */

        String x = "Meet at 6:30 pm on 20.";
        byte[] k = CryptoTools.hexToBytes("52456BAF456AB5555DEFA4");

        byte[] hashLookingFor = CryptoTools.hexToBytes("288B8E5A8003773EFA3AE01BF0EF62E1F5A67240");
        byte[] computedYMAC = computeYMAC(x.getBytes(), k);

        System.out.println(Hex.toString(computedYMAC));
        System.out.println(Hex.toString(hashLookingFor));

        System.out.println(
                "Are the hashes the same? " +
                        CryptoTools.bytesToHex(computedYMAC).equals(CryptoTools.bytesToHex(hashLookingFor))
        );

    }
}
