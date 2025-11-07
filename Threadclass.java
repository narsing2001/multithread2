/*
1.extending Thread class:
-------------------------------------------------------------
-we create a new class that extends Thread class from java.lang.Thread; package
-then override it’s run() method to define task
-make object of class and call start() method which automatically call the run() and begin thread execution
 */
class first extends Thread{
    public void run(){
        System.out.println("thread1 is running");
    }

}
class second extends Thread{
    public  void run(){
        System.out.println("tread2 is running");
    }
}
class Cook extends Thread{
    private String task;
    Cook(String task){
        this.task=task;
    }
    public  void run(){
        System.out.println(task+" is being prepared by "+Thread.currentThread().getName());
    }
}
class Cook1 extends Thread{
    private String task;
    Cook1(String task){
        this.task=task;
    }
    public  void run(){
        System.out.println(task+" is being prepared by "+Thread.currentThread().getName());
    }
}
public class Threadclass {
    public static void main(String[] args) {
        System.out.println("create object of class that extends thread class and call thread using start");
        first f=new first();
        second s=new second();
        f.start();
        s.start();
        System.out.println("----------------------------------");
        System.out.println("pass object of class to thread class and call start method");
        Cook1 c1=new Cook1("pasts");
        Cook1 c2=new Cook1("salad");
        Thread t1=new Thread(c1);
        Thread t2=new Thread(c2);
        t1.start();
        t2.start();

        System.out.println("----------------------------------");

        System.out.println("use object of the class extends Thread of reference type thread class and call start method execute the task ");
        Thread t3=new Cook("desert");
        Thread t4=new Cook("Rice");
        t3.start();
        t4.start();

    }
}
