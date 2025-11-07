class base extends Thread{
    private String task;
    base(String task){
        this.task=task;
    }
    public void run(){
        System.out.println(task+" is being prepared by "+Thread.currentThread().getName());
    }
}
class base1 implements Runnable {   // ✅ implements Runnable
    private String task;

    base1(String task) {
        this.task = task;
    }

    @Override
    public void run() {   // ✅ override run()
        System.out.println(task + " is being prepared by " + Thread.currentThread().getName());
    }
}
public class RunnableInterface {
    public static void main(String[] args) {

        System.out.println("crete constructor object and pass object to thread class using thread object call start method");
        base b1=new base("pizza");//b1 is thread and also runnable
        Thread t1=new Thread(b1);    //thread constructor accept Runnable.thread implements Runnable
        t1.start();                  //start t1
        System.out.println("direct start -----------------------");
        base b2=new base("Sandwich");
        b2.start();

    //to use the object with thread the class must implement Runnable because thread's constructor expects a runnable object
        System.out.println("pass direct constructor object to thread class and call the start method");
        Thread t2=new Thread(new base1("burger"));//pass inline object
        t2.start();

    }
}
