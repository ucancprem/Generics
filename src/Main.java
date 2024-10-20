import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Map<String, Integer> stringlist =  Collections.emptyMap();
        stringlist.put("test", 1);
        System.out.println(stringlist);
    }
}