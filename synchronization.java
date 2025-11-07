
/*
 -we use the synchronization keyword to ensure only one thread can access a method or block of code at a time
-it helps in preventing race condition
 */
class A{
    synchronized void sum(int n){
        //creating a thread instance
        Thread t=Thread.currentThread();
        for(int i=0;i<=5;i++){
            System.out.println(t.getName()+":"+(n+i));
        }
    }
}
//class b extending thread class A
class B extends  Thread{
    //creating an object of class A
    A a=new A();
    public void run(){
        a.sum(10);
    }
}
public class synchronization {
    public static void main(String[] args) {
        //creating an object of class A
        B b=new B();

        //initializing instance t1 of thread class with object of class B
        Thread t1=new Thread(b);

        //initializing instance t2 of thread class with object of class B
        Thread t2=new Thread(b);

        //initializing thread t1 with name ThreadA
        t1.setName("Thread-A");

        //initializing thread t1 with name ThreadB
        t2.setName("ThreadB");

        //starting thread instance t1 and t2
        t1.start();
        t2.start();

    }
}
/*
without synchronized
--------------------------------------------------------------
ThreadB:10
ThreadB:11
Thread-A:10
ThreadB:12
Thread-A:11
ThreadB:13
Thread-A:12
Thread-A:13
ThreadB:14
Thread-A:14
ThreadB:15
Thread-A:15
=======================================================
with synchronized
--------------------
Thread-A:10
Thread-A:11
Thread-A:12
Thread-A:13
Thread-A:14
Thread-A:15
ThreadB:10
ThreadB:11
ThreadB:12
ThreadB:13
ThreadB:14
ThreadB:15
 */