package T1;

import util.CryptoTools;
import util.MyTools;

import java.util.Base64;

public class T13 {
    public static void main(String[] args) {
        String ctHex = "180D1D00000416451B551D4E6F1A0D45741A041D5435040A";
        byte[] ctBytes = CryptoTools.hexToBytes(ctHex);
        String ct = Base64.getEncoder().encodeToString(ctBytes);

//        for (Byte b : ctBytes) {
//            System.out.print((char) (int) b);
//        }

        System.out.println(ct);

        int shiftf2s = 13; // shift back B + 13 = O
        int shiftf2x = 3; // shift back U + 3 = X

        StringBuilder ctBuilder = new StringBuilder(ct);
        ctBuilder.setCharAt(ct.length()-7, 'O');
        ctBuilder.setCharAt(ct.length()-5, 'X');

        ct = ctBuilder.toString();

        System.out.println(ctBuilder.toString());
        System.out.println();
        System.out.println(CryptoTools.bytesToHex(ct.getBytes()));

    }
}
