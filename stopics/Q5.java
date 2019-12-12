package stopics;

public class Q5 {
    public static void main(String[] args) {
        String dLog1 = DiscreteLogEngine.computeUsingDLogTable(2, 11, 3);
        System.out.println("(p=11) Compute the discrete logarithm of 3 to base 2 is 8? " + dLog1.equals("8"));
        String dLog2 = DiscreteLogEngine.computeUsingDLogTable(5, 11, 3);
        System.out.println("(p=11) Compute the discrete logarithm of 5 to base 2 is 2? " + dLog2.equals("2"));
        String dLog3 = DiscreteLogEngine.computeUsingDLogTable(15, 19, 6);
        System.out.println("(p=19) Compute the discrete logarithm of 3 to base 2 is 16? " + dLog3.equals("16"));


        System.out.println("---");
        System.out.println("2 is a primitive root of 11? " + DiscreteLogEngine.isPrimitiveRoot(2, 11));
        System.out.println("13 is a primitive root of 19? " + DiscreteLogEngine.isPrimitiveRoot(19, 13));
        System.out.println("14 is a primitive root of 19? " + DiscreteLogEngine.isPrimitiveRoot(19, 14));
        System.out.println("15 is a primitive root of 19? " + DiscreteLogEngine.isPrimitiveRoot(19, 15));
        System.out.println("16 is a primitive root of 19? " + DiscreteLogEngine.isPrimitiveRoot(19, 16));
        System.out.println("17 is a primitive root of 19? " + DiscreteLogEngine.isPrimitiveRoot(19, 17));
    }
}
