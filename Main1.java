

class Resource {
    private final String name;

    Resource(String name) { 
                this.name = name; 
            }

    void doWork(Resource other) {
        Resource first = this;
        Resource second = other;
        
/*
Thread-1: lock R1 → lock R2 → done
Thread-2: lock R1 → lock R2 → done

Execution order:
Thread-1 locks R1
Thread-2 waits for R1
Thread-1 locks R2
Thread-1 finishes → releases R1 and R2
Thread-2 locks R1 → locks R2 → done
------------------------------------------------------------------
Step 3: How your code avoids deadlock
Resource first = this.name.compareTo(other.name) < 0 ? this : other;
Resource second = this == first ? other : this;
----------------
This ensures both threads lock resources in the same order (lexicographical by name).
Now Thread-1 and Thread-2 always lock R1 first, then R2.
This prevents circular waiting, which is one of the four necessary conditions for deadlock.
-------------without deadlock------------------------------
Thread-2 locked R1
Thread-2 locked R2
Thread-1 locked R1
Thread-1 locked R2
Execution-1 completed without deadlock
Thread-1 is executing task1
Thread-2 is executing task1
Thread-1 is executing task2
Thread-2 is executing task2
Execution-2 completed without deadlock
...Program finished with exit code
----------------------------------------------------------------------
Explanation of Deadlock Prevention Methods Used:

1.Avoid Nested Locks:
In the example, each thread locks resource1 and resource2 sequentially but avoids locking them in different order. This prevents circular waiting.

2.Avoid Unnecessary Locks:
Only methods that actually modify or need thread safety are synchronized (task1 and task2), no unnecessary locks are used.

3.Using Thread.join():
Ensures that the main thread waits for threads to finish, but with a timeout to prevent indefinite waiting, reducing the risk of deadlock.


*/

// Consistent lock order based on resource name prevent deadlock-----------------------
        if (this.name.compareTo(other.name) > 0) {
            first = other;
            second = this;
        }

/*
Thread-1 locks R1 → tries to lock R2
Thread-2 locks R2 → tries to lock R1

Thread-1: [R1] waiting for R2
Thread-2: [R2] waiting for R1
→ Deadlock occurs
------------------------------------------
-Deadlock happens when threads hold some locks and wait indefinitely for others.
-Nested synchronized blocks are dangerous if different threads lock in different orders.
-Consistent lock ordering (or using tryLock) is a simple and effective way to prevent it.
------------------------------------------
Step 2: How deadlock happens

Deadlock typically occurs when two threads lock resources in different order:
Suppose we have two resources: r1 and r2.

Thread-1 executes:

synchronized(r1) { // locks r1
    synchronized(r2) { // tries to lock r2
        ...
    }
}


Thread-2 executes:

synchronized(r2) { // locks r2
    synchronized(r1) { // tries to lock r1
        ...
    }
}


Timeline of events that causes deadlock:

Step	Thread-1	Thread-2
1	Locks r1	Locks r2
2	Tries to lock r2 → blocked	Tries to lock r1 → blocked

Thread-1 waits for r2, which is held by Thread-2.

Thread-2 waits for r1, which is held by Thread-1.

Neither thread can proceed → deadlock.
-------with Dead Locks--------------



*/

        synchronized (first) {
            System.out.println(Thread.currentThread().getName() + " locked " + first.name);
            try { 
                Thread.sleep(50);
            } catch (InterruptedException e) {
                
            }
            synchronized (second) {
                System.out.println(Thread.currentThread().getName() + " locked " + second.name);
            }
        }
    }
}

class SharedResource {
    void task1() {
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + " is executing task1");
            try {
                Thread.sleep(100); // simulate some work
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void task2() {
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + " is executing task2");
            try {
                Thread.sleep(100); // simulate some work
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Main1 {
    public static void main(String[] args) {
        Resource r1 = new Resource("R1");
        Resource r2 = new Resource("R2");

        Thread t1 = new Thread(() -> r1.doWork(r2), "Thread-1");
        Thread t2 = new Thread(() -> r2.doWork(r1), "Thread-2");

        t1.start();
        t2.start();

        try {
            t1.join(500); // wait max 500ms
            t2.join(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Execution-1 completed without deadlock");
        
        
         SharedResource resource1 = new SharedResource();
        SharedResource resource2 = new SharedResource();

        // Thread 1
        Thread t3 = new Thread(() -> {
            resource1.task1();
            resource2.task2();
        }, "Thread-1");

        // Thread 2
        Thread t4 = new Thread(() -> {
            resource1.task1(); // avoid nested locking on resource2
            resource2.task2();
        }, "Thread-2");

        t3.start();
        t4.start();

        // Using Thread.join() to avoid waiting indefinitely
        try {
            t3.join(500); // waits max 500ms for t3 to finish
            t4.join(500); // waits max 500ms for t4 to finish
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Execution-2 completed without deadlock");
    }

    
}
