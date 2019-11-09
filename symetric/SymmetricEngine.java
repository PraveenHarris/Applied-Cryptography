package symetric;

import util.CryptoTools;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class SymmetricEngine {
    public static byte[] decrypt(byte[] ct, byte[] k, byte[] iv, String transformation) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        String[] algorithmSpecs = transformation.split("/");

        Key key = new SecretKeySpec(k, algorithmSpecs[0]);
        Cipher cipher = Cipher.getInstance(transformation);
        if (iv == null) {
            cipher.init(Cipher.DECRYPT_MODE, key);
        }else {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);
        }
        byte[] pt = cipher.doFinal(ct);

        return pt;
    }

//    public static void main(String[] args) throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
//        byte[] keyBytes = CryptoTools.hexToBytes("9F0DCEDB322F3C6873F9256E01376BA4");
//        byte[] ivBytes = CryptoTools.hexToBytes("20FC19123087BF6CAC8D0F1254123004");
//        byte[] ct = CryptoTools.hexToBytes("F38ADBA8A7B4CC613578355032205D50");
//        System.out.println(new String(SymmetricEngine.decrypt(ct, keyBytes, ivBytes, "AES/CBC/PKCS5Padding")));
//
//        keyBytes = "DO NOT TELL EVE!".getBytes();
//        ivBytes = CryptoTools.hexToBytes("20FC19123087BF6CAC8D0F1254123004");
//        ct = CryptoTools.hexToBytes("3188073EA5DB3F5C05B6307B3595607135F5D4B22F2C3EB710AA31377F78B997");
//        System.out.println(new String(SymmetricEngine.decrypt(ct, keyBytes, ivBytes, "AES/CBC/PKCS5Padding")));
//
//        ct = CryptoTools.hexToBytes("734A59929CFBB9E6F64EEB71B4EB66420069E7910AE147F8BC308986E311262B");
//        keyBytes = "thisisthekeydude".getBytes();
//        System.out.println(new String(SymmetricEngine.decrypt(ct, keyBytes, null, "AES/ECB/PKCS5Padding")));
//
//    }
}
