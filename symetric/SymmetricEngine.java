package symetric;

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

/**
 * Encryption engine used for symmetric key encryption/decryption
 */
public class SymmetricEngine {

    /**
     * Uses the key and IV to decrypt the ciphertext
     *
     * @param ct             ciphertext to be decrypted
     * @param k              key
     * @param iv             iv
     * @param transformation algorithm/modeOfOpp/padding
     * @return decryption of cipher text using transformation in bytes
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidAlgorithmParameterException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static byte[] decrypt(byte[] ct, byte[] k, byte[] iv, String transformation) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        String[] algorithmSpecs = transformation.split("/");

        Key key = new SecretKeySpec(k, algorithmSpecs[0]);
        Cipher cipher = Cipher.getInstance(transformation);
        if (iv == null) {
            cipher.init(Cipher.DECRYPT_MODE, key);
        } else {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);
        }
        byte[] pt = cipher.doFinal(ct);

        return pt;
    }

}
