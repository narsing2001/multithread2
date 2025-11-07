import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
// Task to simulate display operation
class DisplayTask implements Runnable {
    private final SharedResource resource;
    public DisplayTask(SharedResource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        resource.display(new Object());
    }
}

// Task to simulate read operation
class ReadTask implements Runnable {
    private final SharedResource resource;

    public ReadTask(SharedResource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        resource.read(new Object());
    }
}

// Shared resource with separate locks for display and read
class SharedResource {
    private final Lock displayLock = new ReentrantLock();
    private final Lock readLock = new ReentrantLock();

    public void display(Object doc) {
        displayLock.lock();
        try {
            Thread.sleep(50); // Simulate processing time
            System.out.println(Thread.currentThread().getName() + " is displaying the document.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            displayLock.unlock();
        }
    }

    public void read(Object doc) {
        readLock.lock();
        try {
            Thread.sleep(50); // Simulate processing time
            System.out.println(Thread.currentThread().getName() + " is reading the document.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            readLock.unlock();
        }
    }
}

public class LockDemo {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();
        // Launching threads for display and read operations
        for (int i = 0; i < 2; i++) {
            new Thread(new DisplayTask(resource), "Display-Thread-" + (i + 1)).start();
        }
        for (int i = 0; i < 2; i++) {
            new Thread(new ReadTask(resource), "Read-Thread-" + (i + 1)).start();
        }
        System.out.println("pass object to thread class with thread name:");
        SharedResource resource1 = new SharedResource();
        Thread displayThread1 = new Thread(new DisplayTask(resource1), "Display-Thread-1");
        Thread displayThread2 = new Thread(new DisplayTask(resource1), "Display-Thread-2");
        Thread readThread1 = new Thread(new ReadTask(resource1), "Read-Thread-1");
        Thread readThread2 = new Thread(new ReadTask(resource1), "Read-Thread-2");


        displayThread1.start();
        displayThread2.start();
        readThread1.start();
        readThread2.start();
    }
}

/*
1. Placeholder for Future Use
---------------------------------
The method might be designed to accept a document or data object that will be processed or displayed later. For example:

public void display(Object doc) {
    System.out.println("Displaying: " + doc.toString());
}
Right now, it's just simulating the action with Thread.sleep() and a print statement, but in a real-world scenario, doc could represent:
A file
A database record
A UI component
Any object containing displayable content
------------------------------------------------------------------------------------------------------------------
2. Interface Consistency
------------------------
If you're designing a system where multiple methods (like display, read, write) all operate on a shared object type, keeping the parameter helps maintain a consistent method signature.
----------------------------------------------------------------------------------
3. Thread Simulation
--------------------
Passing a new Object() in each thread call mimics the idea that each thread is working on a separate document. Even though it's unused, it symbolically represents per-thread data.
-------------------------------------------------------------------------------------------------------
4. Avoiding Compilation Errors in Refactoring
---------------------
Sometimes developers include parameters during early design or refactoring stages to avoid breaking method calls elsewhere in the codebase.
---------------------------------------------------------------------------
✅ Can You Remove It?
Yes, if you're not using doc, you can safely remove it from both the method signature and the calls:
java
 */