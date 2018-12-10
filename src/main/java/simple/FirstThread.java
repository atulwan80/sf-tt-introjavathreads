package simple;

public class FirstThread {
    static class MyJob implements Runnable {
        int i = 0;
        @Override
        public void run() {
            for (; i < 10_000; i++) {
                System.out.println(Thread.currentThread().getName() + " count is " + i);
            }
        }
    }
    public static void main(String[] args) {
        MyJob mj = new MyJob();
        Thread t1 = new Thread(mj);
        Thread t2 = new Thread(mj);
        System.out.println(Thread.currentThread().getName() + " About to start thread");
        t1.start();
        t2.start();
        System.out.println(Thread.currentThread().getName() + "Thread started");
    }
}
