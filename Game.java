import java.util.Scanner;
/**
 * Game.java  
 *
 * @author: All
 * Assignment #:
 * 
 * Brief Program Description:
 * 
 *
 */
public class Game
{

    int chip;
    public static void main(String[] args)
    {
        Scanner scan=new Scanner(System.in);
        System.out.println("What is your name?");
        String name=scan.next();
        System.out.println("How many chips do you have?");
        int chip=scan.nextInt();
        Player p1=new Player(name, chip);
        Dealer d1=new Dealer(p1);
        d1.start();
    }

}
