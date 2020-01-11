package asymmetric;

import java.math.BigInteger;

public class Q4 {
    public static void main(String[] args) {
        /*
            Question:
                x mod 1055827021987 = 365944767426
                x mod 973491987203  = 698856040412

            Example:
                367 mod 15 = 7
                367 mod 31 = 26
         */
        BigInteger[][] p0 = {
                {new BigInteger("15"), new BigInteger("7")},
                {new BigInteger("31"), new BigInteger("26")}
        };
        BigInteger x0 = CRTMultiples.computeWhenXInLeftSide(p0);
//        System.out.println("Example (should return 367): " + x0);


        BigInteger[][] pairs = {
                {new BigInteger("1055827021987"), new BigInteger("365944767426")},
                {new BigInteger("973491987203"), new BigInteger("698856040412")}
        };
        BigInteger x = CRTMultiples.computeWhenXInLeftSide(pairs);
        System.out.println(x);

    }
}
