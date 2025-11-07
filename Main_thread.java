public class Main_thread {
    public static void main(String[] args) {
        Thread t=Thread.currentThread();
        //getting name of the main thread
        System.out.println("current thread:"+t.getName());

        //changing the name of the main thread
        t.setName("Narsing-Main");
        System.out.println("changed name of main thread:"+t.getName());

        //getting the priority of the thread before changes
        System.out.println("main thread priority before change:"+t.getPriority());

        //setting the priority of the main thread
        t.setPriority(Thread.MAX_PRIORITY);

        System.out.println("priority of the main thread after changing the priority:"+t.getPriority());

      for(int i=0;i<5;i++){
          System.out.println("main thread");
      }
        System.out.println("main thread creating a child class");
      Thread t2=new Thread(){
          @Override
          public void run() {
              for(int i=0;i<5;i++){
                  System.out.println("Child thread in main");
              }
          }
      };
        System.out.println("child thread priority:"+t2.getPriority());
//setting the priority of the child thread
  t2.setPriority(Thread.MIN_PRIORITY);
        System.out.println("child thread new priority:"+t2.getPriority());
  t2.start();
//------------------------------------------------------------------------------------------------------------
        System.out.println("Deadlock with use of main Thread- only single thread");
        try {
            System.out.println("entering into deadlock...");

            //joining the current thread
            Thread.currentThread().join();

            System.out.println("statement will never execute......");
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("handle deadlock situation--------------");

    }
}
