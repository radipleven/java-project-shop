import java.util.Date;

public class Main {
   public static void main(String[] args) {
       System.out.println("Welcome to My Java Project!");

       Date finalDate= new Date();
       Goods goods = new Goods("hrana",12.5, Goods.Category.EDIBLE, finalDate, 5);
       System.out.println(goods.getName());
   }
}