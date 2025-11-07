interface Drawable{
    public void draw(int width);
}
public class lambda1{
    public static void main(String[]args){

        int width=10;
        int breadth=20;
        //with lambda parameters----------------------------------------------------------------------------------------
        Drawable d=(w)->{
            System.out.println("Drawing width:"+width);
        };
        d.draw(width);


        //without lambda parameters-------------------------------------------------------------------------------------
        Drawable d1=( b)->{
            System.out.println("Drawing breadth:"+breadth);
        };
        d1.draw(breadth);

    }
}