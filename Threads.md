### Multithreading

        Multuthreading is the ability of the CPU to perform multiple tasks concurrently. 

### Concurrency Vs Parallelism

Concurrency is when multiple tasks can run in overlapping periods. It's an illusion of multiple tasks running in parallel because of a very fast switching by the CPU

Parallelism is a type of computation in which multiple processors carry out many processes at the same time.

### Process vs Threads

### Time slicing Algo

### Pros and Cons of Multithreading

### Thread life cycle

### Runnable interface

### Extend thread class

### Implementing Runnable vs Extend Thread class. Which is better?

### Join() Method

### Daemon thread

### Thread Priority

### Thread Synchronization

### Problems with Synchronization

### Wait and notify

### Producer and Consumer

The producer-consumer problem is a synchronization scenario where one or more producer threads generate data and put it into a shared buffer, while one or more
consumer threads retrieve and process the data from the buffer concurrently.

```Java

import java.util.ArrayList;
import java.util.List;
import week1.*;

public class ProducerConsumer {

    public static void main(String[] args)
    {
        Worker worker = new Worker(5,0);

        Thread producer = new Thread(() -> {
            try {
                worker.produce();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread consumer = new Thread(() -> {
            try{
                worker.consume();
            }
            catch (InterruptedException e){
                throw new RuntimeException();
            }
        });

        producer.start();
        consumer.start();
    }
}

class Worker{

    private int sequence = 0;
    private final Integer top;
    private final Integer bottom;
    private final List<Integer> container;
    private final Object lock = new Object();

    public Worker(Integer top, Integer bottom){
        this.top = top;
        this.bottom = bottom;
        this.container = new ArrayList<>();
    }

    public void produce() throws InterruptedException {
        synchronized (lock)
        {
            try{
               while(true)
               {
                  if(container.size() == top){
                     System.out.println("Container is full! waiting for the items to be removed");
                     lock.wait();
                  }
                  else {
                     System.out.println(sequence + " added to the container");
                     container.add(sequence++);
                     lock.notify();
                  }

                  Thread.sleep(500);
               }
            }
            catch (Exception e){
               System.out.println(e.getMessage());
            }
            
        }
    }
    public void consume() throws InterruptedException{
        synchronized (lock){
            while(true)
            {
                if(container.size() == bottom){
                    System.out.println("Container is empty! waiting for the items to be added");
                    lock.wait();
                }
                else {
                    System.out.println(container.removeFirst() + " Removed from the container");

                    lock.notify();
                }

                Thread.sleep(500);
            }
        }
    }
}


```

### Executor service

      ExecutorService is a part of Java Concurrency framework. It manages and executes threads in the background using a thread pool - making a multithreading simpler and more efficient.

#### Why Use ExecutorService?

1. Avoid manual creating and managing threads
2. Reuses threads
3. Supports Runnable and Callable
4. Returns results via Future


### SingleThreadExecutor

```Java

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadExecutorDemo {

    public static void main(String[] args)
    {
        try(ExecutorService service = Executors.newSingleThreadExecutor()){
            for (int i = 0; i < 5; i++) {
                service.execute(new Task(i));
            }
        }
    }
}

class Task implements Runnable{

    private final int taskId;

    public Task(int taskId)
    {
        this.taskId = taskId;
    }

    @Override
    public void run()
    {
        System.out.println("Task with ID " + taskId + " bieng executed by Thread " + Thread.currentThread().getName());
        try{
            Thread.sleep(500);
        }
        catch (InterruptedException e)
        {

        }
    }
}


```

### Fixed Thread Pool

```Java

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolDemo {

    public static void main(String[] args){
        try(ExecutorService service = Executors.newFixedThreadPool(2)){
            for (int i = 0; i < 5; i++) {
                service.execute(new Work(i));
            }
        }
    }
}

class Work implements Runnable{
    private final int workId;

    public Work(int workId){
        this.workId = workId;
    }

    @Override
    public void run() {
        System.out.println("Task ID " + workId + " being executed by thread " + Thread.currentThread().getName());

        try{
            Thread.sleep(500);
        }
        catch(InterruptedException e){
            throw new RuntimeException(e);
        }
    }
}


```

