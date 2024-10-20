import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LowerBoundWildCards {


    private static void checkType(List<? super Integer> intLists) {
        intLists.forEach(LowerBoundWildCards::defaultType);
    }

    private static void defaultType(Object object) {
        System.out.println("Default/available type of stream elements is  the superType(similar to upperbound, it always the superType)" +
                " Of Integer i.e Object "+ object.hashCode());
        System.out.println("But we can still check for instance of and perform operations on subTypes ");
        switch (object) {
            case Integer i -> System.out.println("Integer -  int value "+ i);
            case Number n -> System.out.println("Number -  double value "+n.doubleValue());
            case Object o -> System.out.println("Object hashcode "+ o.hashCode());
        }
    }

    public static void main(String[] args) {

        List<String> strList = Stream.of("a", "b", "c")
                .toList();

        List<Integer> intList = List.of(1,2,3);
        List<Number> numList = List.of(1,2,3);
        List<Object> objectList = List.of(1,2,3);
        checkType(intList);
        checkType(numList);
        checkType(objectList);


        //ForEach method takes Consumer of <? super T> , so T or super class of T.
        strList.forEach(s -> System.out.println("we can call methods of String of course"+s.toUpperCase()));
        strList.forEach((Object o) -> System.out.println("we can call methods of String's super class "+o.hashCode()));


        List<Integer> collectedlist =  Stream.of(1,2,3,4)
                //peek takes Consumer<? super T> action -
                //so any type of T in numbList or its super classes
                .peek( i -> System.out.println("Binary string"+Integer.toBinaryString(i)))
                .peek( (Number n) -> System.out.println("double value string"+n.doubleValue()))
                .peek( (Object o) -> System.out.println("Binary string"+o.hashCode()))
                .toList();

        System.out.println(collectedlist);
        //Both collections are providing values to the Consumer to use super
        //Remember the acronym/mnemonic PECS -> producer extends; consumer super.
    }
}
