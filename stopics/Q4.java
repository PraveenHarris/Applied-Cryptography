package stopics;

import java.util.Random;

public class Q4 {
    /*
    Given: Alice's raw key, Polarization bases Alice used, Bob's basis, Eves's basis.
    a. Determine size and contents of the sifted keys
    b. How many bit values are shared between Alice and Bob
    c. How many bits in Eve's sifted key are correct
     */
    public static void main(String[] args) {
        // parse input
        String aliceRawKey = "0 0 1 0 0 1 0 1 1 1 0 0 1 0 1 1".replaceAll(" ", "");
        String polarizationBasis = "+ + + x x + + + x + x + + x + x".replaceAll(" ", "");
        String bobBasis = "+ + x + x + + x x + + x + + x +".replaceAll(" ", "");
        String eveBasis = " x . . . x . . . x . . . . . . .".replaceAll(" ", "");

        // Perform BB84
        String eveKeyString = getEveKeyString(aliceRawKey, polarizationBasis, eveBasis);
        String bobKeyString = getBobKeyString(polarizationBasis, eveBasis, bobBasis, aliceRawKey, eveKeyString);
        System.out.println("Alice raw key:\t" + aliceRawKey);
        System.out.println("Polar. basis:\t" + polarizationBasis);
        System.out.println("Eve basis:\t\t" + eveBasis);
        System.out.println("Eve sifted key:\t" + eveKeyString);
        System.out.println("Bob basis:\t\t" + bobBasis);
        System.out.println("Bob sifted key:\t" + bobKeyString);

        // A,B => size and contents of sifted key, how many bits are shared between Alice and Bob
        System.out.println("--------------------");
        StringBuilder aliceSiftedKey = new StringBuilder();
        StringBuilder bobSiftedKey = new StringBuilder();
        int bitsShared = 0;
        for (int i=0; i<polarizationBasis.length(); i++) {
            if (polarizationBasis.charAt(i) == bobBasis.charAt(i)) {
                aliceSiftedKey.append(aliceRawKey.charAt(i));
                bobSiftedKey.append(bobKeyString.charAt(i));
                if (aliceRawKey.charAt(i) == bobKeyString.charAt(i))
                    bitsShared++;
            }
        }
        System.out.println("Alice sifted key:\t" + aliceSiftedKey);
        System.out.println("Bob sifted key:\t\t" + bobSiftedKey);
        System.out.println("Size of sifted key:\t" + bobSiftedKey.length());
        System.out.println("--------------------");
        System.out.println("No of bits shared:\t" + bitsShared);

        // C => number of bits correct in Eve's sifted key
        System.out.println("--------------------");
        int bitsEveGuessedCorrect = 0;
        for (int i = 0; i < aliceRawKey.length(); i++) {
            if (eveKeyString.charAt(i) == aliceRawKey.charAt(i))
                bitsEveGuessedCorrect++;
        }
        System.out.println("No of bits Eve guessed correct: " + bitsEveGuessedCorrect);

    }

    public static String getEveKeyString(String aliceRawKey, String polarizationBasis, String eveBasis) {
        Random random = new Random();
        StringBuilder eveKeyString = new StringBuilder();
        for (int i=0; i<aliceRawKey.length(); i++) {
            char eveBase = eveBasis.charAt(i);
            if (eveBase == '.') {
                eveKeyString.append(".");
            } else {
                if (eveBase == polarizationBasis.charAt(i)) {
                    eveKeyString.append(aliceRawKey.charAt(i));
                } else {
                    eveKeyString.append(random.nextInt(2));
                }
            }
        }

        return eveKeyString.toString();
    }

    public static String getBobKeyString(String originalBasis, String eveBasis, String bobBasis, String rawKey, String eveKeyString) {
        Random random = new Random();
        StringBuilder bobKeyString = new StringBuilder();
        for (int i=0; i<originalBasis.length(); i++) {
            if (eveBasis.charAt(i) == '.') {
                // eve did not interfere

                if (originalBasis.charAt(i) == bobBasis.charAt(i)) {
                    bobKeyString.append(rawKey.charAt(i));
                } else {
                    bobKeyString.append(random.nextInt(2));
                }


            } else {
                // eve interfered

                if (eveBasis.charAt(i) == bobBasis.charAt(i)) {
                    bobKeyString.append(eveKeyString.charAt(i));
                } else {
                    bobKeyString.append(random.nextInt(2));
                }

            }
        }

        return bobKeyString.toString();
    }

    /*
    Output:
            Alice raw key:	0010010111001011
            Polar. basis:	+++xx+++x+x++x+x
            Eve basis:		x...x...x.......
            Eve sifted key:	1...0...1.......
            Bob basis:		++x+x++xx++x++x+
            Bob sifted key:	1010010011101110
            --------------------
            Alice sifted key:	00010111
            Bob sifted key:		10010111
            Size of sifted key:	8
            --------------------
            No of bits shared:	7
            --------------------
            No of bits Eve guessed correct: 2
     */
}
