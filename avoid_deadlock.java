public class avoid_deadlock{
    public static void main(String[] args) {
        final String str1 = "Narsing";
        final String str2 = "Patil";

        Runnable task = () -> {
            // Define fixed lock order manually
            Object lockA = str1;
            Object lockB = str2;

            // Always lock str1 first, then str2
            synchronized (lockA) {
                System.out.println(Thread.currentThread().getName() + ": locked " + lockA);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lockB) {
                    System.out.println(Thread.currentThread().getName() + ": locked " + lockB);
                    System.out.println(Thread.currentThread().getName() + ": executing safely");
                }
            }
        };

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Execution completed without deadlock.................................................................................................");

/*
🔍 How This Prevents Deadlock
Both threads use System.identityHashCode() to determine the lock order.
They always lock the object with the lower identity hash code first, ensuring a consistent sequence.
This eliminates the possibility of circular wait, which is the root of deadlock.
*/
        Runnable task2 = () -> {
            String lock1 = str1;
            String lock2 = str2;

            // Ensure consistent lock order using identityHashCode lower hashcode will lock first
            //use the following condition if not than swap the lower and acquire lock on the first
            if (System.identityHashCode(lock1) > System.identityHashCode(lock2)) {
                String temp = lock1;
                lock1 = lock2;
                lock2 = temp;
            }

            synchronized (lock1) {
                System.out.println(Thread.currentThread().getName() + ": locked " + lock1);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lock2) {
                    System.out.println(Thread.currentThread().getName() + ": locked " + lock2);
                    System.out.println(Thread.currentThread().getName() + ": executing safely");
                }
            }
        };

        Thread t3 = new Thread(task2, "Thread-1");
        Thread t4 = new Thread(task2, "Thread-2");

        t3.start();
        t4.start();

        try {
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Execution completed without deadlock using System.identifyhashcode.");
    }
}
/*
🧠 If You Were Using ReentrantLock
With ReentrantLock, you must release the lock manually:
--------------------------------------
Lock lock = new ReentrantLock();
lock.lock();
try {
    // critical section
} finally {
    lock.unlock(); // 🔓 manual release
}
----------------------
while using manual lock use try catch or finally block this will work even if the exception will occur
 */