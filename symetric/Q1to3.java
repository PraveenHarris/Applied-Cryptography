package symetric;

import util.CryptoTools;
import util.MyTools;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Q1to3 {

    public static void main(String[] args) throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        Q1();
        Q2();
        Q5();
    }

    private static void Q1() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        // Problem: Decrypt the ciphertext
        byte[] keyBytes = CryptoTools.hexToBytes("9F0DCEDB322F3C6873F9256E01376BA4");
        byte[] ivBytes = CryptoTools.hexToBytes("20FC19123087BF6CAC8D0F1254123004");
        byte[] ct = CryptoTools.hexToBytes("F38ADBA8A7B4CC613578355032205D50");

        byte[] ptBytes = SymmetricEngine.decrypt(ct, keyBytes, ivBytes, "AES/CBC/PKCS5Padding");

        System.out.println(new String(ptBytes));
    }

    private static void Q2() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        // Problem: Decrypt the ciphertext
        byte[] keyBytes = "DO NOT TELL EVE!".getBytes();
        byte[] ivBytes = CryptoTools.hexToBytes("20FC19123087BF6CAC8D0F1254123004");
        byte[] ct = CryptoTools.hexToBytes("3188073EA5DB3F5C05B6307B3595607135F5D4B22F2C3EB710AA31377F78B997");

        byte[] ptBytes = SymmetricEngine.decrypt(ct, keyBytes, ivBytes, "AES/CBC/PKCS5Padding");

        System.out.println(new String(ptBytes));
    }

    private static void Q5() throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        //Problem: Decrypt the ciphertext using scheme CT = E(~K, E(K, PT))
        String secretKeyASCII = "FACEBOOK";
        String ctHex = "8A9FF0E2CD27DA4DC7F0C810E73D0E3B3B27CA03762BAE85597995997E625BDF0FEC655994EDD4B0851D7955B3F66717A52F83D01D73ABD9C593DA8C8CCBB073BB19E78442D9AA6D13B307EC0E8EA191E6A21897A82F1A643DC3BE0E12854D01C6006AA1D0EB1B94CAC573908018F284";

        byte[] ct = CryptoTools.hexToBytes(ctHex);
        byte[] key = secretKeyASCII.getBytes();
        byte[] keyBitComplement = MyTools.bitComplement(key);

        byte[] p0 = SymmetricEngine.decrypt(ct, keyBitComplement, null, "DES/ECB/NoPadding");
        byte[] p1 = SymmetricEngine.decrypt(p0, key, null, "DES/ECB/NoPadding");

        System.out.println(new String(p1));
    }

}
