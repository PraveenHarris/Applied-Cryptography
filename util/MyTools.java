package util;

import java.util.Base64;

public class MyTools {

    /**
     * Perform Caesar shift on a letter
     * @param asciiCode the ascii code of the letter
     * @param shift number of single shifts
     * @param clockwise true => encrypt, false => decrypt
     * @return byte shifted by value
     */
    public static byte shiftBy(int asciiCode, int shift, boolean clockwise) {
        if (clockwise) {
            return (byte) (Math.floorMod(asciiCode - 65 + shift, 26) + 65);
        } else {
            return (byte) (Math.floorMod(asciiCode - 65 - shift, 26) + 65);
        }
    }

    public static byte[] shiftAllBy(byte[] arr, int shift, boolean clockwise) {
        byte[] rtn = new byte[arr.length];
        for (int i = 0; i < arr.length; i++) {
            rtn[i] = shiftBy((int) arr[i], shift, clockwise);
        }

        return rtn;
    }

    public static double[] normalizeFrequencyVector(int[] vector, double initialLength) {
        double[] rtn = new double[vector.length];
        for (int i = 0; i < vector.length; i++) {
            rtn[i] = vector[i] / initialLength;
        }

        return rtn;
    }

    public static double[] normalizeFrequencyVector(int[] vector, int initialLength) {
        return normalizeFrequencyVector(vector, (double) initialLength);
    }

    public static byte[] bitComplement(byte[] byteArray) {
        byte[] rtn = new byte[byteArray.length];
        for (int i=0; i<byteArray.length; i++) {
            rtn[i] = (byte) ~byteArray[i];
        }

        return rtn;
    }

    public static byte[] XORByteArrays(byte[] a1, byte[] a2) {
        byte[] rtn = new byte[Integer.max(a1.length, a2.length)];


        for (int i=0; i<rtn.length; i++) {
            rtn[i] = (byte) (a1[i] ^ a2[i]);
        }

        return rtn;
    }

    /**
     * Find dot product of a frequency vector and the english frequency vector
     * @param vector to find dot product with against frequency vector
     * @return dot product
     */
    public static double computeDotProduct(double[] vector) {
        double dp = 0;
        for (int i=0; i<26; i++) {
            dp += vector[i] * CryptoTools.ENGLISH[i];
        }

        return dp;
    }

    public static double normalizeAndComputeDotProduct(String s) {
        int[] freq = CryptoTools.getFrequencies(s.getBytes());
        double[] normalized = normalizeFrequencyVector(freq, s.length());

        return MyTools.computeDotProduct(normalized);
    }

    public static double[] computeIC(String ct, int maxIterations) {
        double[] icValues = new double[maxIterations];
        for (int i=1; i<maxIterations; i++) {
            int counter = 0;
            for (int j=0; j<ct.length()-i; j++) {
                if (ct.charAt(j) == ct.charAt(j+i))
                    counter++;
            }

            icValues[i] = counter * 1.0 / ct.length();
        }

        return icValues;
    }

    public static String byteArrayToBinaryString(byte[] array) {
        StringBuilder rtn = new StringBuilder();
        for (byte b : array) {
            rtn.append(Integer.toBinaryString(b & 255 | 256).substring(1));
        }

        return rtn.toString();
    }


    public static byte[] concatenateByteArrays(byte[] b1, byte[] b2) {
        byte[] temp = b1;
        b1 = new byte[b1.length + b2.length];
        System.arraycopy(temp, 0, b1, 0, temp.length);
        System.arraycopy(b2, 0, b1, temp.length, b2.length);

        return b1;
    }
}

