
// Java program of ReentrantLock() With Conditions
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
public class EvenOdd2 extends Thread {
    ReentrantLock lock = new ReentrantLock();
    // Conditions for even/odd
    Condition ev = lock.newCondition();
    Condition od = lock.newCondition();

    // Counter variable
    int c;
    EvenOdd2(int c) {
        this.c = c;
    }
    int MAX = 10;
    public void run() {
        while (c <= MAX) {
            // Acquire the lock
            lock.lock();
            try {
                // wait for even Conditions
                if (c % 2 == 1 && Thread.currentThread().getName().equals("even")) {
                    ev.await();
                } else if (c % 2 == 0 && Thread.currentThread().getName().equals("odd")) {
                    // Wait for odd condition
                    od.await();
                } else {
                    System.out.println(Thread.currentThread().getName() + " Thread " + c);
                    c += 1;
                    if (c % 2 == 0)
                        ev.signal();
                    else
                        od.signal();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // Release the lock
                lock.unlock();
            }
        }
    }
    public static void main(String[] args) {
     EvenOdd2 obj = new EvenOdd2(0);
        Thread even = new Thread(obj, "even");
        Thread odd = new Thread(obj, "odd");
        even.start();
        odd.start();

    }
}
