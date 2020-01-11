package symmetric;

import util.CryptoTools;
import util.MyTools;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class Q4 {

    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        /* YORK mode of operation
            C_0: E(K, bitComp(iv) xor P_0)
            C_1: E(K, bitComp(C_0) xor P_1)
            ...
            C_n-1: E(K, bitComp(C_n-2) xor P_n-1)
         */

        // given: ct, key, iv
        String ctHex = "437DBAB5607137A5CFC1031114634087";
        String keyHex = "6B79466F724D4F50";
        String ivHex = "6976466F724D4F50";
        byte[] ctBytes = CryptoTools.hexToBytes(ctHex);
        byte[] keyBytes = CryptoTools.hexToBytes(keyHex);
        byte[] ivBytes = CryptoTools.hexToBytes(ivHex);

        // plaintext
        byte[] ptBytes = new byte[ctBytes.length];

        // define Cipher that will perform the decryption
        String algo = "DES";
        String mode = "ECB";
        String padding = "NoPadding";
        String transformation = String.format("%s/%s/%s", algo, mode, padding);
        Cipher cipher = Cipher.getInstance(transformation);
        Key key = new SecretKeySpec(keyBytes, algo);
        cipher.init(Cipher.DECRYPT_MODE, key);

        // values for mode of operation
        final int DES_BLOCK_SIZE = 8; // in bytes
        byte[] ctBlock = new byte[DES_BLOCK_SIZE];
        byte[] ptBlock = null;
        byte[] prevCtBlock = new byte[DES_BLOCK_SIZE];
        System.arraycopy(ivBytes, 0, prevCtBlock, 0, DES_BLOCK_SIZE);

        // mode of operation of YORK
        for (int i = 0; i < ptBytes.length; i += DES_BLOCK_SIZE) {
            System.arraycopy(ctBytes, i, ctBlock, 0, DES_BLOCK_SIZE);

            byte[] temp = cipher.doFinal(ctBlock);
            byte[] binComp = MyTools.bitComplement(prevCtBlock);
            ptBlock = MyTools.XORByteArrays(temp, binComp);

            System.arraycopy(ptBlock, 0, ptBytes, i, DES_BLOCK_SIZE);
            System.arraycopy(ctBlock, 0, prevCtBlock, 0, DES_BLOCK_SIZE);

        }

        System.out.println("CT (in bytes): " + new String(ctBytes));
        System.out.println("The plaintext is: " + new String(ptBytes) + "\n");
    }

}
