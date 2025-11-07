// Multiple threads execute the same method
// but in synchronized way
class Parent1{
    // Synchronized method ensures that only one thread
    // can execute this method at a time on the same object
    synchronized public void getLine(){
        for (int i = 0; i < 3; i++) {
            System.out.println(i);
            try {
                Thread.sleep(100);
            }
            catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}

class Child11 extends Thread {
    // Reference variable 
    Parent1 p1;

    Child11(Parent1 p1) {
        this.p1 =p1;
    }

    @Override
    public void run() {
        p1.getLine();
    }
}

public class withSynchronizedBlock {
    public static void main(String[] args) {

        // Object of Line class shared among the threads
       Parent1 obj = new Parent1();

        // Creating threads that share the same object
        Child11 t1 = new Child11(obj);
        Child11 t2 = new Child11(obj);

        t1.start();
        t2.start();
    }
}