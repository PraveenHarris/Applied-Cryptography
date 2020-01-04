package stopics;

import java.util.HashSet;
import java.util.Random;

/**
 * Generate shares to reconstitute a secret
 */
public class SecretShareEngine {

    /**
     * Custom data structure for holding x-y pairs of shares and coefficients
     */
    class SharesAndCoeff {
        int[][] shares;
        int[] coeff;

        public SharesAndCoeff(int[][] shares, int[] coeff) {
            this.shares = shares;
            this.coeff = coeff;
        }
    }

    /**
     * Generate x shares where a total of y shares are needed to constitute the secret
     * @param totalNumberOfShares number of shares requested
     * @param totalNeedToReconstitute number of shares required to reconstitute the secret
     * @param M secret
     * @param xRange the range generated shares (used for bitStrength number of digits in the share)
     * @return SharesAndCoeff object containing shares and coefficients for the secret
     */
    public SharesAndCoeff generateShares(int totalNumberOfShares, int totalNeedToReconstitute, int M, int modulus,  int[] xRange) {
        int from = xRange[0], to = xRange[1];
        int range = to - from;
        Random random = new Random();
        modulus = modulus==0 ? 100003 : modulus;

        int[][] shares = new int[totalNumberOfShares][2]; // (x,y)
        int noOfCoefficients = totalNeedToReconstitute - 1;
        int[] coeff = new int[noOfCoefficients];
        for (int i=0; i<noOfCoefficients; i++)
            coeff[i] = (int) (random.nextDouble() * 5) + 5;
        int[] xCord = getDistinctNumbers(totalNumberOfShares, xRange[0], range);

        for (int i=0; i<totalNumberOfShares; i++) {
            int degree=1, y = M;
            for (int j=0; j<noOfCoefficients; j++) {
                // y=..+bx^i+..
                y += Math.pow(xCord[i], degree++) * coeff[j];
            }
            shares[i][0] = xCord[i];
            shares[i][1] = y % modulus;
        }

        return new SharesAndCoeff(shares, coeff);
    }

    /**
     * Get distinct numbers within a range
     * @param total total numbers required
     * @param from starting from
     * @param range used for ceiling
     * @return array of distinct numbers
     */
    public static int[] getDistinctNumbers(int total, int from, int range) {
        Random random = new Random();
        int[] rtn = new int[total];
        HashSet<Integer> set = new HashSet<>();

        while (set.size() != total) {
            set.add((int) (random.nextDouble()*range) + from);
        }

        int i=0;
        for (int x: set) {
            rtn[i++] = x;
        }

        return rtn;
    }

}
