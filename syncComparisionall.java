import java.util.*;
/*
-That synchronized block gives you fine-grained control.
-That synchronized method locks the entire method.
-That static synchronized method locks at the class level, affecting all instances.
 -------------------------------------------------------------------------------------------------------------------------
Each synchronization type locks on a different object:
      1.Block & Method: Lock on the instance (this)
      2.Static Method: Lock on the class (ClassName.class)

 */
class SyncComparison {
    private String name = "";
    private static int staticCount = 0;
    private int instanceCount = 0;

    // 🔒 Synchronized Block
    public void updateWithBlock(String newName) {
        synchronized (this) {
            name = newName;
            instanceCount++;
            System.out.println("Block: " + name + " | Instance Count: " + instanceCount);
        }
    }

    // 🔒 Synchronized Method
    public synchronized void updateWithMethod(String newName) {
        name = newName;
        instanceCount++;
        System.out.println("Method: " + name + " | Instance Count: " + instanceCount);
    }

    // 🔒 Static Synchronized Method
    public static synchronized void updateStatic(String newName) {
        staticCount++;
        System.out.println("Static Method: " + newName + " | Static Count: " + staticCount);
    }
}

public class syncComparisionall {
    public static void main(String[] args) {
        SyncComparison obj1 = new SyncComparison();
        SyncComparison obj2 = new SyncComparison();

        // Threads using synchronized block
        Thread t1 = new Thread(() -> obj1.updateWithBlock("Block-A"));
        Thread t2 = new Thread(() -> obj1.updateWithBlock("Block-B"));

        // Threads using synchronized method
        Thread t3 = new Thread(() -> obj2.updateWithMethod("Method-A"));
        Thread t4 = new Thread(() -> obj2.updateWithMethod("Method-B"));

        // Threads using static synchronized method
        Thread t5 = new Thread(() -> SyncComparison.updateStatic("Static-A"));
        Thread t6 = new Thread(() -> SyncComparison.updateStatic("Static-B"));

        // call synchronized block start
        t1.start();
        t2.start();
        //call synchronized method start
        t3.start();
        t4.start();
        //call synchronized static block
        t5.start();
        t6.start();

        // Wait for all threads to finish
        try {
            //wait synchronized block
            t1.join();
            t2.join();
            //wait synchronized method
            t3.join();
            t4.join();
            //wait synchronized static block
            t5.join();
            t6.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
