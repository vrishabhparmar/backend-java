package streams;

import java.util.Arrays;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;

public class Java8Features {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Task());
        t1.start();

        // # Lambda Expression

        Thread t2 = new Thread(() -> {
            System.out.println("Using Lambda Expression");
        });

        t2.start();

        MathOperation sumOperation = Integer::sum;
        MathOperation subOperation = (a,b) -> a - b;
        sumOperation.operation(1,2);
        subOperation.operation(1,2);

        Runnable task =  () -> System.out.println("Runnable using a lambda expression");
        task.run();

        // # Predicate --> functional interface (Boolean values function)
        Predicate<Integer> isEven = a -> a % 2 == 0;
        System.out.println(isEven.test(3));

        // Starts with
        Predicate<String> doesWordStartsWith = (a) -> a.toLowerCase().startsWith("a");
        System.out.println(doesWordStartsWith.test("Alpha"));

        Predicate<String> doesWordEndWithA = (a) -> a.toLowerCase().endsWith("a");
        System.out.println(doesWordEndWithA.test("Alpha"));

        // Method channing for Predicate
        Predicate<String> and = doesWordEndWithA.and(doesWordStartsWith);

        System.out.println(and.test("Alphabet"));

        // # Function
        Function<Integer, Integer> doubleIt = x -> 2 * x;
        Function<Integer, Integer> tripleIt = x -> 3 * x;
        System.out.println(doubleIt.apply(2));
        System.out.println(doubleIt.andThen(tripleIt).apply(2));

        // # CONSUMER
        Consumer<Integer> print = System.out::println;
        print.accept(5);

        Consumer<List<Integer>> printList = (x ) -> {
            for( int a : x){
                System.out.println(a);
            }
        };

        List<Integer> list = Arrays.asList(2,3,4,5);
        printList.accept(list);

        // Supplier // Can be used to return a database connection
        Supplier<String> stringSupplier = () -> "Hello World";

        System.out.println(stringSupplier.get());

        // Combine Predicate, Function, Consumer, Suppler
        Predicate<Integer> check = x -> x % 2 == 0;
        Function<Integer, Integer> doubleNumber = x -> 2 * x;
        Consumer<Integer> printNum = System.out::println;
        Supplier<Integer> supplyNum = () -> 100;

        if(check.test(supplyNum.get()))
        {
            printNum.accept(doubleNumber.apply(supplyNum.get()));
        }

        // BiPredicate, BiConsumer, BiFunction
        BiPredicate<Integer, Integer> checkSumEven = (a,b) -> (a+b) % 2 == 0;
        BiConsumer<Integer, Integer> biConsumer = (x, y) -> {
            System.out.println(x);
            System.out.println(y);
        };

        BiFunction<String, String, Integer> length = (a,b) -> (a + b).length();

        // Unary Operator
        Function<Integer,Integer> add = (x) -> x *2;
        UnaryOperator<Integer> addUni = (x) -> x * 2; // when the return type and parameter type are similar

        BiPredicate<Integer, Integer> addAgain = (a,b) -> (a+b) % 2 == 0;
        BinaryOperator<Integer> addAgainBinary = Integer::sum;

        // Method Reference -- use method without invoking & in place of lambda expression
        List<String> students = Arrays.asList("Ram", "sham", "Ramesh");
        students.forEach( x -> System.out.println(x)); // Using Lambda expression
        students.forEach(System.out::println); // Using Method Reference

        // Constructor Reference
        List<String> list1 = Arrays.asList("A","B","C");
        list1.stream().map(m -> new MobilePhones(m)).collect(Collectors.toList());
        List<MobilePhones> mobilePhones = list1.stream().map(MobilePhones::new).collect(Collectors.toList());


    }

}

class MobilePhones {
    String name;
    public MobilePhones(String name)
    {
        this.name = name;
    }
}

class Task implements Runnable{

    @Override
    public void run(){
        System.out.println("Using normal Expression ");
    }
}

@FunctionalInterface
interface MathOperation{
    public int operation(int a, int b);
}

class Add implements MathOperation{
    public int operation(int a, int b){
        return a + b;
    }
}


