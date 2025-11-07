class Test {
    Object getValue(String type) {
        if (type.equals("int")) {
            return 10;
        }else {
            return 10.5;
        }
    }
}

public class Demo {
    public static void main(String[] args) {
        Test t = new Test();
        int x = (int) t.getValue("int");       // cast to int
        double y = (double) t.getValue("double");
        System.out.println(x);
        System.out.println(y);
    }
}
