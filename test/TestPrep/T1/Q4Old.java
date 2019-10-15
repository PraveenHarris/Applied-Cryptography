package test.TestPrep.T1;

import util.Binary;
import util.CipherEngUtil;
import util.CryptoTools;
import util.SymCipherEng;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;

public class Q4Old {
    public static void main(String[] args) throws IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, InvalidKeyException {
        // ct, key, iv
        byte[] ctBytes = CryptoTools.hexToBytes("437DBAB5607137A5CFC1031114634087");
        byte[] keyBytes = CryptoTools.hexToBytes("6B79466F724D4F50");
        byte[] ivBytes = CryptoTools.hexToBytes("6976466F724D4F50");

        byte[] ptBytes = new byte[ctBytes.length];

        System.out.println(new String(ctBytes));

        SymCipherEng engine = new SymCipherEng(SymCipherEng.ALGO_SYM.DES, keyBytes, CipherEngUtil.OPMODE.ECB, CipherEngUtil.PADDING.NoPadding);

        final byte[] ctBlock = new byte[SymCipherEng.DES_BLOCK_SIZE];
        byte[] ptBlock = null;
        final byte[] prevCtBlock = new byte[SymCipherEng.DES_BLOCK_SIZE];
        System.arraycopy(ivBytes, 0, prevCtBlock, 0, SymCipherEng.DES_BLOCK_SIZE);

        for (int plaintextPos = 0; plaintextPos != ptBytes.length; plaintextPos += SymCipherEng.DES_BLOCK_SIZE) {
            // Take the next subset of the ciphertext of length DES block size (i.e., 8 bytes).
            System.arraycopy(ctBytes, plaintextPos, ctBlock, 0, SymCipherEng.DES_BLOCK_SIZE);
            /*
             * Decrypt the current ciphertext block and then xor it with the previous ciphertext block to get
             * the current plaintext block. Note that it is safe to use Binary.xorEquals since Binary.comp
             * returns a new array.
             */
            ptBlock = Binary.xorEquals(Binary.comp(prevCtBlock), engine.decrypt(ctBlock));
            // Copy the current plaintext block into the complete plaintext array.
            System.arraycopy(ptBlock, 0, ptBytes, plaintextPos, SymCipherEng.DES_BLOCK_SIZE);
            // Update the pointer to the previous ciphertext block.
            System.arraycopy(ctBlock, 0, prevCtBlock, 0, SymCipherEng.DES_BLOCK_SIZE);
        }

        // Print the plaintext.
        System.out.println("The plaintext is:\n" + new String(ptBytes) + "\n");



    }
}
