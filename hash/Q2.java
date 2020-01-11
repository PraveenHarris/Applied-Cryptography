package hash;

import java.math.BigInteger;

public class Q2 {
    public static void main(String[] args) {
        /*
        Problem: Alice and Bob have the following RSA parameters:
          nA = 171024704183616109700818066925197841516671277
          eA = 1571
          pB = 98763457697834568934613
          qB = 8495789457893457345793
          eB = 87697
        Alice wants to send a message m to Bob. To ensure sender integrity,
        she signs the message iteself using her private key and obtains
        the signature s. (Side note: one typically signs the hash of the message,
        not the message iteself, but since her message is pretty short, this works too.)
        And to keep the contents of the message confidential, she encrypts both
        m and s using Bob's public key and sends the two ciphertexts m' and s' to Bob.
        He receives:
          m' = 418726553997094258577980055061305150940547956
          s' = 749142649641548101520133634736865752883277237
        Help him determine the message that Alice sent and assure him of its origin integrity.
        Simplified: Alice signs her message m using her private key to get signature s.
            She sends to Bob m and s encrypted with Bob's public key (m' and s').
            Only bob can decrypt m' and s', as they require his private key.
            Since s is encrypted with privA we can use pubA to decrypt it -> call this s0.
            We can verify the integrity by seeing if m == s0
         */

        // parse problem statement
        BigInteger nA = new BigInteger("171024704183616109700818066925197841516671277");
        BigInteger eA = new BigInteger("1571");
        BigInteger pB = new BigInteger("98763457697834568934613");
        BigInteger qB = new BigInteger("8495789457893457345793");
        BigInteger eB = new BigInteger("87697");
        BigInteger m_prime = new BigInteger("418726553997094258577980055061305150940547956");
        BigInteger s_prime = new BigInteger("749142649641548101520133634736865752883277237");

        // Idea: we need to find d
        BigInteger nB = pB.multiply(qB);
        BigInteger phiB = (pB.subtract(BigInteger.ONE)).multiply(qB.subtract(BigInteger.ONE));
        BigInteger dB = eB.modInverse(phiB);

        // decrypt m_prime and s_prime using privB
        BigInteger m = m_prime.modPow(dB, nB);
        BigInteger s = s_prime.modPow(dB, nB);
        System.out.println("m_prime after decrypting with privB:\t" + m);
        System.out.println("s_prime after decrypting with privB:\t" + s);
        System.out.println();

        // decrypt m using eA and nA
        BigInteger s0 = s.modPow(eA, nA);
        System.out.println("s_0 after decrypting with pubA:\t\t\t" + s0);
        System.out.println();

        System.out.println("Origin integrity verified by m == s0:\t" + m.equals(s0));
    }
}
