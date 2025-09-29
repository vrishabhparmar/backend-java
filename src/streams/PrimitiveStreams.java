package streams;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class PrimitiveStreams {
    public static void main(String[] args) {
        int[] numbers = {1,2,3,4};
        IntStream stream = Arrays.stream(numbers);

        // boxed converts IntStream to Wrapper stream
        System.out.println(stream.boxed().collect(Collectors.toList()));

        DoubleStream doubles = new Random().doubles(5);
        System.out.println(doubles.boxed().toList());

    }
}