### Cached Thread Pool Executor

Cached Thread Pool is auto-scaling in nature. It keeps generating new threads for bew tasks. It has a upper limit to which the number of
threads can be generated. If the thread is idle for more than 60 sec, its killed.

### Scheduled Executor

```Java

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorDemo {
    public static void main(String[] args) {
        try (ScheduledExecutorService service = Executors.newScheduledThreadPool(1)) {

            service.scheduleAtFixedRate(new ProbeTask(), 1000, 2000, TimeUnit.MILLISECONDS);

            try {
                if (service.awaitTermination(10000, TimeUnit.MILLISECONDS)) {
                    service.shutdownNow();
                }
            } catch (InterruptedException e) {
                service.shutdownNow();
            }
        }
    }
}

class ProbeTask implements Runnable{
    @Override
    public void run() {
        System.out.println("Probing end point for updates");
    }
}


```

### What if we want a return value from a thread.

In case if Runnable interface the run method doesn't have any return type.

```Java

import java.util.concurrent.*;

public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        try (ExecutorService executorService = Executors.newFixedThreadPool(2)) {

            Future<Integer> future = executorService.submit(new ReturnValueTask());
            System.out.println(future.get());
        }

    }

}

class ReturnValueTask implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        return 0;
    }
}


```

### Concurrent Collections

Most of the java collections (for ex. ArrayList) are not thread safe. In order to make collections thread safe. Use Collections.synchronize() method.
we can also use the collections which are synchronized.

Downside of using the Collections.synchronized() approach

1. Coarse grained locking: Means using a one big lock to control access to large portion of code or shared resource - often a whole object.
   Advantages are that it is easy to implement and avoids race condition. but the disadvantages are that only one thread can access any part of the object. Even if thread doesn't touch the same data they're blocked.

2. Limited Functionality
3. No fail fast iterator
4. performance overhead

### Count down latch
CountDownLatch is a synchronization aid that allows one or more thread to wait until a set of operations are completed

Think of this like a gate that stays closed until the count reaches zero, and then opens to let waiting thread continue.

#### When to us Count down latch?

When you many independent threads working on different tasks, and you want each one of them to follow a sequence of one after the other.
You can use the CountDownLatch so that the thread can notify using countDown() that they are done executing there part of process.

#### Is the functionality similar to join?

No it is not. We use join when we want the main thread which form the worker thread to complete their task.

#### Can we reset the count?

No.

```Java

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);

        // Worker Threads
        for (int i = 1; i <= 3; i++) {
            final int id = i;
            new Thread(() -> {
                System.out.println("Worker " + id + " started");
                try {
                    Thread.sleep(1000 * id); // simulate work
                } catch (InterruptedException e) {}
                System.out.println("Worker " + id + " finished");
                latch.countDown(); // reduce latch count
            }).start();
        }

        // Main thread waits
        System.out.println("Main thread waiting...");
        latch.await(); // Waits until count = 0
        System.out.println("All workers done! Main thread proceeds.");
    }
}


```

### Blocking Queue

A Blocking Queue is a thread-safe queue designed for producer-consumer scenarios. It is a part of concurrent package.

```Java

import java.util.concurrent.*;

public class BlockingQueueExample {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5); // max 5 items

        // Producer thread
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    queue.put(i); // waits if queue is full
                    System.out.println("Produced: " + i);
                } catch (InterruptedException e) {}
            }
        }).start();

        // Consumer thread
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    int value = queue.take(); // waits if queue is empty
                    System.out.println("Consumed: " + value);
                } catch (InterruptedException e) {}
            }
        }).start();
    }
}


```

Other common implementation of Blocking Queue
1. ArrayBlockingQueue: Bounded, backed by array
2. LinkedBlockingQueue: Optionally bounded, backed by linked nodes.
3. PriorityBlockingQueue: Unbounded, elements order by priority
4. DelayQueue: Takes elements only after a delay


### Concurrent Map

### Cyclic Barrier

### Exchanger

### Copy on Write array

### Locks

### Locks Conditions

### Reentrant lock

### Read Write lock

### Wait Queue in Read Write lock
