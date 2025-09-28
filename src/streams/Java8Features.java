package streams;

public class Java8Features {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Task());
        t1.start();

        // Lambda Expression

        Thread t2 = new Thread(() -> {
            System.out.println("Using Lambda Expression");
        });

        t2.start();
    }

}

class Task implements Runnable{

    @Override
    public void run(){
        System.out.println("Using normal Expression ");
    }
}


