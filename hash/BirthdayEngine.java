package hash;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

public class BirthdayEngine {

    private static BigInteger factorial(BigInteger from, BigInteger to) {
        if (from.compareTo(to) < 0)
            return BigInteger.ONE;

        return from.multiply(factorial(from.subtract(BigInteger.ONE), to));
    }

    public static double computeActualProbability(int totalNumberOfDays, int nmberOfPeople) {
        /*
        Problem: Compute the probability of at least two people sharing the same birthdays
        Idea: P(at_least_2) = P(at_least_2) + P(at_least_3) + ...
        Easier way: P(at_least_2) = 1 - P(no_one_sharing_same_bday)
         */

        // parse variables to BigInteger
        BigInteger noOfDays = new BigInteger(String.valueOf(totalNumberOfDays));
        BigInteger noOfPeople = new BigInteger(String.valueOf(nmberOfPeople));

        // calculate P_prime
        BigInteger factorials = factorial(noOfDays, (noOfDays.subtract(noOfPeople)).add(BigInteger.ONE)); // +1 for the offset
        BigDecimal p_prime_numerator = new BigDecimal(factorials);
        BigDecimal p_prime_denominator = new BigDecimal((noOfDays).pow(nmberOfPeople));
        double p_prime = p_prime_numerator.divide(p_prime_denominator, MathContext.DECIMAL32).doubleValue();

        // actual probability
        double p = 1 - p_prime;
        return p;
    }

    public static double computeApproximation(int totalNumberOfDays, int numberOfPeople) {
        double exponentOfE = (-1.0 * Math.pow(numberOfPeople, 2)) / (2.0 * totalNumberOfDays);
        double approx = 1 - Math.pow(Math.E, exponentOfE);

        return approx;
    }

}























