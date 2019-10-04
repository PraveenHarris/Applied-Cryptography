package foundation;

import util.CryptoTools;
import util.MyTools;


public class C_Exhaustive {

    public static void main(String[] args) throws Exception {

        // read ciphertext
        byte[] ct =  CryptoTools.fileToBytes("C:\\Users\\prave\\Documents\\York University\\Y3S1\\Applied Cryptography\\Code\\src\\data\\C_Exhaustive.ct");

        // print ct
        System.out.println(new String(ct));

        int mostOptimalShift = 0;
        double greatestDotProduct = 0;

        // try all 26 possible shifts
        for (int shift=0; shift < 26; shift++) {
            byte[] potentialPt = new byte[ct.length];

            // decrypt by shifting all bytes to the 'left'
            for (int i=0; i<ct.length; i++) {
                int asciiCode = (int) ct[i];
                byte decryptedChar = MyTools.shiftBy(asciiCode, shift, false);
                potentialPt[i] = decryptedChar;
            }

            // compute dot product using frequency vectors
            int[] ptFrequencies = CryptoTools.getFrequencies(potentialPt);
            double[] normalizedFrequencyVector = MyTools.normalizeFrequencyVector(ptFrequencies, potentialPt.length);
            double currentDotProduct = MyTools.computeDotProduct(normalizedFrequencyVector);

            // update highest dot product and optimal shift
            if (greatestDotProduct < currentDotProduct) {
                mostOptimalShift = shift;
                greatestDotProduct = currentDotProduct;
            }

            // print dot product for each shift
            System.out.printf("\nshift: %d\tdotProduct: %.2f", shift, currentDotProduct);
        }

        // print most optimal shift with its dot product
        System.out.printf("\n\nmostOptimalShift: %d\tgreatestDotProduct: %.2f\n", mostOptimalShift, greatestDotProduct);


        // decrypt ct and print pt
        byte[] pt = MyTools.shiftAllBy(ct, mostOptimalShift, false);
        System.out.println("\npt: " + new String(pt));

    }

}
