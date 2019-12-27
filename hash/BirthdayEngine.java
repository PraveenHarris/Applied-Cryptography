package hash;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

/**
 * Computing the probability of the birthday problem. Birthday Problem: given m number of people and n number of days, whats the probability that 2 of them share the same birthday.
 */
public class BirthdayEngine {

    /**
     * Computing the probability for the Birthday problem using factorials
     * @param totalNumberOfDays number of days in calendar
     * @param numberOfPeople number of people born on the same day
     * @return actual probability of m people born on th same day
     */
    public static double computeActualProbability(int totalNumberOfDays, int numberOfPeople) {
        /*
        Problem: Compute the probability of at least two people sharing the same birthdays
        Idea: P(at_least_2) = P(at_least_2) + P(at_least_3) + ...
        Easier way: P(at_least_2) = 1 - P(no_one_sharing_same_bday)
         */

        // parse variables to BigInteger
        BigInteger noOfDays = new BigInteger(String.valueOf(totalNumberOfDays));
        BigInteger noOfPeople = new BigInteger(String.valueOf(numberOfPeople));

        // calculate P_prime
        BigInteger factorials = factorial(noOfDays, (noOfDays.subtract(noOfPeople)).add(BigInteger.ONE)); // +1 for the offset
        BigDecimal p_prime_numerator = new BigDecimal(factorials);
        BigDecimal p_prime_denominator = new BigDecimal((noOfDays).pow(numberOfPeople));
        double p_prime = p_prime_numerator.divide(p_prime_denominator, MathContext.DECIMAL32).doubleValue();

        // actual probability
        double p = 1 - p_prime;
        return p;
    }

    /**
     * Approximating the birthday problem using Taylor series expansion of e
     * @param totalNumberOfDays number of days in calendar
     * @param numberOfPeople number of people born on the same day
     * @return approximated probability of m people born on th same day
     */
    public static double computeApproximation(int totalNumberOfDays, int numberOfPeople) {
        double exponentOfE = (-1.0 * Math.pow(numberOfPeople, 2)) / (2.0 * totalNumberOfDays);
        double approx = 1 - Math.pow(Math.E, exponentOfE);

        return approx;
    }

    /**
     * Compute factorial
     * @param from ending point
     * @param to starting point
     * @return computed factorial
     */
    private static BigInteger factorial(BigInteger from, BigInteger to) {
        if (from.compareTo(to) < 0)
            return BigInteger.ONE;

        return from.multiply(factorial(from.subtract(BigInteger.ONE), to));
    }

}
