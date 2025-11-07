class thread1 extends Thread{
    //initiated run method for thread
    @Override
    public void run() {
        System.out.println("thread1 class extends Thread......");
    }
}

class thread2 implements  Runnable{
    @Override
    public void run() {
        System.out.println("thread2 class implements Thread......");
    }
}

public class Thread_state {
    public static void main(String[] args) {
        //for the thread class
        System.out.println("thread class-----------------------------------------");
        thread1 t1=new thread1();
        t1.start();

        //for the runnable interface
        System.out.println("Runnable interface-----------------------------------");
        thread2 t2=new thread2();
        Thread T=new Thread(t2);
        T.start();

        //wait for the both thread to finish before printing the final result
        try {
            t1.join();
            T.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
            //e.printStackTrace();
        }
    }
}
