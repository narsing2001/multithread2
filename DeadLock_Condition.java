
/*
🧩 Four Necessary Conditions for Deadlock
To prevent deadlocks, it's helpful to understand the four conditions that must all be true for a deadlock to occur:
Mutual Exclusion – Only one thread can hold a resource at a time.
Hold and Wait – A thread holding one resource is waiting for another.
No Preemption – Resources can't be forcibly taken away.
Circular Wait – A closed chain of threads exists, each waiting for a resource held by the next.
If you break any one of these conditions, you can prevent deadlock.

✅ Practical Strategies to Prevent Deadlocks
1. Consistent Lock Ordering
---------------------------
Always acquire locks in a fixed global order. For example:

synchronized (lockA) {
    synchronized (lockB) {
        // safe operations
    }
}
All threads must follow this order to avoid circular wait.
---------------------------------------------------------------------------------------------------------------
2. Avoid Nested Locks
---------------------
Nested locking (locking one resource inside another lock) increases the risk of circular dependencies.
If possible, redesign logic to avoid this pattern.
-----------------------------------------------------------------------------------------------------------------------------------------
3. Use tryLock() with Timeout
-----------------------------
Java’s ReentrantLock offers tryLock() which lets a thread attempt to acquire a lock and back off if it fails:
if (lock.tryLock(100, TimeUnit.MILLISECONDS)) {
    try {
        // critical section
    } finally {
        lock.unlock();
    }
}
This avoids indefinite waiting.
------------------------------------------------------------------------------------------------------------------------------------------------
4. Minimize Lock Scope
-----------------------
Keep the locked section of code as short and efficient as possible. Don’t perform long operations
(like I/O or sleep) while holding a lock.
------------------------------------------------------------------------------------------------------------------------------------
5. Avoid Unnecessary Locks
--------------------------
Only lock what’s absolutely necessary. Over-locking increases contention and risk of deadlock.
-----------------------------------------------------------------------------------------------------------------
6. Use Thread Join with Timeout
-------------------------------
If one thread must wait for another, use join() with a timeout to prevent indefinite blocking:
thread.join(2000); // waits max 2 seconds
--------------------------------------------------------------------------------------------------------------------------
7. Deadlock Detection Tools
Use profilers or thread dump analyzers (like VisualVM, JConsole, or IntelliJ's debugger) to detect deadlocks during development.

🧠 Bonus: Deadlock-Free Design Patterns
Actor Model: Avoids shared state entirely.
Message Passing: Threads communicate via queues instead of shared locks.
Immutable Objects: No need for locks if data doesn’t change.
 */




public class DeadLock_Condition {
    public static void main(String[] args) {
        final String str1="Narsing";
        final String str2="Patil";
        Thread t1=new Thread(){
            @Override
            public void run() {
                synchronized (str1){
                    System.out.println("Thread-1:locked resources-1");
                    try{
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (str2){
                        System.out.println("Thread-1:locked resources-2");
                    }
                }
           /*
                   synchronized (str2){
                         System.out.println("Thread-1:locked resources-2");
                      }
           */

            }
        };


        Thread t2=new Thread(){
            @Override
            public void run() {
                synchronized (str2){
                    System.out.println("Thread-2:locked resources-1");
                    try{
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (str1){
                        System.out.println("Thread-2:locked resources-2");
                    }
                }
                 /*
                   synchronized (str1){
                              System.out.println("Thread-2:locked resources-2");
                    }
                 */
            }
        };

          t1.start();
          t2.start();

          try{
              t1.join();
              t2.join();
          } catch (InterruptedException e) {
              e.printStackTrace();
          }



    }
}
