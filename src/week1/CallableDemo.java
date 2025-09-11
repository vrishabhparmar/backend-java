package week1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        try (ExecutorService executorService = Executors.newFixedThreadPool(2)) {

            executorService.submit(new ReturnValueTask());
            //System.out.println(future.get());
        }

    }

}

class ReturnValueTask implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        return 0;
    }
}
