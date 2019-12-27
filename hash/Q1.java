package hash;

import util.Hex;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class Q1 {
    public static void main(String[] args) throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, InvalidKeySpecException, NoSuchPaddingException {
        /*
        Problem: Your RSA keys are as follows, use them to sign the message using SHA-512,
        show that if you send the message (in clear plaintext) and the signature to Alice,
        then she can indeed verify that it came from you and that its content is intact.
         */
        BigInteger n = new BigInteger(
                "945874683351289829816050197767812346183848578056570056860845622609107886220137" +
        "220709264916908438536900712481301344278323249667285825328323632215422317870682" +
        "037630270674000828353944598575250177072847684118190067762114937353265007829546" +
        "21660256501187035611332577696332459049538105669711385995976912007767106063");
        BigInteger e = new BigInteger("74327");
        BigInteger d = new BigInteger(
                "7289370196881601766768920490284861650464951706793000236386405648425161747775298" +
        "3441046583933853592091262678338882236956093668440986552405421520173544428836766" +
        "3419319185756836904299985444024205035318170370675348574916529512369448767695219" +
        "8090537385200990850805837963871485320168470788328336240930212290450023");
        String message = "Meet me at 5 pm tomorrow";

        // has the message
        MessageDigest digest = MessageDigest.getInstance("SHA-512");
        byte[] hashedMessage = digest.digest(message.getBytes());
        BigInteger encryptedMessage = (new BigInteger(hashedMessage)).modPow(d, n);
        byte[] signature = encryptedMessage.toByteArray();

        System.out.println("Orig. Mess.:\t"  + message);
        System.out.println("Enc. Mess.:\t\t" + encryptedMessage);
        System.out.println("Signature 0x:\t" + Hex.toString(signature));
        System.out.println();

        // Alice receives: message and signature
        BigInteger decryptedMessage = (new BigInteger(signature)).modPow(e, n);
        System.out.println("Hashed Mess.:\t" + Hex.toString(digest.digest(message.getBytes())));
        System.out.println("Dec. Mess.:\t\t" + Hex.toString(decryptedMessage.toByteArray()));

        /*
        I hashed the message and then encrypted it using my private exponent. Alice can know that
        I sent this message by decrypting it with my public exponent, because only I know
        the corresponding private exponent which I used to encrypt.
        Alice can verify that the message hasn't been tampered with by comparing the {hash of the message}
        with the {decrypted value of the encrypted message}.
         */
    }
}


















