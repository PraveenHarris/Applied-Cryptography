package test.TestWork.T1.T2;

import java.math.BigInteger;

public class Q21 {
    public static void main(String[] args) {
        /*
        Alice and Bob use RSA to communicate. One day, Alice wanted to send a message m to Bob so she encrypted it (without padding) using his public exponent e and modulus n and sent the obtained ciphertext ct to him. You need to find out the content of the message given only the information in this program fragment. The fragment gives you Bob's prime factors p and q, his public exponent e, and the received ciphertext ct.
         */

        // given information
        BigInteger p = new BigInteger("270208816742950846165867774432236299371");
        BigInteger q = new BigInteger("212339076166563026994707591649705499891");
        BigInteger e = new BigInteger("1031");
        BigInteger ct = new BigInteger("127833752397689537730867232484673025344739353166885012630590181083225230237");

        // find private exponent
        BigInteger n = p.multiply(q);
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        BigInteger d = e.modInverse(phi);

        // decrypt ciphertext
        BigInteger pt = ct.modPow(d, n);
        System.out.println(new String(pt.toByteArray()));

    }
}
