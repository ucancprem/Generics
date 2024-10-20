import java.math.BigDecimal;
import java.util.List;

public class UpperBoundWildCards {

    private static double addNumbers(List<? extends Number> numberList) {
        return numberList.stream().mapToDouble(Number::doubleValue).sum();
    }

    private static void checkType(List<? extends Number> numberList){
        numberList.forEach(UpperBoundWildCards::defaultType);
    }

    private static void defaultType(Number number) {
        System.out.println("Default/available type of stream elements is the superType i.e. number"+ number.doubleValue());
    }

    public static void main(String[] args) {
        //So, how do you solve the problem of UnboundedWildcards?  - by having a bound
        //Lets take a look at UpperBound

        //List<? extends Number> numberList= List.of(1,2,3,5);
        // numberList.add(1); -> We still cannot add to the list
        // But as shown by this method, now we can accept everything that extends the upperbound
        // We can still only use methods from List/Number.

        List<Number> numList = List.of(1,2,3,5);
        List<Integer> intList = List.of(1,2,3,5);
        List<Double> dobuleList = List.of(1.0,2.0,3.0,5.0);
        List<BigDecimal> bigDecimalList = List.of(new BigDecimal(1),
                new BigDecimal(2),
                new BigDecimal(3),
                new BigDecimal(5));

        System.out.println(addNumbers(numList));
        System.out.println(addNumbers(dobuleList));
        System.out.println(addNumbers(intList));
        System.out.println(addNumbers(bigDecimalList));
        //Java Generics are covariant when they use extends with a wildcard.

        checkType(dobuleList);

    }
}
