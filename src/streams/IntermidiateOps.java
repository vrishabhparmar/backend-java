package streams;

import java.util.Arrays;
import java.util.*;
import java.util.stream.*;

public class IntermidiateOps {
    public static void main(String[] args) {
        // 1. Filter
        List<String> list = Arrays.asList("Luffy", "Zoro", "Sanji", "Ussop", "Chopper", "Franky", "Brook", "Robin", "Nami");
        Stream<String> stream = list.stream().filter(x -> x.startsWith("L"));
        long count = stream.count();

        System.out.println(count);

        // 2. map
        Stream<String> stringStream = list.stream().map(String::toUpperCase);

        // Sorted
        Stream<String> sorted = stringStream.sorted((a,b) -> a.length() - b.length());
        sorted.forEach(System.out::println);

        // distinct

        Stream<String> sorted2 = list.stream().sorted((a,b) -> a.length() - b.length()).distinct();
        sorted2.forEach(System.out::println);


    }
}
