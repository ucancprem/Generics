package examples.stream.max;

import examples.Employee;
import examples.EmployeeGenerator;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LookingAtCollectorsToMap {

    public static void main(String[] args) {
        List<Employee> employees = EmployeeGenerator.generateEmployees();

        Map<Integer, Employee> employeeMapByIds = employees.stream().
                collect(Collectors.toMap(Employee::getId, Function.identity()));


        /**
         * Lets look the signature
         * public static <T, K, U>
         *     Collector<T, ?, Map<K,U>> toMap(Function<? super T, ? extends K> keyMapper,
         *                                     Function<? super T, ? extends U> valueMapper)
         *
         *  <T, K, U> -> declaration of types used by methods since they're not declared at the class level
         *  KeyMapper, valueMapper are straightforward -> They take type T(Employee) and return K, U respectively,
         *  which are Integer, Employee in this case.

         *
         *  And the return type is more interesting - It returns -> not a map, but a Collector.
         *  A Collector that takes T, and returns a Map<K,U>>. and it uses ? - unbounded type - so only methods
         *  on Collectors/Objects can be used on them and it is used as the intermediate accumulation type of the Collector
         */

        System.out.println(employeeMapByIds);
    }
}
