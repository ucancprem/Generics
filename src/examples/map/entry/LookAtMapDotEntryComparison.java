package examples.map.entry;

import examples.Employee;
import examples.EmployeeGenerator;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LookAtMapDotEntryComparison {

    private static Map<Integer, Employee> createCacheOfEmployeeToLookupById(List<Employee> employees) {
        return employees.stream().collect(Collectors.toMap(Employee::getId, Function.identity()));
    }

    public static void main(String[] args) {
        List<Employee> employees = EmployeeGenerator.generateEmployees();

        Map<Integer, Employee> employeeByIdCache = createCacheOfEmployeeToLookupById(employees);
        // Below does not work because Employee does not extend Comparable, and therefore there exists no natural way to order
        // employeeByIdCache.entrySet().stream().sorted()
        //       .forEach( (entry ) -> System.out.println(entry.getKey()+" : "+entry.getValue()));
        System.out.println("------------------------------------------Sort by Id");
        employeeByIdCache.entrySet().stream().sorted(Map.Entry.comparingByKey())
                .forEach( employeeByIdEntry -> System.out.println(employeeByIdEntry.getKey()+":: "+employeeByIdEntry.getValue()));

        // <K extends Comparable<? super K>, V> Comparator<Map.Entry<K, V>> comparingByKey()
        //Because above is tyring to compare by the natural ordering of K - it has to extend Comparable of Type K or its ancestors
        //And as expected it returns Comparator<Map.Entry<K, V>>

        System.out.println("------------------------------------------Sort by Value(Emp.name)");

        //Similarly for comparingByValue, V should be implement Comparable to provide the natural ordering, but Employee object does not.
        // <K, V extends Comparable<? super V>> Comparator<Map.Entry<K, V>> comparingByValue()
        // Therefore you cannot just use comparingByValue() - compilation error -
        // `no instance(s) of type variable(s) V exist so that Employee conforms to Comparable<? super V>`

        //employeeByIdCache.entrySet().stream().sorted(Map.Entry.comparingByValue()) - does not work
        // but we can use another method that takes in a comparator to provide the ordering
        employeeByIdCache.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.comparing(Employee::getName)))
                .forEach( employeeByIdEntry -> System.out.println(employeeByIdEntry.getKey()+":: "+employeeByIdEntry.getValue()));



        System.out.println("------------------------------------------Sort by Id reversed");
        //If we want to use the reversed method on the Comparator object you will see a compilation error
        //'sorted(java.util.Comparator<? super java.util.Map.Entry<java.lang.Integer,examples.Employee>>)' in 'java.util.stream.Stream'
        // cannot be applied to '(java.util.Comparator<java.util.Map.Entry<K,java.lang.Object>>)'
        //We have lost the Generic Type Of V, due to type erasure
        //Workaround, pass in your own comparator to sort by reversed.

        //employeeByIdCache.entrySet().stream().sorted(Map.Entry.comparingByKey().reversed())
        employeeByIdCache.entrySet().stream().sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                .forEach( employeeByIdEntry -> System.out.println(employeeByIdEntry.getKey()+":: "+employeeByIdEntry.getValue()));

        System.out.println("------------------------------------------Sort by Value(Emp.name)");

        //same but reverse of name.
        employeeByIdCache.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.comparing(Employee::getName).reversed()))
                .forEach( employeeByIdEntry -> System.out.println(employeeByIdEntry.getKey()+":: "+employeeByIdEntry.getValue()));


    }

}
