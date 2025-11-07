

class MyThread extends Thread{
    MyThread(String name){
        super(name);
    }

    @Override
    public void run() {
        System.out.println("my thread method is running....");
    }
}


class Ticket1 implements  Runnable{
    @Override
    public void run() {
        try{
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Ticket1 thread class name:"+Thread.currentThread().getName());
        System.out.println("state of bookingThread while mainThread is waiting:"+Ticket_Real.t1.getState());
    }
}
//------------------------------------------------------------------------------------------------------------------------
public class Ticket_Real implements  Runnable {
    public  static Thread t1;
    public static Ticket_Real t2;
    @Override
    public void run() {
        Ticket1 tik1=new Ticket1();
        Thread tikThread=new Thread(tik1);
        System.out.println("state after creating tikThread-t2:"+tikThread.getState());
        tikThread.start();
        System.out.println("state after starting tikThread t2:"+tikThread.getState());
        try{
            tikThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Ticket_Real thread class name:"+Thread.currentThread().getName());
        System.out.println("state after tikThread-t2 finishers working:"+tikThread.getState());
    }

//--------------------------------------------------------------------------------------------------------------------------
    public static void main(String[] args) {
        t2=new Ticket_Real();
        t1=new Thread(t2);
        System.out.println("state after creating mainthread t1:"+t1.getState());
        t1.start();
        try{
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("state after starting mainThread t1:"+t1.getState());

        MyThread t3=new MyThread("t-3");
        MyThread t4=new MyThread("t-4");
        t3.start();
        System.out.println(t3.getName());
        t4.start();
        System.out.println(t4.getName());

        t3.setName("Thread-3 changed to Patil-3");
        t4.setName("Thread-4 changed to Patil-4");
        System.out.println("current thread name-1:"+Thread.currentThread().getName());
        System.out.println("current thread name-2:"+Thread.currentThread().getName());




    }
}
