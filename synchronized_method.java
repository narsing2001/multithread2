class Test{
        synchronized  void fun1(int n){
        for(int i=1;i<=3;i++){
            System.out.println(n+i);
            try {
                Thread.sleep(100);
            }catch (Exception e){

            }
        }
    }
}

public class synchronized_method {
    public static void main(String[] args) {
          Test t=new Test();
         Thread a=new Thread(){
             @Override
             public void run() {
                 t.fun1(15);
             }
         };
         Thread b=new Thread(){
             @Override
             public void run() {
                 t.fun1(30);
             }
         };

         a.start();
         b.start();
         try{
             a.join();
             b.join();
         }catch(InterruptedException e){
             e.printStackTrace();
         }

    }
}
