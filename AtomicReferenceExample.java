/*4. AtomicReference<V>
---------------
Purpose: Allows atomic updates to object references, not just primitive values.
🧠 Explanation:
compareAndSet() updates the reference only if it matches the expected old value.
This prevents two threads from overwriting each other’s changes.
✅ Example: Safely update a shared object
*/
import java.util.concurrent.atomic.AtomicReference;

class User {
    String name;
    User(String name) {
           	this.name = name; 
	}
}

public class AtomicReferenceExample {
    public static void main(String[] args) {
        AtomicReference<User> userRef = new AtomicReference<>(new User("Alice"));

        // Attempt to change Alice → Bob
        User oldUser = userRef.get();
        User newUser = new User("Bob");

        boolean updated = userRef.compareAndSet(oldUser, newUser);

        System.out.println("Update successful: " + updated);
        System.out.println("Current user: " + userRef.get().name);
    }
}


