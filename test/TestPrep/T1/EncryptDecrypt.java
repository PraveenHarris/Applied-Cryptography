package test.TestPrep.T1;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class EncryptDecrypt {
    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        String pt = "ThisIsTheEndGirl";
        String k = "praveens";
        byte[] ptBytes = pt.getBytes();
        byte[] keyBytes = k.getBytes();

        Cipher c = Cipher.getInstance("DES/ECB/NoPadding");
        Key key = new SecretKeySpec(keyBytes, "DES");
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] ctBytes = c.doFinal(ptBytes);

        System.out.println("ct: " + Arrays.toString(ctBytes));

        c.init(Cipher.DECRYPT_MODE, key);
        System.out.println(new String(c.doFinal(ctBytes)));
    }
}
