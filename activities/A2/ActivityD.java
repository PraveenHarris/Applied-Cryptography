package activities.A2;

import symetric.SymmetricEngine;
import util.CryptoTools;
import util.Hex;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.util.Arrays;
import java.util.Base64;

public class ActivityD {
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        /* Compute the MAC of the message,
        using MD5 for the hash, AES no padding for the cipher,
        and the secret key shown in the same document.
        Express the MAC as a hex string and write it below.
        The Message: "No one can make you feel inferior without your consent."
        The Secret Key: "internationalfro"
         */
        String message = "No one can make you feel inferior without your consent.";
        String keyString = "internationalfro";

        // parse to bytes
        byte[] messageInBytes = message.getBytes();
        byte[] keyInBytes = keyString.getBytes();

        // hash message
        MessageDigest digest = MessageDigest.getInstance("MD5");
        byte[] hashedMessage = digest.digest(messageInBytes);

        // encrypt using secret key
        Key key = new SecretKeySpec(keyInBytes, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] mac = cipher.doFinal(hashedMessage);

        // print initial items
        System.out.println("Message:\t\t" + message);
        System.out.println("Key Hex:\t\t" + Hex.toString(keyInBytes));
        System.out.println("Hashed M. HEX:\t" + Hex.toString(hashedMessage));
        System.out.println("MAC:\t\t\t"  + Hex.toString(mac));
        System.out.println();

        // perform verification by: decryption of MAC == comparing hash of message received
        byte[] ver = SymmetricEngine.decrypt(mac, keyInBytes, null, "AES/ECB/NoPadding");
        System.out.println("Message is authentic? " + Arrays.equals(ver, digest.digest(message.getBytes())));


    }
}
