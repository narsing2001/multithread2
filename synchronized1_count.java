class Counter1{
    private int c=0;
    private int c1=0;
    public  synchronized void increment(){
        c++;
    }

    public  synchronized int getIncrement(){
        return c;
    }

    // Method with synchronization block--------------------------------------------------------
    public void inc() {
        synchronized(this) { // Synchronize only this block
            c1++;
        }
    }

    // Method to get counter value
    public int get() {
        return c1;
    }


}
public class synchronized1_count {
    public static void main(String[] args) {
        Counter1 cnt=new Counter1();
        System.out.println("thread-1 execute.......");
        Thread t1=new Thread(()->{
                for(int i=0;i<1000;i++){
                    cnt.increment();
                }
        });

        System.out.println("thread-2 execute......");
        Thread t2=new Thread(()->{
            for(int i=0;i<1000;i++){
                cnt.increment();
            }
        });

        System.out.println("thread-3 execute......");
        Thread t3=new Thread(()->{
            for(int i=0;i<500;i++){
                cnt.inc();
            }
        });
        System.out.println("thread-4.......");
        Thread t4=new Thread(()->{
            for(int i=0;i<500;i++){
                cnt.inc();
            }
        });


        t1.start();
        t2.start();
        try{
            //wait both thread to finish the work
            System.out.println("thread-1 wait start----------------");
            t1.join();
            System.out.println("thread-1 wait end and Thread-2 wait start----------------");
            t2.join();
            System.out.println("thread-2 wait end----------------");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Total counter of synchronized method::"+cnt.getIncrement());

        t3.start();
        t4.start();
        try{
            System.out.println("thread-3 wait start----------------");
            t3.join();
            System.out.println("thread-3 wait end and Thread-4 wait start----------------");
            t4.join();
            System.out.println("thread-4 wait end-----------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Total counter of synchronized block::"+cnt.get());

    }
}
