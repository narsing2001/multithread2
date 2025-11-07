// Multiple threads are executing on the same object 
// at same time without synchronization
import java.io.*;
class Parent
{
    // if multiple threads(trains) will try to access this unsynchronized method,
    // then object's  state will be corrupted
    public void getLine(){
        for (int i = 0; i < 3; i++){
            System.out.println(i);
            try{
                Thread.sleep(100);
            }
            catch (Exception e){
                System.out.println(e);
            }
        }
    }
}

class Child1 extends Thread {
    // shared object
    Parent p;

    Child1(Parent p) {
        // Initialize shared object
        this.p =p;
    }

    @Override
    public void run() {
        // Access the getLine() method
        p.getLine();
    }
}

public class withoutSync {
    public static void main(String[] args) {
        // Shared Line object
        Parent obj = new Parent();

        // creating the threads that are
        // sharing the same Object
        Child1 t1 = new Child1(obj);
        Child1 t2 = new Child1(obj);

        // threads start their execution
        t1.start();
        t2.start();
    }
}