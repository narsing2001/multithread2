class Sender {
    public void send(String msg) {
        System.out.println("Sending:" + msg);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
        System.out.println(msg + ":" + "Sent");
    }
}
 class ThreadedSend extends Thread{
        private String msg;
        Sender sender;
//        ThreadedSend(String m,Sender obj){
//            msg=m;
//            sender=obj;
//        }
  ThreadedSend(String msg,Sender sender){
              this.msg=msg;
              this.sender=sender;
          }
        public void run(){
            synchronized (sender){
                sender.send(msg);
            }
        }
 }


public class synchronized_block {
    public static void main(String[] args) {
       Sender send=new Sender();
       ThreadedSend s1=new ThreadedSend("hi",send);
        ThreadedSend s2=new ThreadedSend("Bye",send);
        s1.start();
        s2.start();
        try {
            s1.join();
            s2.join();
        } catch (Exception e) {

        }
    }


}
