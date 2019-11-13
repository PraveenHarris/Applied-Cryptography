package hash;

public class Q5 {
    public static void main(String[] args) {
        int totalNumberOfDays = 365;
        int numberOfPeople = 23;

        double actualProbability = BirthdayEngine.computeActualProbability(totalNumberOfDays, numberOfPeople);
        System.out.println(actualProbability);

        double approximatedProbability = BirthdayEngine.computeApproximation(totalNumberOfDays, numberOfPeople);
        System.out.println(approximatedProbability);
    }
}
