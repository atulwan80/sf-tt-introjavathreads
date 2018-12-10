package simple;

public class Visibility2 {
    static Object rendezvous = new Object();
    static boolean stop = false;
    public static void main(String[] args) throws Throwable {
        new Thread(()->{
            int x = 0;
            System.out.println("Starting worker...");
            while(!stop)
                synchronized (rendezvous) {
                    x++;
                }
            System.out.println("Worker finished...");
        }).start();

        Thread.sleep(1000);
        System.out.println("About to set stop flag to true");
        synchronized(rendezvous) {
            stop = true;
        }
        System.out.println("Stop flag is " + stop);

        // JVM exits when last "non-daemon" thread exits.
    }
}
