package simple;

public class Visibility {
    static volatile boolean stop = false;
    public static void main(String[] args) throws Throwable {
        new Thread(()->{
            System.out.println("Starting worker...");
            while(!stop)
                ;
            System.out.println("Worker finished...");
        }).start();

        Thread.sleep(1000);
        System.out.println("About to set stop flag to true");
        stop = true;
        System.out.println("Stop flag is " + stop);

        // JVM exits when last "non-daemon" thread exits.
    }
}
