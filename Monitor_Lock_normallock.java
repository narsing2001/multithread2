class ShareDataclass{
    //monitor implementation is carried out by using the synchronization
    synchronized public void display(String str){
        for (int i = 0; i <str.length() ; i++) {
            System.out.println(str.charAt(i));
         //try catch block for exception because the thread method is used
            try{
                Thread.sleep(100);
            }catch (Exception e){

            }
        }
    }
}
class Thread11 extends Thread{
    ShareDataclass p1;

    public Thread11(ShareDataclass p1){
        this.p1=p1;
    }
    public  void run(){
        p1.display("Narsing");
    }
}

class Thread12 extends Thread {
    ShareDataclass p2;
    public  Thread12(ShareDataclass p2){
        this.p2=p2;
    }
    public void run(){
        p2.display("Patil");
    }
}

public class Monitor_Lock_normallock {
    public static void main(String[] args) {
        ShareDataclass share=new ShareDataclass();
        Thread11 t1=new Thread11(share);
        Thread12 t2= new Thread12(share);

        t1.start();
        t2.start();

    }
}
