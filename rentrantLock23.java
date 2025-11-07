import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

class Worker implements Runnable{
    private ReentrantLock lock;
    private String name;
    public  Worker(ReentrantLock lock,String name){
        this.lock=lock;
        this.name=name;
    }

    @Override
    public void run() {
        while(!lock.tryLock()){
            System.out.println(name+":waiting for lock");
        }

        System.out.println(name+":acquired lock");
        //release the lock after completing the task
        lock.unlock();
        System.out.println(name+":released lock");
    }
}
public class rentrantLock23 {
    public static void main(String[] args) {
        ReentrantLock lock=new ReentrantLock();
        ExecutorService pool= Executors.newFixedThreadPool(2);

        //lock acquiring
        pool.execute(new Worker(lock,"job1"));
        pool.execute(new Worker(lock,"job2"));

        //shutdown the executor service
        pool.shutdown();


    }
}
