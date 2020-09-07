import java.util.Scanner;
/**
 * Dealer.java  
 *
 * @author:
 * Assignment #: Beuluh Lee
 * 
 * Brief Program Description:
 * 
 *
 */
public class Dealer
{
    private Shoe shoe = new Shoe(4);
    private Hand dealerHand = new Hand();
    private Player p1;
    //creates a new shoe with 4 decks for the game and a dealer hand. It takes in the player as a parameter so it can access its hand in later methods
    public Dealer(Player p)
    {
        p1=p;
        shoe = new Shoe(4);    
        shoe.shuffleShoe();
        dealerHand = new Hand();
    }
    // selects a card from the shoe and returns it
    public Card selectCard()
    {
        return shoe.dealCard();
    }
    //takes in a string command from the Player and does as written (as a card to Player’s hand when “hit is called” while returning true to indicate an action, 
    //while nothing is done and false is returned when stand is called
    public boolean takeInOrder(String s)
    {
        if(s.equals("hit") || s.equals("Hit")){
            p1.getHand().hit(selectCard());
            return true;
        }
        else
        {
            return false;

        }
    }
    //Checks the dealer’s hand’s total value. If the total value is equal or less than 16, the dealer has to hit. If the total value is greater than 16, has to stand. 
    public boolean yesOrNo(){
        if(dealerHand.getValue()<17){
            dealerHand.hit(selectCard());
            return true;
        }
        else{
            return false;
        }
    }
    //returns the dealer’s hand for use in other methods 
    public Hand getDHand()
    {
        return dealerHand;
    }
    //prints out dealer's cards individually, excluding the first card
    public String toString()
    {
        String h = "";
        for(int x=1; x<dealerHand.getSize(); x++){
            h += ((getDHand())).getCardValueHand(x) +"\n"; //maybe might work?
        }
        return "Dealer's Card except for the first card\n"+h;
    }

    public String revealString()
    {
        String h="";
        for(int x=0; x<dealerHand.getSize(); x++){
            h += ((getDHand())).getCardValueHand(x) +"\n"; //maybe might work?
        }
        return "Dealer's Card \n"+h;
    }

