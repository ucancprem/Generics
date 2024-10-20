import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InheritanceGen {

    private static double calculateSum(List<Number> nums){
        return nums.stream().mapToDouble(Number::doubleValue).sum();
    }
    
    public static void main(String[] args) {
        List<String> strList = new ArrayList<>();
        List<Object> objList = new ArrayList<>();

       // objList = strList; DOES NOT COMPILE
        // Lets say that was possible; Well in that case it means
        // the objList and strList both point to the same reference or location.
        // so something like below would be okay
        objList.add(3);

        //resulting in a strList containing an object.
        // And below would throw a ClassCastException.
        String result = strList.get(0);

        //ThereFore if we we use a SuperClassType, you can end up messing up the subclass type

        //But lets say we had number like below, it should be possible for us to call
        //`calculateSum` method above for all lists below. But we cannot. Due to above.
        //So, we cannot invoke `calculateSum` on anything other than numList
        List<Number> numList = Arrays.asList(3,1,1,5,9);
        calculateSum(numList);
        List<Integer> intList = Arrays.asList(3,1,1,5,9);
        //calculateSum(intList);
        List<Double> doubleList = Arrays.asList(3.0,1.0);
        //calculateSum(doubleList);

        //This is where WildCards help us. See <EnterNextClass>

    }
}
