package asymmetric;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class Q1 {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        // Question: Determine the plaintext
        BigInteger n = new BigInteger(
                "945874683351289829816050197767812346183848578056570056860845622609107886220137" +
                        "220709264916908438536900712481301344278323249667285825328323632215422317870682" +
                        "037630270674000828353944598575250177072847684118190067762114937353265007829546" +
                        "21660256501187035611332577696332459049538105669711385995976912007767106063"
        );
        BigInteger e = new BigInteger("74327");
        BigInteger d = new BigInteger(
                "7289370196881601766768920490284861650464951706793000236386405648425161747775298" +
                        "3441046583933853592091262678338882236956093668440986552405421520173544428836766" +
                        "3419319185756836904299985444024205035318170370675348574916529512369448767695219" +
                        "8090537385200990850805837963871485320168470788328336240930212290450023"
        );
        BigInteger ct = new BigInteger(
                "8701485697571629912108508730957703831688317541285382011555129355623048840582638" +
                        "5706604303724175236985573832006395540199066061101502996745421485579743246846982" +
                        "6363174405058850929567231994074036320411089130186716135085720028980086157008585" +
                        "79079601105011909417884801902333329415712320494308682279897714456370814"
        );

        byte[] pt = AsymmetricEngine.decrypt(ct, n, d, "RSA/ECB/NoPadding");
        System.out.println(new String(pt));
    }
}
