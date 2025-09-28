package streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.*;

public class JavaStreams {
    public static void main(String[] args) {
        // Java Streams is a feature of Java 8
        // it processes collections of data in a functional and declarative manner
        // Simplify Data Processing
        // Embrace Readability and Maintainability
        // Enable Easy Parallelism

        // define streams
        // a sequence of elements supporting functional and declarative programming

        List<Integer> numbers = Arrays.asList(1,2,3,4,5);
        System.out.println(numbers.stream().filter(x -> x % 2 == 0).count());

        // Creating Streams
        // 1. From Collections
        List<Integer> list = Arrays.asList(1,2,3,4,5);
        Stream<Integer> stream = list.stream();

        // 2. From Arrays
        Integer[] arr = {1,2,3,4,5};
        Stream<Integer> stream2 = Arrays.stream(arr);

        // 3. using Stream.of()
        Stream<Integer> stream3 = Stream.of(1,3,4,5);
        System.out.println(stream3);

        // 4. Infinite Streams
        Stream<Integer> generate = Stream.generate(() -> 1).limit(100);
        generate.forEach(System.out::print);
        Stream.iterate(1, x -> x + 1).limit(100).forEach(System.out::print);



        //
    }
}
