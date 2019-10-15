package A1;

import util.CryptoTools;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import static util.MyTools.bitComplement;

public class ActivityB {
    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

        // representation of ciphertext in hex and byte[]
        String ctInHex = "8BF21599B490AB61967FD9C0AD8F8A7BDA0456349B5A54BB0F71D7B17F74C0492FBBF825C345CF57BA5207A30D1F52350ACCA02FC6C6CE61FE556244B808A7BBA3DFE11A1E3E054517F608794DA725D314D1EF7FFD8444535D8A8EE7C5C2401EB0F86988E2DEB55FFEC773CC145E7CD8BDF4C5409A597DDD94701A228881C5F34090190359025FA44F46E9D654D6EE367B16FED4850DDD07D6FFC53C27B5CCFC8D4C9D2AF64BF332978B3EBD0CCA4DCA2491515FD489912D98E44F4DF2185DC361C551178126EFE652BB6FA3797CBFAB0885B65934F5E4DF2841369036951364A01FC9BB71168079129D7DF0CCEE0B955D71F99902CFC76685921DD68D69A46B1CE0A53B16D3ACA414E925B71447B4BB61C3F62E702F4647E8A034F522160DBCBAD25D13D758D875B4A903F8CEB6581546C250D3D78C37FA28413690369513649469763A4E2A273897B8FE03ADBEA4FE756340E8F147A9146C0DD2461DD1FEDB63D86F5977F4B7E0A1BA00BC60B0FCFB782D77FEBE138AA46D60F8B7954FEDBF8B9791C982F304A4A37297B585350B419852EFECCDEAB0EABA099161068B19855571BC9C3F4495E8790024D4DB7A5D68055F7C974AFAA9F627DD62236E80C4532F5E1E68927B2240FAB161DE19E3A05C2AE4E4F3F6B0A3B6FEC6E89143E263913DD86E0CF4DEE4C7AA0332474FDEF84C39141E59CC32B587379EF6E689EA4250350763E6C27E5C7D4E3A72C84F96EDA05365F5B50C0587402CF523FF6068F5CC9FB2A922D3EB73B5B7C39C135B05174726407DB75D7E59A5C7B042C50285C19BDAAD80CC8002DD34108C6484CEB34D5CBB561E49E5FF3C9A8C9F3277C1865CB0E54DE0D7C84B526F670554629A2BDE437E5B4FF18683172824E02A37ADC3C5E8484BC7CDAE020CD9541410277C941663FD7901FFA73859E982E9A5EE7E07BCF2FB2C064CFF58DD2005E546002535EE9FFF0DB86EFF5D1AB78707C6937FC066E15A230310EC5A2B8DBA53E500B0AA83FEC2BC1A16A913ACBE6837ABA2990E129A3ED29A1B2CF58529A55BA31F857C994792B4F8E060E9881159491707D0A5CF196C472DE8F51157CF5DB4C71A46B984D5D6568C5FB2B9A29135363DADEFA3E9ACBD9AED981349521AF281B844E6B031929F4BB03D69506154D7B94ACFDE8764E8B4BB54EC09569FA5C76071A9652F02261FAC6CDA10E5B0E50E9E1768A4949FACB858A6327E837737BB4C269D47E26400E2A922F66F723AA38A6C73EF26BC575DFB9FC89D24C6C748D36FE35230868FD4E29F3E0FB58D2F5439A0FB0580EE998E5E10F48BEA00E4FA5C41E43F6AF18DB23EA27CB6CFD1023F1C8788DA1C24CC940DEB6EAB06D0565F03F4CCE5E02E9594B49EA2B4870351A3F48EC5A4D291BB37C929C5D313A72B9B2C2C15FD0FC3DDD75BD0495DF8F209D5454234AE70E89D59F2E06DDC398F50C4551FEB5A944185041AACA31FC44D9C64185CDE399E2A1AB36BBB0DD8ADC1B0C6AD7E3678DD16CEF556A080A15972C57932501BFA5E90DA8A46A308AB4CD9933F12D49B2F860D1F61F833A2FCD105079BEFCD7DD6E6B8FDE3ADDE79E6C88604AD191DC75502CF1954";
        byte[] ctInBytes = CryptoTools.hexToBytes(ctInHex);
        System.out.println("CT: \n" + new String(ctInBytes) + "\n");

        // representation of second key in string and bytes[] & bitComp
        String keyInHex = "72656D656D626572";
        byte[] keyInBytes = CryptoTools.hexToBytes(keyInHex);
        System.out.println("KEY: " + new String(keyInBytes));
        System.out.println("keyInBytes: " + CryptoTools.bytesToBin(keyInBytes));
        byte[] keyBitComplement = bitComplement(keyInBytes);
        System.out.println("keyBitComplement: " + CryptoTools.bytesToBin(keyBitComplement));
        System.out.println();

        // decrypt second ct with bit complement of original key
        Key secret = new SecretKeySpec(keyBitComplement, "DES");
        Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, secret);
        byte[] pt0 = cipher.doFinal(ctInBytes);

        // print value of pt0: intermediary pt
        System.out.println("pt0: \n" + new String(pt0));

        // decrypt first ct with original key
        secret = new SecretKeySpec(keyInBytes, "DES");
        cipher.init(Cipher.DECRYPT_MODE, secret);
        byte[] pt1 = cipher.doFinal(pt0);

        // print value of pt1: original PT
        System.out.println("\n" + new String(pt1));

    }

}