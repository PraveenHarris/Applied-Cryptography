package stopics;

public class Q10 {
    public static void main(String[] args) {
        int M = 44;
        int totalNumberOfShares = 5;
        int totalNeededToReconstitute = 3;
        SecretShareEngine.SharesAndCoeff sharesAndCoeff = (new SecretShareEngine()).compute(totalNumberOfShares,totalNeededToReconstitute, M, new int[]{30, 50});
        int[][] shares = sharesAndCoeff.shares;
        int[] coeff = sharesAndCoeff.coeff;

        System.out.println("M="+M);
        for (int i=0; i<coeff.length; i++) {
            System.out.printf("x^%d=%d\n", i+1, coeff[i]);
        }
        System.out.println();

        for (int i=0; i<totalNumberOfShares; i++) {
            System.out.printf("(%d, %d)\n", shares[i][0], shares[i][1]);
        }
    }
}
