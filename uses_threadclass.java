import java.io.FileNotFoundException;

class Upper extends Thread{
    @Override
    public void run() {
        for(int i=0;i<5;i++){
            System.out.println(Thread.currentThread().getName()+"-count:"+i);
        }
        try{
         Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("Thread Interrupted...");
            e.printStackTrace();
        }
    }
}
//---------------------------------------------------------------------------------------------------------------------------
  public class uses_threadclass {

    private class RunnableImp implements Runnable{
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+": executing run() method!");
        }
    }
//---------------------------------------------------------------------------------------------------------------------------
    private class RunnableImp1 implements  Runnable{
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+":executing run() method!");
            try {
                throw new FileNotFoundException("file not found!");
            } catch (FileNotFoundException e) {
                System.out.println("file not found exception caught here...");
                e.printStackTrace();
            }
        }
//---------------------------------------------------------------------------------------------------------------------------
    }
    public static void main(String[] args) {
        Upper t1=new Upper();
        Upper t2=new Upper();

        t1.setName("worker thread-1");
        t2.setName("worker thread-2");

 //start thread-1---------------------------------------------------------------------------------------------
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            System.out.println("t1 execution ends.....");
            throw new RuntimeException(e);
        }
 //start thread-2-----------------------------------------------------------------------------------------------
        t2.start();
        System.out.println("state of t1:"+ t1.getState());
        System.out.println("state of t2:"+t2.getState());

 //creating  thread-3---------------------------------------------------------------------------------------------------
        Thread t3=new Thread(new uses_threadclass().new RunnableImp());
        //executing thread
        t3.start();
        try{
              t2.join();
              t3.join();
         } catch (InterruptedException e) {
            System.out.println("t2 and t3 execution ends.....");
             throw new RuntimeException(e);
         }
        System.out.println("thread t2 is terminated:"+t2.getState());
        System.out.println("thread t3 is terminated:"+t3.getState());

//running the thread-4---------------------------------------------------------------------------
       Thread t4=new Thread(new uses_threadclass().new RunnableImp1());
       t4.start();
      try{
          t4.join();
        } catch (InterruptedException e) {
          System.out.println("t4 execution end.......");
          throw new RuntimeException(e);
     }
        System.out.println("state of the thread t4:"+t4.getState());

    }
}
