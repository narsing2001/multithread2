interface A1 {
    void doSomething();
    default void  doDefault(){
        System.out.println("hi i am default method..");
    }
}
class B1 {
    void sayHello() {
        System.out.println("Hello from B");
    }
}

class C1 extends B1 implements A1 {
    public void doSomething() {
        System.out.println("Doing something in C");
    }
}

public class extends_class_implement {
    public static void main(String[] args) {
        C1 obj = new C1();
        obj.sayHello();      // Inherited from class B
        obj.doSomething();   // Implemented from interface A
        obj.doDefault();
    }
}
