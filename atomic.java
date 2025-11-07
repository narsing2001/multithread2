import java.util.concurrent.atomic.AtomicInteger;
class Counter{

    //creating a variable of class type AtomicInteger
    AtomicInteger count=new AtomicInteger(0);
    //defining the increment method to change value of AtomicInteger variable
    public synchronized void increment(){
        count.incrementAndGet();
    }

    int count1=4000;
    public synchronized void increment1(){
        // count.incrementAndGet();
        count1++;
    }

}
public class atomic {
    public static void main(String[] args) {
 //creating an instance of counter class-----------------------------------------------
        Counter c=new Counter();

//creating a thread t1----------------------------------------------------------------------------------
  Thread t1=new Thread(){
      @Override
       public void run() {
             for(int i=1;i<=2000;i++){
                   c.increment();
                   c.increment1();
         }
     }
 };


 //creating an instance t2 of thread class------------------------------------------------------------------------
        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
            for(int i=1;i<=2000;i++){
                      c.increment();
                      c.increment1();
            }
            }
        });

        //calling start() method with t1 and t2
          t1.start();
          t2.start();
        //call join method with t1 and t2
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("thread are waiting..........");
        }
        System.out.println("using the atomic count:java.util.concurrent.atomic.AtomicInteger="+c.count);
        System.out.println("using the synchronized increment count1:method initial value::4000 and final value="+c.count1);

    }
}
