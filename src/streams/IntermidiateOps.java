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

        // peek

        // flatmap
        // If we have a 2d list how to flatten it
        List<List<Integer>> d_list = Arrays.asList(Arrays.asList(1, 23, 4), Arrays.asList(1, 3, 4, 5));
        List<Integer> list1 = d_list.stream().flatMap(Collection::stream).toList();
        System.out.println(list1);

        List<String> sentences = Arrays.asList("I love Java", "Java is powerful");
        List<String[]> list2 = sentences.stream().map(item -> item.split(" ")).toList();
        Stream<String> stringStream1 = list2.stream().flatMap(Arrays::stream);
        stringStream1.forEach(System.out::println);

        // Primitive

    }
}
