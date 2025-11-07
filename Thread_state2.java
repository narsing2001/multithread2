class parent implements Runnable{
    @Override
    public void run() {
        System.out.println("implementing try catch block to set sleep state for inactive thread");
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        }
        System.out.println("the state of t1 after it invoked join method() on thread t2:"+" "+Thread_state2.t1.getState());
    try{
        Thread.sleep(2000);
    } catch (InterruptedException e) {
        throw new RuntimeException(e);
    }

    }
}

public class Thread_state2 implements Runnable {
    public static Thread t1;
    public static Thread t2;

    @Override
    public void run() {
        t2=new Thread(new parent());
        t2.start();
        System.out.println("state of t2-Parent after start():"+t2.getState());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("state of t2-parent after sleep():"+t2.getState());
        try{
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("state of t2-parent after join():"+t2.getState());
            throw new RuntimeException(e);

        }
        System.out.println("state of t2 after join():"+t2.getState());
        System.out.println("state of t1 after start():"+t1.getState());

    }

    public static void main(String[] args) {

        t1=new Thread(new Thread_state2());
        System.out.println("initial state of t1:"+t1.getState());
        t1.start();
        System.out.println("state of t1 after start():"+t1.getState());
        // Wait briefly to ensure t2 is initialized and started
        try {
            Thread.sleep(500); // optional delay
        } catch (InterruptedException e) {
            System.out.println("state of the t1-child: "+t1.getState());
            e.printStackTrace();
        }
        try {
            t1.join();
        } catch (InterruptedException e) {
            System.out.println("----main thread code execution end-----------");
        }
      //  System.out.println("----main thread code execution end-----------");
        System.out.println("state of t1 thread:"+t1.getState());
        System.out.println("state of t2 thread:"+t2.getState());

    }
}
