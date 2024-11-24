package examples;

public class Employee {

    public static final Employee DEFAULT_EMPLOYEE = new Employee(0, "DEFAULT ROBOT");

    private final int id;
    private final String name;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
