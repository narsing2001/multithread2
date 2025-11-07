//Example 1: Below is a simple Java code demonstrating how to use ReentrantLock to prevent race conditions when accessing a shared counter

import java.util.concurrent.locks.ReentrantLock;

public class rentrantLock22 {
    //counter value shared across the thread
    private static int c=0;

    //Lock object
    private static ReentrantLock lock=new ReentrantLock();
    public static void increment(){
        //acquire the lock
        lock.lock();
        try{
            c++;
            System.out.println(Thread.currentThread().getName()+":increment counter to:"+c);
        }finally{
            //unlock used to release the lock
            lock.unlock();
        }
    }
    public static void main(String[] args) {

        Runnable r1=new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <5 ; i++) {
                    increment();
                }
            }
        };
        Thread t1=new Thread(r1,"Thread-1");
        Thread t2=new Thread(r1,"Thread-2");

        t1.start();
        t2.start();
    }
}
