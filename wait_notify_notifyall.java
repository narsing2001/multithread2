import java.util.LinkedList;
import java.util.Queue;
//above programme is for the producer and consumer problem using Queue interface that implements LinkedList
public class wait_notify_notifyall {
    //shared queue is used by the producer and consumer
   private static  final Queue<Integer> q=new LinkedList<>();

    //maximum size of the queue
   private static final int fullSize=10;

    //producer task---------------------------------------------------------
   private static final Runnable produce=new Runnable() {
        @Override
        public void run() {
            while(true){
                //use this for current instance and object reference for the collection or object
                synchronized (q){
                    //wait if the queue is full
                    while(q.size()==fullSize){
                        try{
                            System.out.println("queue is at max capacity.....");
                            q.wait();
                        }catch(InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                    //add item to the queue--------------------------------------------------------------------------------------------------
                    q.add(10);
                    System.out.println("item added into the queue....!");
                    q.notifyAll();
                    try{
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    };
    //Consumer task-----------------------------------------------------------------------------------------------------------------
    private static final Runnable consume= new Runnable() {
        @Override
        public void run() {
            while(true){
                synchronized (q){
                    //wait if the queue is empty
                    while(q.isEmpty()){
                        try{
                            System.out.println("queue is empty,waiting...");
                            q.wait();
                        }catch(InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                    //remove item from the queue---------------------------------------------------------------
                    System.out.println("Removed: "+q.remove()+":from queue");
                    q.notifyAll();    //notify all waiting producer
                    try{
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //---------------------------------------------------------------------------------------------------
            }
        }
    };
    public static void main(String[] args) {
        System.out.println("Main thread started--------------");

        //create and start the producer thread
        Thread p=new Thread(produce,"Producer");

        //create and start the consumer thread
        Thread c=new Thread(consume,"Consumer");
        p.start();
        c.start();
        System.out.println("Main thread exiting......");


    }
}
