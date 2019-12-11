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


    public static void main(String[] args) {
//        int M = 44;
//        int totalNumberOfShares = 5;
//        int totalNeededToReconstitute = 3;
//        SharesAndCoeff sharesAndCoeff = (new SecretShareEngine()).compute(totalNumberOfShares,totalNeededToReconstitute, M, new int[]{30, 50});
//        int[][] shares = sharesAndCoeff.shares;
//        int[] coeff = sharesAndCoeff.coeff;
//
//        System.out.println("M="+M);
//        for (int i=0; i<coeff.length; i++) {
//            System.out.printf("x^%d=%d\n", i+1, coeff[i]);
//        }
//        System.out.println();
//
//        for (int i=0; i<totalNumberOfShares; i++) {
//            System.out.printf("(%d, %d)\n", shares[i][0], shares[i][1]);
//        }
    }


}
