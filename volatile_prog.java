/*
-volatile keyword ensures visibility changes across thread
-it does not guarantee atomicity
-but ensures every thread reads the latest value from main memory, not cache
 */

public class volatile_prog {
    //initializing volatile variable a,b
    static volatile int a=0,b=0;
    //defining a static void method
    static void method1(){
        a++;
        b++;
    }
    static void method2(){
        System.out.println("a="+a+","+"b="+b);
    }
    public static void main(String[] args) {
        //creating an instance t1 of thread class
        Thread t1=new Thread(){
            @Override
            public void run() {
                for (int i=0;i<5;i++){
                    method1();
                }
            }
        };

        Thread t2=new Thread(){
            @Override
            public void run() {
                for(int i=0;i<5;i++){
                  method2();
              }
            }
        };
 //starting instance t1 nd t2
        t1.start();
        t2.start();
    }
}
