

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
Example 3: This example demonstrates how to use ReentrantLock with Condition objects to synchronize even and odd thread operations.
 */
public class OddEvenLock implements Runnable {
    private int c;
    private final Lock lock=new ReentrantLock();

//condition for even odd
    private final Condition ev=lock.newCondition();
    private final Condition od=lock.newCondition();
    private boolean isEvenTurn=true;
    public OddEvenLock(int start){
        this.c=start;
    }
int MAX=10;
    @Override
    public void run() {
        while(c<=MAX){
            //acquire the lock
            lock.lock();
            try{
                if(c%2==1 && Thread.currentThread().getName().equals("even")){
                  //  while (!isEvenTurn){
                    //wait for even condition
                        ev.await();
                    }
                  else if(c%2==0 && Thread.currentThread().getName().equals("odd")){
                        System.out.println("Even:"+c);
                        c++;
                        isEvenTurn=false;
                        od.signal();
                    }else{
                        while(isEvenTurn){
                            od.await();
                        }
                        if(c%2!=0){
                            System.out.println("Odd:"+c);
                            c++;
                            isEvenTurn=true;
                            ev.signal();
                        }
                    }

            }catch (InterruptedException e){
                e.printStackTrace();
            }finally{
                lock.unlock();
            }
        }
    }
    public static void main(String[] args) {
   OddEvenLock obj=new OddEvenLock(0);
        Thread even = new Thread(obj, "even");
        Thread odd = new Thread(obj, "odd");
        even.start();
        odd.start();
    }
}
/*
🔹 import java.util.concurrent.locks.*;
This imports the locking utilities from Java's concurrency package:
-----------------------------
Lock: Interface for locking mechanisms.
ReentrantLock: A concrete implementation of Lock.
Condition: Used for thread coordination (like wait() and notify() but more flexible).
--------------------------------
🔹 class Geeks implements Runnable
You're defining a class Geeks that implements Runnable, meaning it can be run by a thread. Both threads will share this object.

🔹 private int c;
This is the shared counter that both threads will increment and print. It starts from 0 and goes up to 10.

🔹 private final Lock l = new ReentrantLock();
This creates a mutual exclusion lock to ensure only one thread accesses the critical section at a time.

🔹 private final Condition ev = l.newCondition();
🔹 private final Condition od = l.newCondition();
These are condition variables used to pause and resume threads:
---------------
ev is for the even thread.
od is for the odd thread.
---------------------------------------------------
🔹 private boolean isEvenTurn = true;
This flag tells whose turn it is:

true → even thread should run.
false → odd thread should run.

🔹 public Geeks(int start)
Constructor to initialize the counter c.

🔹 public void run()
This is the core logic that runs when a thread starts. Both threads share this method but behave differently based on their name.

🔹 while (c <= 10)
Loop until the counter reaches 10.

🔹 l.lock();
Before entering the critical section, the thread acquires the lock.

🔹 try { ... } catch { ... } finally { l.unlock(); }
Standard pattern:

try: Do the work.
catch: Handle interruptions.
finally: Always release the lock.

🔹 if (Thread.currentThread().getName().equals("even"))
Check if the current thread is the even thread.

🔹 while (!isEvenTurn) ev.await();
If it's not the even thread's turn, it waits on the ev condition.

🔹 System.out.println("Even: " + c);
Print the even number.

🔹 c++; isEvenTurn = false;
od.signal();
Increment the counter, switch turn to odd, and signal the odd thread to wake up.

🔹 else { while (isEvenTurn) od.await(); ... }
Same logic for the odd thread:

Wait if it's not its turn.

Print odd number.
-------------------------------
Increment counter.
Switch turn to even.
Signal even thread.

🔹 public static void main(String[] args)
This is the entry point of the program.

🔹 Geeks obj = new Geeks(0);
Create a shared Geeks object with counter starting at 0.

🔹 Thread even = new Thread(obj, "even");
🔹 Thread odd = new Thread(obj, "odd");
Create two threads using the same Runnable object but give them different names to distinguish behavior.

🔹 even.start(); odd.start();
Start both threads. They begin executing run() and alternate printing numbers.
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
🔧 private final Condition ev = lock.newCondition();
This line creates a condition variable named ev that is associated with the lock lock. It's not checking anything by itself—it's a tool used for thread coordination.

Think of it like a waiting room for threads. Here's what it does:

🧠 What Is a Condition?
----------------------------------
A Condition in Java is like a more advanced version of wait() and notify() used with synchronized.
But instead of being tied to intrinsic locks, it's used with ReentrantLock.
-----------------------------
It allows threads to:
1)Wait (ev.await()) until a certain condition is met.
2)Signal (ev.signal()) another thread that the condition has changed.

🧪 What Does ev Represent?
In your code, ev is used by the even-number thread. It doesn't "check" anything directly,
but it helps enforce this logic:
1.If it's not the even thread's turn (isEvenTurn == false), the even thread waits on ev.
2.When the odd thread finishes its job, it signals ev to wake up the even thread.
3.So ev is the communication channel that lets the even thread know: "Hey, it's your turn now!"

🔁 Example Flow
Even thread starts.
------------------
Checks isEvenTurn. If false → ev.await() → goes to sleep.
Odd thread finishes → sets isEvenTurn = true → calls ev.signal() → wakes up even thread.

🧵 Summary
ev is a Condition object used to pause and resume the even thread.
It works with lock to ensure safe coordination.
It doesn't "check" anything directly—it waits and signals based on logic in your code.
 */
