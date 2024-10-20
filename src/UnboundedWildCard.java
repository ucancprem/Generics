import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class UnboundedWildCard {

    /**
     * You can read values out of it, not write to it.
     *
     * @param args
     */
    public static void main(String[] args) {
        // `?` -> I don't know what the type is/unknown type
        List<?> wildCardList = Arrays.asList("1", 2, LocalDate.now());

        //With an unknown type you can read values in it with below caveats
        System.out.println(""+wildCardList.size());
        wildCardList.forEach(System.out::println);

        //BUT NEVER write to it - because the type of the value to put in is.. unknown
        // wildCardList.add(3); ->'add(capture<?>)' in 'java.util.List' cannot be applied to '(int)'
        //wildCardList.add("abc"); - 'add(capture<?>)' in 'java.util.List' cannot be applied to '(String)'

        // We are able to call with methods below because they either belong to the
        // `List` interface/its parent class like the object class that DON'T need the contained type.
        System.out.println(""+wildCardList.isEmpty());
        wildCardList.forEach(System.out::println);


        //Here are good use cases of wildcards in java
        /*
            Takes collection of unknown Type and tells us if the element is present or not.
            boolean containsAll(Collection<?> c);
         */

        /*
            This is implemented in AbstractCollection as so.
            public boolean containsAll(Collection<?> c) {
                for (Object e : c)
                    if (!contains(e))
                     return false;
                return true;
            }

            Above delegates calls to `contains` method - implementation below
          */

        /*
            It does need the type, and uses the Object class' equals method(reference)
            to determine if the object is present or not.

            public boolean contains(Object o) {
                Iterator<E> it = iterator();
                if (o==null) {
                    while (it.hasNext())
                        if (it.next()==null)
                            return true;
                } else {
                    while (it.hasNext())
                        if (o.equals(it.next()))
                            return true;
                }
                return false;
            }

            All needed methods are from object or AbstractConnection itself that does not need underlying type
        */


        //This only lets us use methods in that collection(only on list object) or the Object class itself
        //SEE UpperBoundWildCards and LowerBoundWildCards on how to circumvent the shortcomings of this.


    }
}
