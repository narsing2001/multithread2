/*🔹 3. AtomicBoolean
-----------------------------
Purpose: Performs atomic operations on a boolean value — great for flags or on/off switches between threads.
✅ Example: Simple flag control
🧠 Explanation:
The first call sets the flag from false → true, so it starts the task.
The second call fails because another thread (or same thread) already set it to true.
*/
import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicBooleanExample {
    private static AtomicBoolean isRunning = new AtomicBoolean(false);

    public static void main(String[] args) {
        if (isRunning.compareAndSet(false, true)) {
            System.out.println("Task started...");
        } else {
            System.out.println("Task is already running!");
        }

        // Trying again
        if (isRunning.compareAndSet(false, true)) {
            System.out.println("Task started again!");
        } else {
            System.out.println("Task is already running!");
        }
    }
}


