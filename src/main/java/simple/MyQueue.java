package simple;

// THIS CODE IS BROKEN!!!!
public class MyQueue {
    private int[] data = new int[10];
    private int count = 0;

    public void put(int x) throws InterruptedException {
        synchronized (this) {
            while (!(count < data.length))
                this.wait();
            data[count++] = x;
            this.notify(); // notifyAll() ??? Horribly un-scalable
        }
    }

    public int take() throws InterruptedException {
        synchronized (this) {
            while (count == 0)
                this.wait();
            int rv = data[0];
            System.arraycopy(data, 1, data, 0, --count);
            this.notify();
            return rv;
        }
    }

    public static void main(String[] args) {
        MyQueue mq = new MyQueue();
        new Thread(()-> {
            try {
                for (int i = 0; i < 100; i++) {
                    mq.put(i);
//                    if (i == 89) { mq.put(3);}
                }
            } catch (InterruptedException ex) {
                System.out.println("Interrupted!!");
            }
            System.out.println("Finished putting");
        }).start();
        new Thread(()-> {
            try {
                for (int i = 0; i < 100; i++) {
                    if (i != mq.take()) {
                        System.err.println("BAD VALUE");
                    }
                }
            } catch (InterruptedException ex) {
                System.out.println("Interrupted!!");
            }
            System.out.println("Finished taking");
        }).start();
        System.out.println("Finished setup");
    }
}
