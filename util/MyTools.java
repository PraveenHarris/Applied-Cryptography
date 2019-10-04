package util;

public class MyTools {

    /**
     * Perform Caesar shift on a letter
     * @param asciiCode the ascii code of the letter
     * @param shift number of single shifts
     * @param clockwise true => encrypt, false => decrypt
     * @return
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

    public static double[] normalizeFrequencyVector(int[] vector, double norm) {
        double[] rtn = new double[vector.length];
        for (int i = 0; i < vector.length; i++) {
            rtn[i] = vector[i] / norm;
        }

        return rtn;
    }

    public static double[] normalizeFrequencyVector(int[] vector, int norm) {
        return normalizeFrequencyVector(vector, (double) norm);
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
}

