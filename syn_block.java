import java.util.*;

class Parent3 {
    private String name = "";
    private int count = 0;

    // Method demonstrating block synchronization
    public void updateName(String newName, List<String> nameList) {
        // Synchronize only the critical section that modifies shared data
        synchronized (this) {
            name = newName;
            count++;
            System.out.println("Name updated to: " + name + " | Update count: " + count);
        }

        // This part is not synchronized — multiple threads can add to the list concurrently
        nameList.add(newName);
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }
}

public class syn_block {
    public static void main(String[] args) {
        Parent3 p3 = new Parent3();
        //create synchronizedList of class type ArrayList()
        List<String> ListNames = Collections.synchronizedList(new ArrayList<>());

        // Simulate multiple threads updating the geek's name
        Thread t1 = new Thread(() -> p3.updateName("Mohit", ListNames));
        Thread t2 = new Thread(() -> p3.updateName("Aisha", ListNames));
        Thread t3 = new Thread(() -> p3.updateName("Ravi", ListNames));

        t1.start();
        t2.start();
        t3.start();

        // Wait for threads to finish
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Final output
        System.out.println("Final name: " + p3.getName());
        System.out.println("Total updates: " + p3.getCount());
        System.out.println("Names in list: " + ListNames);
    }
}
