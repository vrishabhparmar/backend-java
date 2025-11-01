package streams;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TerminalOps {
    public static void main(String[] args) {
        // 1. collect
        List<Integer> list = Arrays.asList(1, 23, 3, 4, 56);
        Stream<Integer> integerStream = list.stream().map(x -> x ^ 1);
        //List<Integer> collect = integerStream.collect(Collectors.toList());
        List<Integer> collect = integerStream.toList();

        // 2. for each
        collect.forEach(System.out::println);

        // 3. reduce --> Combines all the elements to produce a single value.
        Optional<Integer> reduce = collect.stream().reduce(Integer::sum);
        if (reduce.isPresent()) System.out.println(reduce.get()); // Option 1
        reduce.ifPresent(System.out::println); // Option 2

        // 4. count

        // 5. anyMatch, allMatch, noneMath
        boolean b = collect.stream().anyMatch(x -> x % 2 == 0); // any one
        System.out.println(b);

        boolean b1 = collect.stream().allMatch(x -> x % 2 == 0); // all matches until the condition is true
        System.out.println(b1);

        boolean b3 = collect.stream().noneMatch(x -> x % 2 == 0); // non matches
        System.out.println(b3);

        // 6. findFirst, findAny
        Optional<Integer> any = collect.stream().findAny();
        any.ifPresent(System.out::println);
    }
}
