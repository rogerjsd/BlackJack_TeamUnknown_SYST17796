/*
 * Team: Team Unknown
 * Project: Blackjack
 */

package BlackJack;

import java.util.Scanner;

/**
 * This class +++Insert Description Here+++
 *
 * @author Nitesh S
 */
public class Blackjack {

    public static void main(String args[]){
        //Create scanner input object
        Scanner sc = new Scanner(System.in);
        //Generate a new Deck object
        Deck playingDeck = new Deck();
        playingDeck.createDeck();
        playingDeck.shuffle();
        
        //Create Deck objects for the Player and Dealer
        Deck playerDeck = new Deck();
        Deck dealerDeck = new Deck();
        
        //Declare round end and game end booleans
        boolean endRound = false;
        boolean exitGame = false;
        
        System.out.println("Please enter you name: ");
        //Declare name validation boolean
        boolean nameValid = false;
        //Keep asking for name until the name is valid
        while (!nameValid) {
            String name = sc.nextLine();
            nameValid = checkNameValidation(name);
        }
        
        //Give the Player a starting sum of $300
        double playerMoney = 300.00;
        
        // Game Loop
        while(playerMoney > 0 && !exitGame){
            System.out.println("You currently have $" + playerMoney + ". How much do you want to bet?");
            double playerBet = 0;
            boolean moneyValid = false;
            //Make sure the bet is valid
            while(!moneyValid) {
                playerBet = sc.nextDouble();
                if(playerBet > playerMoney){
                    System.out.println("You can't bet more money than what you have. Enter again:");
                }
                else moneyValid = true;
            }
            
            //Deal 2 cards to the Player first
            playerDeck.draw(playingDeck);
            playerDeck.draw(playingDeck);
            //Then deal 2 cards to the Dealer
            dealerDeck.draw(playingDeck);
            dealerDeck.draw(playingDeck);
            //Purge scanner buffer
            sc.nextLine();
            while(true){
                //Hand outputs
                System.out.println("Your hand: ");
                System.out.println(playerDeck.toString());
                System.out.println("Your hand's value: " + playerDeck.cardsValue());
                
                System.out.println("Dealer has: ");
                System.out.println(dealerDeck.getCard(0));
                
                //Blackjack checks: if the Player or Dealer's hand already equals to 21, end the round immediately
                //The Player's Blackjack is always prioritized over the Dealer
                if(playerDeck.cardsValue() == 21){
                        System.out.println("You got a Blackjack! You win!!");
                        playerMoney += playerBet;
                        endRound = true;
                        break;
                }
                else if(dealerDeck.cardsValue() == 21){
                        System.out.println("The Dealer got a Blackjack! You lose!!");
                        playerMoney -= playerBet;
                        endRound = true;
                        break;
                }
                //Declare player movement validation boolean
                boolean playerMoveValid = false;
                //Integer value of player movement
                int playerMove = 0;
                //Ask the Player for their next move
                System.out.println("Do you want to Hit or Stay? Enter 1 to HIT and 2 to STAY");
                while (!playerMoveValid) {
                    String answer = sc.nextLine();
                    //try pareInt the player's input, if it has error, keep looping
                    try {
                        playerMove = Integer.parseInt(answer);
                        //check the integer value of player's input, if it is 
                        //neither 1 nor 2, keep looping
                        if(playerMove != 1 && playerMove != 2) {
                            System.out.println("Invalid input! Enter 1 to HIT and 2 to STAY");
                        }
                        else 
                            playerMoveValid = true;
                    
                    }
                    catch (Exception e) {
                        System.out.println("Invalid input! Enter 1 to HIT and 2 to STAY");
                    }
                }
                //Hit
                if(playerMove == 1){
                    //Draw and announce it to the Player
                    playerDeck.draw(playingDeck);
                    System.out.println("You drew a: "+ playerDeck.getCard(playerDeck.deckSize()-1).toString());
                    
                    //Bust check
                    if(playerDeck.cardsValue() > 21){
                        System.out.println("You busted! Your value is: "+ playerDeck.cardsValue());
                        playerMoney -= playerBet;
                        endRound = true;
                        break;
                    }
                    //21 check: force the player to stay if their hit makes their hand equal to 21
                    else if(playerDeck.cardsValue() == 21){
                        System.out.println("Your value is: "+ playerDeck.cardsValue() + ". You will now stand.");
                        break;
                    }
                }
                //Stay
                else if(playerMove == 2){
                    break;
                }
            }
            //Reveal the Dealer's hand to see if it's already greater than the Player's
            System.out.println("Dealer's cards: " + dealerDeck.toString());
            if(dealerDeck.cardsValue() > playerDeck.cardsValue() && endRound == false){
                System.out.println("You lose.");
                playerMoney -= playerBet;
                endRound = true;
            }
            
            //Dealer must draw at 16 and stand on all 17s
            while((dealerDeck.cardsValue() < 17) && endRound == false){
                dealerDeck.draw(playingDeck);
                System.out.println("Dealer draws: " + dealerDeck.getCard(dealerDeck.deckSize() -1 ).toString());
            }
            
            //Display the Dealer's total hand value
            System.out.println("Dealer's value: " + dealerDeck.cardsValue());
            
            //The Dealer busts if the value is greater than 21
            if((dealerDeck.cardsValue() > 21) && endRound == false){
                System.out.println("The Dealer busts. You win!");
                playerMoney += playerBet;
                endRound = true;
            } 
            
            //Declare Push if both hands are equal
            if((playerDeck.cardsValue() == dealerDeck.cardsValue()) && endRound == false){
                System.out.println("Push");
                endRound = true;
            }
            
            //Player wins if their hand is greater than the Dealer's
            if((playerDeck.cardsValue() > dealerDeck.cardsValue()) && endRound == false){
                System.out.println("You win!");
                playerMoney += playerBet;
                endRound = true;
            }
            //If the Dealer's hand is greater, the Player loses
            else if(endRound == false){
                System.out.println("You lose.");
                playerMoney -= playerBet;
                endRound = true;
            }
            
            //Move all cards back to the main deck
            playerDeck.moveCardstoDeck(playingDeck);
            dealerDeck.moveCardstoDeck(playingDeck);
            System.out.println("The round is over.");
            
            //Ask if the Player wants to play another round if they have money remaining
            if(playerMoney > 0){
                boolean playAgainValid = false;
                while(!playAgainValid) {
                    System.out.println("Enter 'Y' to play next round. Enter 'N' to leave.");
                    String playAgain = sc.nextLine();
                    if(playAgain.equalsIgnoreCase("n")){
                        playAgainValid = true;
                        //Set game end and announce results
                        exitGame = true;
                        System.out.println("Final money remaining: $" + playerMoney);
                    }
                    else if(playAgain.equalsIgnoreCase("y")){
                        playAgainValid = true;
                        //Reshuffle the deck and reset endRound to false
                        playingDeck.shuffle();
                        endRound = false;
                        //Empty the string
                        playAgain = "";
                    }
                    else {
                        System.out.println("Invalid Input!");
                    }
                }
            }
            //Player lost all their money
            else
                System.out.println("You are out of money. The game will now end.");
        }
        //Announce game over
        System.out.println("Game over!!");
    }
    
    public static boolean checkNameValidation(String name) {
        if (name.isEmpty() || name == null) {
            System.out.println("Name can not be emtpy! Enter again: ");
            return false;
        }
        else return true;
    }
}
