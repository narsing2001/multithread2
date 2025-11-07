import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/*
-sharedResource is a common variable accessed by two threads.
-ReentrantLock ensures only one thread updates it at a time.
-Both threads increment the value 1000 times each.
-lock.lock() and lock.unlock() provide safe access.
-Final output = 2000, proving thread-safety.
 */
public class Reentrant_lock {
    //ReentrantLock for Thread synchronization
    private  static  final Lock lock=new ReentrantLock();

    //shared resources accessible by multiple thread
    private static  int sharedResources=0;

    static class IncrementTask implements Runnable{
        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                //acquire lock
                lock.lock();
                try {
                    sharedResources++;
                }finally {
                    //release lock
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        IncrementTask inc=new IncrementTask();
        //share common object to the thread
        Thread t1=new Thread(inc);
        Thread t2=new Thread(inc);
        t1.start();
        t2.start();
        try{
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted........wait!");
        }
        //print final value of the shared resources
        System.out.println("final value of shared resources:"+sharedResources);
    }
}
