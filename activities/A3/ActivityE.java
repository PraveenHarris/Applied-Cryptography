package activities.A3;

import symetric.SymmetricEngine;
import util.CryptoTools;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class ActivityE {
    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        // given
        BigInteger p = new BigInteger("1426978031065901624399459");  //prime modulus
        BigInteger g = new BigInteger("142983226354603241203899");   //primitive root
        BigInteger aX = new BigInteger("182090720176738428472735");  //Alice's DH private
        BigInteger bY = new BigInteger("927925155478651997585838"); //Bob's DH public
        String ct = "7E50BE075E0B8B84"; //The received DES/ECB/PKCS5Padding ciphertext 0x

        // find key
        BigInteger keyBI = bY.modPow(aX, p);
        byte[] fullKeyArray = keyBI.toByteArray();
        byte[] keyArray = new byte[8];
        System.arraycopy(fullKeyArray, 0, keyArray, 0, 8);

        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        Key key = new SecretKeySpec(keyArray, "DES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        System.out.println(new String(cipher.doFinal(CryptoTools.hexToBytes(ct))));

    }
}
