/*
Thread Synchronization
------------------------
Thread synchronization is the process of controlling the order of access to shared resources among
multiple threads to prevent data corruption and ensure correct program execution. in the present
time we useLocks to achieve thread synchronization. It is achieved by using Synchronized Keyword.
Example: Thread Synchronization using synchronized
*/
// Java program to demonstrate thread synchronization
class Counter2 {
    // counter variable
    private int c = 0;
    // synchronized method
    public synchronized void increment() {
        c++;
    }
    public int getCount() {
        return c;
    }
}
public class Thread_Sync {
    public static void main(String[] args) throws InterruptedException {
        Counter2 cnt = new Counter2();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                cnt.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                cnt.increment();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Count: " + cnt.getCount());
    }
}