package examples;

import java.util.Arrays;
import java.util.List;

public class EmployeeGenerator {

    public static List<Employee> generateEmployees() {
        return Arrays.asList(
                new Employee(1, "First Emp"),
                new Employee(2, "Second Emp"),
                new Employee(3, "Third Emp"),
                new Employee(4, "Fourth Emp"),
                new Employee(5, "Fifth Emp"));
    }
}
