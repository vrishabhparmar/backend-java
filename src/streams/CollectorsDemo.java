package streams;

import java.util.*;
import java.util.stream.Collectors;

public class CollectorsDemo {
    public static void main(String[] args) {
        // Collectors is a utility class
        //provides a set of methods to create common collectors

        // Collecting to a List
        List<String> list = Arrays.asList("Alice", "Twinkle", "Yolo");
        List<String> a = list.stream().filter(name -> name.startsWith("A"))
                .collect(Collectors.toList());

        a.forEach(System.out::println);

        // Collecting to a Set
        List<Integer> list1 = Arrays.asList(1, 2, 3, 3, 4, 4, 4, 4, 5);
        Set<Integer> collect = list1.stream().collect(Collectors.toSet());
        collect.forEach(System.out::println);

        // Collecting to a specified Collection
        ArrayDeque<Integer> collect1 = list1.stream().collect(Collectors.toCollection(() -> new ArrayDeque<>()));

        // 4. Joining Strings
        String concated_string = list.stream().map(String::toUpperCase).collect(Collectors.joining(" "));
        System.out.println(concated_string);

        // Summarizing data
        // Generate statistical summary
        IntSummaryStatistics nums = list1.stream().collect(Collectors.summarizingInt(x -> x));
        System.out.println( nums.getAverage());
        System.out.println( nums.getMax());
        System.out.println( nums.getCount());
        System.out.println( nums.getMin());
        System.out.println( nums.getSum());

        // Generating Average
        Double avg = list1.stream().collect(Collectors.averagingInt(x -> x));
        System.out.println(avg);

        // Counting
        Long count = list1.stream().collect(Collectors.counting());
        System.out.println(count);

        // Grouping elements
        Map<Object, Long> map = list1.stream().collect(Collectors.groupingBy((x) -> x, Collectors.counting()));
        System.out.println(map);

        // Grouping elements using three parameters
        TreeMap<Integer, Long> treeMap = list1.stream()
                .collect(Collectors.groupingBy((x) -> x, TreeMap::new, Collectors.counting()));

        System.out.println(treeMap);

        // 9. Partitioning elements
        Map<Boolean, List<String>> partitioningBy = list.stream()
                .collect(Collectors.partitioningBy((x) -> x.length() > 5));

        System.out.println(partitioningBy);

        // 10. Mapping and collecting
        // applies a mapping function before collecting
        List<String> collect2 = list.stream().map(String::toUpperCase).collect(Collectors.toList());
        //List<String> collect2 = list.stream().collect(Collectors.mapping(String::toUpperCase, Collectors.toList()));
        System.out.println(collect2);

        // Example 1: Collecting names by length
        Map<Integer, List<String>> map1 = list.stream().collect(Collectors.groupingBy(String::length));
        System.out.println(map1);

        // Example 2: Counting word occurrences
        String sentence = "hello word hello java word";
        String[] s = sentence.split(" ");
        Map<String, Long> collect3 = Arrays.stream(s)
                .collect(Collectors.groupingBy(x -> x, Collectors.counting()));

        System.out.println(collect3);

        // Example3 : Partitioning Even and odd numbers
        List<Integer> list2 = Arrays.asList(1, 2, 3, 4, 5);
        Map<Boolean, List<Integer>> evenOdd = list2.stream().collect(Collectors.partitioningBy(x -> x % 2 == 0));
        System.out.println(evenOdd);

        // Example 4: Summing values in a map;
        Map<String, Integer> map3 = new HashMap<>();
        map3.put("Apple", 10);
        map3.put("Mongo", 30);
        map3.put("Banana", 20);

        Optional<Integer> reduce = map3.values().stream().reduce(Integer::sum);
        reduce.ifPresent(System.out::print);

        // creating a map from the stream
        List<String> fruits = Arrays.asList("Apple", "Banana", "Orange");
        Map<String, Integer> collect4 = fruits.stream()
                .collect(Collectors.toMap(String::toUpperCase, String::length));

        System.out.println(collect4);

        // Example 6: Counting
        List<String> words2 = Arrays.asList("apple", "banana", "apple", "yellow", "apple");
        Map<String, Long> collect5 = words2.stream().collect(Collectors.groupingBy(x -> x, Collectors.counting()));
        System.out.println(collect5);
        Map<String, Integer> collect6 = words2.stream().collect(Collectors.toMap(x -> x, v -> 1, Integer::sum));
        System.out.println(collect6);


    }
}
