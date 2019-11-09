package activities.A2;

import asymetric.AsymmetricEngine;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class ActivityC {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        // info obtained from 'Activity C' web page copied and pasted
        BigInteger n = new BigInteger("5409301266464832194067606610393116131353013206399904476598236122013084637353040988327494627724161211224123683806133530016170756404616012882929965296330791");
        BigInteger e = new BigInteger("101");
        BigInteger d = new BigInteger("1017591327354770412745391342549200064313933177441566188666994914042065426828761478384191879643673598426455498221236174647316248635842037259429430802479909");
        BigInteger ct = new BigInteger("4866159444640288517355989180634952628731986599496712796560494907930538149558225422929993666717305787622831528520318222798833803021899340489193673868639937");

        // decrypt ciphertext
        byte[] pt = AsymmetricEngine.decrypt(ct, n, d, "RSA/ECB/NoPadding");

        System.out.println(new String(pt));
    }
}
