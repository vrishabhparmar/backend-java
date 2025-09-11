package week1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableFixedThreadPool {

    public static void main(String[] args) {

        try(ExecutorService service = Executors.newFixedThreadPool(2)){
            Callable<String> task = () -> {
                return "Result from thread: " + Thread.currentThread().getName();
            };

            Future<String> future = service.submit(task);

            System.out.println("Result: "+ future.get());

            System.out.println("Shutdown");

            service.shutdown();
        }
        catch (Exception e){
            System.out.println("Exception");
        }




    }
}
