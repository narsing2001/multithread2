/*
-Uses a shared TicketBooking class with a synchronized bookTicket() method.
-Ensures only one thread can book tickets at a time, preventing race conditions.
-Multiple threads try booking tickets in a loop.
-availableTickets is safely accessed and updated through synchronization.
-Prevents overbooking and ensures thread-safe ticket allocation.
-The program prints the remaining available tickets after all booking attempts.
 */
class TicketBooking{
    private int totalTicket=10; //total available ticket
    //synchronized method for booking tickets
    public synchronized  void bookTicket(int tickets){
        if(totalTicket>=tickets){
            totalTicket-=tickets;
            System.out.println("Booked "+tickets+" tickets,Remaining tickets:"+totalTicket);
        }else{
            System.out.println("not enough tickets available to book:"+tickets);
        }
    }
    public  int getTicketCount(){
        return totalTicket;
    }
}
public class Ticket_thread_synchronized {
    public static void main(String[] args) {
 TicketBooking tic=new TicketBooking(); //shared resources
//thread-1 to book tickets----------------------------------------------------------
Thread t1=new Thread(()->{
     for (int i=0;i<2;i++){
           tic.bookTicket(2); //trying to book 2 tickets
           try{
                 Thread.sleep(1000);
              } catch (InterruptedException e) {
                 e.printStackTrace();
              }
            }
       });

//thread-2 to book tickets----------------------------------------------------------
        Thread t2=new Thread(()->{
            for (int i=0;i<2;i++){
                tic.bookTicket(3); //trying to book 2 tickets
                try{
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
//start both threads---------------------------------------
        t1.start();
        t2.start();
        try{
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//print final remaining tickets---------------
        System.out.println("Final available Tickets:"+tic.getTicketCount());





    }
}
