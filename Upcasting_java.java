/*Casting is the process of converting a reference of one class
 type into another class type
 --------------------------------------------
 1.Upcasting refers to  converting a subclass reference into  a superclass reference
 -casting happen implicitly
 -upcasting allows us to treat an object of a subclass as if it were an object of one of its superclasses
 -We are restricting the reference to only superclass members.
 Even if the object is of subclass type, you cannot access child-specific members.
 BUT: If a method is overridden in subclass, the child’s implementation runs (runtime polymorphism).
 -it consider that every subclass is superclass
 -----------------------------------------------------------------------------------------------------------------------------------------
 2.downcasting involves converting a superclass reference back into a subclass reference
 -downcasting is explicit
 -we must ensure that the object being cast matches the target type
 to avoid ClassCastException
 -in downcasting we need to access subclass specific method or field that aren't
 available in the superclass
 -----------------------------------------------------------------------------------------------------------------
 | Feature / Method Type      | Upcasting (Child → Parent)        | Downcasting (Parent → Child)        |
| -------------------------- | --------------------------------- | -------------------------------------|
| **Parent methods**         | ✅ Accessible                      | ✅ Accessible                      |
| **Overridden methods**     | ✅ Accessible (child version runs) | ✅ Accessible (child version runs) |
| **Child-specific methods** | ❌ Not accessible                  | ✅ Accessible                      |
-------------------------------------------------------------------------------------------------------|
 */
class Animal{
    void eat(){
        System.out.println("Animal eat...");
    }
    void sounds(){
        System.out.println("animal sounds.....");
    }
}
class Dog extends Animal{
//    void eat() {
//        System.out.println("Dog eat");
//    }
    void bark(){
        System.out.println("Dog barks");
    }

    void sounds() {
        System.out.println("barking..........");
    }
}

public class Upcasting_java {
    public static void main(String[] args) {

     Animal a=new Dog();  //upcasting
        a.eat(); //method in parent overridden in child
        //a.bark();//not allowed not in an animal  since reference is a type of a animal
      /*
      overridden method i.e child version is executed(runtime polymorphism)
     -Superclass method/variable:: only members available in the parent reference can be accessible
     ex:if the Animal has sleep() then a.sleep() is valid
     -subclass-specific method :: are not accessible directly after upcasting
     ex:a.bark() is invalid unless you downcast back to Dog
     ---------------------------
     -upcasting allows only parent class method/varibale but overriden methods follow runtime binding(child method execute)
     -if you need subclass methods you must use downcasting
     in upcasting, the method you can use are:
     1)parent class methods
     2)overriden child method(via dynamic dispatch)
     3)child specific method (unless downcasted)
       */
        Animal a1=new Dog();   //upcasting
        Dog d2=(Dog) a1;      //downcasting
        d2.eat();            //parent method inherited through inheritance
        d2.bark();          //accessed the child specific method(only possible after downcasting)
        d2.sounds();  //overridden method still behave polymorphically(child version executes)

      //type safety check (instanceof)->before downcasting we usually check to avoid ClassCastException
      if(a instanceof Dog){
          Dog d3=(Dog) a;
          d3.bark();  //safe call
      }
      /*
      -parent methods(inherited from superclass)
      -overridden method (child's version executes)
      -subclass-specific methods/fields(only accessible after downcasting)
      -Risky: If object is not actually of that child type, it throws ClassCastException.
       Allows access to child-specific methods/fields
       */
/*
|--------------------------------------------------------------------------------------------------------------|
| Feature       | Upcasting                                           | Downcasting                            |
| ------------- | --------------------------------------------------- | -------------------------------------- |
| Direction     | Child → Parent                                      | Parent → Child                         |
|   Cast        | Implicit                                            | Explicit `(Child)`                     |
|   Safety      | Always safe                                         | Risky (may throw `ClassCastException`) |
|   Access      | Only parent methods (overridden child version runs) | Parent + Child-specific methods        |
|   Usage       | Common (polymorphism, abstraction)                  | Rare, only when needed                 |
---------------------------------------------------------------------------------------------------------------|

         */

    }
}
