package streams;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ParallerStreams {
    public static void main(String[] args) {

        List<Integer> list = Stream.iterate(1, x -> x + 1).limit(20000).toList();

        long start = System.currentTimeMillis();

        List<Integer> list1 = list.stream().map(ParallerStreams::factorial).toList();

        long end = System.currentTimeMillis();

        System.out.println(end - start);


        // Using Parallel Streams
        start = System.currentTimeMillis();

        list1 = list.parallelStream().map(ParallerStreams::factorial).toList();

        end = System.currentTimeMillis();

        System.out.println(end - start);

        // Parallel streams are most effective for cpu intensive application or large datasets where tasks are independent
        // They may add an overhead for simple tasks or small datasets

        // Fibonacci
        List<Integer> list2 = Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1] ,t[0] + t[1]})
                .limit(10)
                .map(t -> t[0])
                .toList();

        //list2.forEach(System.out::println);

        // Factorial

        Optional<Integer> list3 = Stream.iterate(1, t -> t + 1)
                .limit(4).toList()
                .stream()
                .reduce( (a, b) -> a * b);

        list3.ifPresent(System.out::println);

        OptionalInt reduce = IntStream.range(1, 5).reduce((a, b) -> a * b);
        reduce.ifPresent(System.out::println);

        //Cumulative Sum
        int[] arr = new int[]{1,2,3,4,5};
        IntStream int_stream = Arrays.stream(arr);
        //int_stream.reduce(Integer::sum).ifPresent(System.out::println);
        final int[] sum = {0};
        int[] array = int_stream.map(x -> {
            int i = x + sum[0];
            sum[0] = i;
            return sum[0];
        }).toArray();

        Arrays.stream(array).forEach(System.out::println);



    }


    public static int factorial(int a)
    {
        int res = 1;
        for (int i = 1; i <= a; i++) {
            res *= i;
        }

        return res;
    }
}
