package T1;

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

public class T12 {
    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        String key1 = "manifest";
        String key2Hex = "30313233343536373839616263646566";
        byte[] key2Bytes = CryptoTools.hexToBytes(key2Hex);
        String key2IVHex = "31323334353637383930313233343536";
        byte[] key2IVBytes = CryptoTools.hexToBytes(key2IVHex);
        String ct = "YYTFFZNZEHTFGSIEGXQPDJIDLXEHDTPFVFEIGEIPBRNWHMQFSOTTBSRPNDC";
        byte[] ctBytes = ct.getBytes();

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        Key key2 = new SecretKeySpec(key2Bytes, "AES");
        IvParameterSpec spec = new IvParameterSpec(key2IVBytes);
        cipher.init(Cipher.DECRYPT_MODE, key2, spec);
        byte[] pt0 = cipher.doFinal(ctBytes);


        /*
        If i had more time...
        we decrypt using AES/CBC/PKCS5 along with the IV, then we take the result and then decrypt using DES/ECB/NoPadding which will give us the plaintext.

        forseeable challenges overcoming the padding issue that is currently there with AES, some bytes to hex conversions (some encoding) which are trivial because of the Cryptotools + MyTools library
         */
    }
}