    public void start()
    {
        Scanner scan=new Scanner(System.in);
        int round=0;
        while(p1.getChips()>0)
        {

            round++;
            boolean player=false;
            boolean dealer=false;
            boolean playerTurn=true;
            boolean dealerTurn=false;
            System.out.println("ROUND "+round);
            System.out.println("Current chips: "+p1.getChips());
            p1.getHand().resetHand();
            getDHand().resetHand();
            while(player==false && dealer==false)
            {
                System.out.println("How many chips do you wanna bet?");
                int tempChip=scan.nextInt();
                boolean dblDown = false;

                (p1.getHand()).hit(selectCard());
                (p1.getHand()).hit(selectCard());
                (getDHand()).hit(selectCard());
                (getDHand()).hit(selectCard());
                System.out.println(toString());
                System.out.println(p1);
                if(p1.getHand().blackjack())
                {
                    if(getDHand().lastCard().getCardValue()==10)
                    {
                        if(getDHand().blackjack())
                        {
                            System.out.println("PUSH, it is a tie");
                            player=true;
                            dealer=true;
                        }
                        else
                        {
                            System.out.println("YAY, you won because you have a black jack and the dealer does not");
                            p1.setChips(p1.getChips()+tempChip);
                            System.out.println("New chips number: "+p1.getChips());
                            player=true;
                        }
                    }
                    else if(getDHand().lastCard().isAce())
                    {
                        System.out.println("Do you want to buy insurance?");
                        String insurance=scan.next();
                        if(insurance.equals("yes")||insurance.equals("Yes"))
                        {
                            System.out.println("How much insurance do you want? (up to half of what you bet");
                            int newInsurance=scan.nextInt();

                            if(getDHand().blackjack())
                            {
                                System.out.println("YAY you won because both you and the dealer have black Jack");
                                p1.setChips(p1.getChips()+tempChip+(2*newInsurance));
                                System.out.println("New number of chips: "+p1.getChips());
                                player=true;
                            }
                            else
                            {
                                p1.setChips(p1.getChips()-newInsurance);
                            }
                        }

                    }

                }
                else
                {
                    if(getDHand().lastCard().getCardValue()==10)
                    {
                        if(getDHand().blackjack())
                        {
                            System.out.println("SORRY you lost because you don't have black jack but the dealer does.");
                            p1.setChips(p1.getChips()-tempChip);
                            System.out.println("New number of chips: "+p1.getChips());
                            dealer=true;
                        }
                    }
                    else if(getDHand().lastCard().isAce())
                    {
                        System.out.println("Do you want to buy insurance?");
                        String insurance=scan.next();
                        if(insurance.equals("yes")||insurance.equals("Yes"))
                        {
                            System.out.println("How much insurance do you want? (up to half of what you bet");
                            int newInsurance=scan.nextInt();

                            if(getDHand().blackjack())
                            {
                                System.out.println("Round ends...U LOST");
                                p1.setChips(p1.getChips()-tempChip+(2*newInsurance));
                                dealer=true;
                            }
                            else
                            {
                                p1.setChips(p1.getChips()-newInsurance);
                            }

                        }

                    }

                }
                if(!player && !dealer)
                {
                    System.out.println("Do you want double down?");
                    String doubleDown=scan.next();
                    if(doubleDown.equals("yes"))
                    {
                        tempChip=p1.doubleDown(tempChip);
                        System.out.println("New betting amount: "+tempChip);
                        p1.getHand().hit(selectCard());
                        System.out.println(p1);
                        // if(p1.getHand().bust())
                        // {
                            // System.out.println("Sorry you lost, you busted.");
                            // p1.setChips(p1.getChips()-tempChip);
                            // dealer=true;
                        // }
                        dblDown = true;
                        dealerTurn=true;
                        playerTurn=false;
                    }
                    if(playerTurn){
                        while(!p1.getHand().bust() &&!getDHand().bust())                
                        {
                            String command="";

                            if(playerTurn)
                            {
                                System.out.println("This is your card: \n"+p1);
                                System.out.println("Please print your command");
                                command=scan.next();
                                takeInOrder(command);
                                System.out.println("Your order was "+command+":\n"+p1);
                                if(p1.fiveCharlie()||p1.getHand().getValue()==21)
                                {
                                    if(getDHand().blackjack())
                                    {
                                        System.out.println("Round ends...U LOST 1");
                                        p1.setChips(p1.getChips()-tempChip);
                                        dealer=true;
                                    }
                                    System.out.println("Round ends...U WON 2");
                                    p1.setChips(p1.getChips()+tempChip);
                                    player=true;
                                }
                                if(p1.getHand().lastCard().isAce()){
                                    System.out.println("You have been dealt an ace. What value would you like to set it to? 10 or 1?");
                                    int ace = scan.nextInt();
                                    if(ace == 1)
                                    {
                                        p1.getHand().lastCard().setAceToOne(true);
                                    }
                                    else
                                    {
                                        p1.getHand().lastCard().setAceToOne(false);
                                    }
                                }
                                dealerTurn=true;
                                playerTurn=false;

                            }
                            if(dealerTurn)
                            {
                                
                                yesOrNo();
                                System.out.println(revealString());
                                if(getDHand().bust()==true)
                                {
                                    System.out.println("Round ends...U WON 3");
                                    p1.setChips(p1.getChips()+tempChip);
                                    player=true;
                                }
                                if(command.equals("stand"))
                                {
                                    playerTurn=false;
                                    dealerTurn=true;
                                }
                                if(!dblDown){
                                    playerTurn=true;
                                    dealerTurn=false;
                                }
                                else{
                                    playerTurn=false;
                                    dealerTurn=false;

                                }
                            }
                            if(command.equals("stand") &&!yesOrNo() && !playerTurn && !dealerTurn)
                            {
                                if(p1.getHand().getValue() == getDHand().getValue()){
                                    System.out.println("PUSH...you and the deaker has the same value");
                                    player=true;
                                    dealer=true;
                                }
                                else if(p1.getHand().getValue() <getDHand().getValue()){
                                    System.out.println("SORRY you lost, both of you stood but dealer has a higher hand ");
                                    p1.setChips(p1.getChips()-tempChip);
                                    dealer=true;
                                }
                                else 
                                {
                                    System.out.println("YAY you won, both of you stood but you have a higher hand");
                                    p1.setChips(p1.getChips()+tempChip);
                                    player=true;
                                }

                            }

                        }
                    }
                        if(p1.getHand().bust())
                        {
                            p1.setChips(p1.getChips()-tempChip);
                            System.out.println("SORRY you lost, you busted ");
                            dealer=true;
                        }
                        else if(p1.getHand().getValue() == getDHand().getValue()){
                                    System.out.println("PUSH it's a tie");
                                    player=true;
                                    dealer=true;
                                }
                        else
                        {
                            System.out.println("YAY you won, the dealer busted");
                            p1.setChips(p1.getChips()+tempChip);
                            player=true;

                        }
                    
                }
            }

        }
    }
}
