package week1;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.util.concurrent.atomic.AtomicInteger;

class Counter{
    private int count = 0;
    public  void increment(){
        count++;
    }
    public int getCount(){
        return count;
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        final int x;
        x = 10;

        Counter counter = new Counter();
        Thread t1 = new Thread( () -> {
           for(int i = 0; i < 10000; i++)
           {
               counter.increment();
           }
        });

        Thread t2 = new Thread( () -> {
            for(int i = 0; i < 10000; i++)
            {
                counter.increment();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(counter.getCount());

    }

}