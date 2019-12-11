package stopics;

import java.util.Random;

public class SecretShareEngine {

    class SharesAndCoeff {
        int[][] shares;
        int[] coeff;

        public SharesAndCoeff(int[][] shares, int[] coeff) {
            this.shares = shares;
            this.coeff = coeff;
        }
    }

    public SharesAndCoeff compute(int totalNumberOfShares, int totalNeedToReconstitute, int M, int[] xRange) {
        int from = xRange[0], to = xRange[1];
        int range = to - from;
        Random random = new Random();

        int[][] shares = new int[totalNumberOfShares][2];
        int noOfCoefficients = totalNeedToReconstitute - 1;
        int[] coeff = new int[noOfCoefficients];
        for (int i=0; i<noOfCoefficients; i++)
            coeff[i] = (int) (random.nextDouble() * 5) + 5;

        for (int i=0; i<totalNumberOfShares; i++) {
            int x = (int) (random.nextDouble() * range) + from;
            int y = M, degree=1;
            for (int j=0; j<noOfCoefficients; j++) {
                y += Math.pow(x, degree++) * coeff[j];
            }
            shares[i][0] = x;
            shares[i][1] = y;
        }

        return new SharesAndCoeff(shares, coeff);
    }

}
