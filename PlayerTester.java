import java.util.Scanner;
/**
 * Write a description of class PlayerRunner here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PlayerTester
{
    public static void main(String [] args)
    {
        //test constructor
        Player p1=new Player("Grace",100);
        Dealer d1=new Dealer(p1);
        //test toString 
        //test getHand
        ( p1.getHand()).hit(d1.selectCard());
        ( p1.getHand()).hit(d1.selectCard());
        ( p1.getHand()).hit(d1.selectCard());
        ( p1.getHand()).hit(d1.selectCard());
        ( p1.getHand()).hit(d1.selectCard());
        System.out.println(p1);
        System.out.println("value"+p1.getHand().getValue());
        System.out.println("true or false "+p1.getHand().bust());
        Scanner scan=new Scanner(System.in);
        //test DoubleDown
        System.out.println();
        System.out.println("How any chips do you want to bet?");
        int bet=scan.nextInt();
        System.out.println("Do you want double down?");
        String doubleDown=scan.next();
        if(doubleDown.equals("yes"))
        {
            System.out.println(p1.doubleDown(bet));
            
        }
        else
        {
            System.out.println(bet);
        }
        //test fiveCardCharlie
        System.out.println();
        if(p1.fiveCharlie())
        {
            System.out.println("5 cards values are less than 22");
        }
        else 
        {
            System.out.println("5 cards values are NOT less than 22");
        }
        
        //test setChips
        //test getChips
        System.out.println();
        System.out.println("Original chips number: "+p1.getChips());
        System.out.println("How many chips do you want to set it to?");
        p1.setChips(scan.nextInt());
        System.out.println(p1.getChips());
        p1.getHand().resetHand();
        System.out.println("new hand: "+p1);
    }
    
}
