/*✅ Example: Thread-safe counter
🔹 1. AtomicInteger

Purpose: Perform atomic operations on an int value — like incrementing a counter safely across multiple threads.
*/
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerExample {
    private static AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        // Two threads incrementing the counter
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.incrementAndGet(); // Atomic increment
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.incrementAndGet(); // Atomic increment
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Final counter value: " + counter.get());
    }
}
/*
🧠 Explanation:

Both threads safely increment the same variable.

Without AtomicInteger, you might get inconsistent results due to race conditions.

With it, you always get 2000.
*/