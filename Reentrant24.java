import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class DisplayJob implements Runnable{
    private final TestResources res1;

    public DisplayJob(TestResources res1) {
        this.res1 = res1;
    }

    @Override
    public void run() {
        res1.showRecord(new Object());
    }
}
class ReadJob implements Runnable{
    private final TestResources res1;

    public ReadJob(TestResources res1) {
        this.res1 = res1;
    }

    @Override
    public void run() {
        res1.readRecord(new Object());
    }
}
class TestResources{
    private final Lock l1=new ReentrantLock();
    private final  Lock l2=new ReentrantLock();

 //showRecord method-----------------------------------------------------------------------
    public void showRecord(Object doc) {
        l1.lock();
        try {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName()+":Showing....");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            l1.unlock();
        }
    }
//readRecord method-----------------------------------------------------------------------
        public void readRecord(Object doc){
            l2.lock();
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName()+":Reading....");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                l2.unlock();
            }
        }
    }
public class Reentrant24 {
    public static void main(String[] args) {
     TestResources share=new TestResources();
     DisplayJob D= new DisplayJob(share);
     ReadJob R=new ReadJob(share);

//     Thread t1=new Thread(D);
//     Thread t2=new Thread(R);

        for (int i = 0; i <2 ; i++) {
            Thread t1=new Thread(D,"Thread-"+(i+1));
           t1.start();
        }

        for (int i = 0; i <4 ; i++) {
            Thread t2=new Thread(R,"Thread-"+(i+1));;
            t2.start();
        }


    }
}
