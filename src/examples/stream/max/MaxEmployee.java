package examples.stream.max;

import examples.Employee;
import examples.EmployeeGenerator;

import java.util.Comparator;
import java.util.List;

/**
 * Max method takes a java. util. Comparator<? super T> comparator
 * So, we can now use the Employee/the object class.
 *
 */
public class MaxEmployee {

    public static void main(String[] args) {
        List<Employee> employees = EmployeeGenerator.generateEmployees();

        Employee maxId = employees.stream()
                .max((Employee e1, Employee e2) -> e1.getId() - e2.getId())
                .orElse(Employee.DEFAULT_EMPLOYEE);

        System.out.println("Employee with Max ID "+maxId);

        Employee maxName = employees.stream()
                .max((Object o1, Object o2) -> o1.toString().compareTo(o2.toString()))
                .orElse(Employee.DEFAULT_EMPLOYEE);

        System.out.println("Employee with Max name "+maxName);

        Employee maxIDCComparingInt = employees.stream()
                .max(Comparator.comparingInt(Employee::getId)) //ToIntFunction<? super T> keyExtractor
                .orElse(Employee.DEFAULT_EMPLOYEE);
        System.out.println("Employee with Max name using Comparator.comparingInt "+maxIDCComparingInt);

        Employee maxIdComparingStr =  employees.stream()
                .max(Comparator.comparing(Object::toString)) // Takes Function<? super T, ? extends U> - Unlike ComparingInt,
                                                            // which only deals with Integer or T
                                                            // This handles two types String, Integer, i.e T, U
                .orElse(Employee.DEFAULT_EMPLOYEE);
        System.out.println("Employee with Max name using Comparator.comparing "+maxIdComparingStr);
    }

}
