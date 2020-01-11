package asymmetric;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;

/**
 * Asymmetric Engine used for public-key encryption/decryption
 */
public class AsymmetricEngine {

    /**
     * Decrypts ciphertext using d and n
     *
     * @param ct             ciphertext
     * @param n              public modulus
     * @param d              private key
     * @param transformation algorithm/modeOfOp/padding
     * @return decryption of the ciphertext in bytes
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static byte[] decrypt(BigInteger ct, BigInteger n, BigInteger d, String transformation) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        String[] algorithmSpecs = transformation.split("/");

        KeyFactory keyFactory = KeyFactory.getInstance(algorithmSpecs[0]);
        RSAPrivateKeySpec privateKeySpec = new RSAPrivateKeySpec(n, d);
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

        Cipher cipher = Cipher.getInstance(transformation);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] pt = cipher.doFinal(ct.toByteArray());

        return pt;
    }

}
