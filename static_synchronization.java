/*
synchronized method is declared as static which means lock or monitor is applied on class not on object so that only one thread will
access the class at a time
 */
class PrintTest extends Thread{
    //static synchronization locks the class PrintTest
    synchronized  public static void Print(int n){
        for (int i = 0; i <=10 ; i++) {
            System.out.println("Thread-"+n+" is working....");
            try{
                Thread.sleep(1000);
            }catch (Exception e){
                System.out.println(e.toString());
            }
        }
        System.out.println("-----------------------------------------");
        try{
            Thread.sleep(1000);
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }
}
//helper class extending the thread class-----------------------------------------------
class Thread1 extends Thread{
    //run() method for thread

    @Override
    public void run() {
        //passing the class not the object
        PrintTest.Print(1);
    }
}
class Thread2 extends Thread{
    @Override
    public void run() {
        //passing the class not the object
        PrintTest.Print(2);
    }
}
//main class
public class static_synchronization {
    public static void main(String[] args) {
 //no shared object create the objects of thread1 and thread2 that are extending to Thread class
 Thread1 t1=new Thread1();
 Thread2 t2=new Thread2();

 //starting the thread with help of start() method
        t1.start();
        t2.start();

    }
}
