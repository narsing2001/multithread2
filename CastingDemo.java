class Animal1{
    void eat1(){
        System.out.println("Animal eats food");
    }
    void parentMethod() {
        System.out.println("Animal: parent method");
    }
}
class Dog1 extends Animal1{
    @Override
    void eat1() {
        System.out.println("Dog eats bones");
    }
    void  bark1(){
        System.out.println("dog barks loudly");
    }
    void childMethod() {   // Child-specific method
        System.out.println("Dog: child-specific method (bark)");
    }
}

public class CastingDemo {
    public static void main(String[] args) {
        System.out.println("Upcasting-----------------------------------------------------------");
        Animal1 a1=new Dog1(); //child object ,parent reference
        a1.eat1();  //Dog1 overridden method eat() is called
        //a1.bark1()  //not allowed due reference type is Animal
        a1.parentMethod();

        System.out.println("Downcasting-------------------------------------------------------------------");
        Dog1 d=(Dog1) a1;  //explicit casting required
        d.eat1();     //Dog's overridden eat()
        d.bark1();     //now allowed due to child specific method
        d.parentMethod();
        d.childMethod();

        // ---------- Unsafe Downcasting ----------
        System.out.println("Unsafe Downcasting-------------------------------------------------------------");
        Animal1 a2 = new Animal1(); // Pure Animal object
        // Dog d2 = (Dog) a2;     // ❌ Runtime error: ClassCastException
        // d2.bark();

        System.out.println("wrong downcasting--------------------------------------------------------------");
        try{
            Dog1 x=(Dog1) a2;
            x.childMethod();
        }catch (ClassCastException e){
            System.out.println("Error:"+e);
        }


        System.out.println("safe downcasting with instanceof-------------------------------------------------");
        if(a1 instanceof Dog1){
            Dog1 safeDog=(Dog1) a1;
            safeDog.bark1();  //safe call
        }

        /*
        -unsafe if object is not actually of that type->ClassCastException
        -best practice-always use instanceof before downcasting

        -✅ Real-world analogy:
         Upcasting: “I don’t care which type of dog it is (Bulldog, Labrador, German Shepherd), I just want it as an Animal that can eat().”
         Downcasting: “Now I specifically need a Dog, because I want it to bark() (a feature not in Animal).”
         -in Upcasting we can use the Generalization scope is general Animal may be dog,cat,monkey that can eat anyone
         -in downcasting we can use the Specialization scope is specific animal, i want only dog which can be has bark behaviour scope decreases
         -❌ NOT Accessible:If you downcast wrongly (e.g., Animal a = new Animal(); Dog d = (Dog)a;) → ClassCastException.
        */
        if(a2 instanceof Dog1){
            Dog1 wrongdog= (Dog1) a2;
            wrongdog.bark1();
        }else{
            System.out.println("a2 is animal ,not a Dog so downcasting is unsafe");
        }

    }
}
