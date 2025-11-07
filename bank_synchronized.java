/*
-Demonstrates process synchronization using a shared BankAccount object.
-Two threads operate concurrently: one for depositing, one for withdrawing.
-Both deposit() and withdraw() methods are marked as synchronized.
-synchronized ensures mutual exclusion only one thread can access the balance at a time.
-Prevents race conditions when threads access or modify the balance simultaneously.
-Ensures thread safety and maintains a consistent, accurate account balance.
*/
class Bankaccount{
    private int balance=1000; //sharing resources bank balance

    //synchronized method for deposit operation--------------------------------------------------------------------
    public  synchronized  void deposite(int amount){
        balance+=amount;
        System.out.println("Deposited: "+amount+",balance: "+balance);
    }
    //synchronized method for withdrawal operation--------------------------------------------------------
    public synchronized  void  withdraw(int amount){
        if(balance>=amount){
            balance-=amount;
            System.out.println("withdrawn:"+amount+",Balance:"+balance);
        }else{
            System.out.println("Insufficient balance to withdraw:"+amount);
        }
    }
    //get balance method-------------------------------------------------------------
    public int getBalance(){
           return balance;
    }
}
public class bank_synchronized {
    public static void main(String[] args) {
        Bankaccount acc=new Bankaccount(); //shared resources

        //thread-1 to deposit money into account
        Thread t1=new Thread(()->{
         for(int i=0;i<3;i++){
             acc.deposite(200);
             try{
                 Thread.sleep(1000);
             } catch (InterruptedException e) {
                 System.out.println("amount deposit processing wait.....!");
                 e.printStackTrace();
             }
         }
        });

        //thread-1 to deposit money into account
        Thread t2=new Thread(()->{
            for(int i=0;i<3;i++){
                acc.withdraw(100);
                try{
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("amount withdraw processing wait.....!");
                    e.printStackTrace();
                }
            }
        });

        //start both threads
        t1.start();
        t2.start();

        //wait for both thread to finish its works

        try{
            t1.join();
            t2.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        //print the final balance
        System.out.println("final balance:"+acc.getBalance());


    }
}
