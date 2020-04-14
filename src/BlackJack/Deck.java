/*
 * Team: Team Unknown
 * Project: Blackjack
 */

package BlackJack;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class models a standard deck of 52 playing cards.
 *
 * @author Nitesh S
 * @author Calvin V
 */
public class Deck {
     
    //Create the cards ArrayList to store Card objects
    private ArrayList<Card> cards;
    
    /**
     * Default constructor for the Deck class.
     * This will instantiate cards.
     */
    public Deck(){
        this.cards = new ArrayList<Card>();
    }
    
    /**
     * Fills the deck with 52 cards of 13 different values across 4 suits.
     */
    public void createDeck(){
        //Generate the loop based on the 4 suits and 13 card values
        for(Suit cardSuit: Suit.values()){
            for(Value cardValue: Value.values()){
                //Add this card to the deck
                addCard(new Card(cardSuit,cardValue) {});
            }
        }
    }
    
    /**
     * Shuffles the Deck by randomly moving cards into a "replacer" cards ArrayList.
     * This requires cards to be populated with Card objects to begin with.
     */
    public void shuffle(){
        //Declare temporary Cards ArrayList
        ArrayList<Card> tempDeck = new ArrayList<Card>();
        //Declare Random class object
        Random random = new Random();
        //Declare index variable to pick from the (remaining) deck and the initial deck size
        int randomCard = 0;
        int cardSize = deckSize();
        
        //Run through the deck loop-wise using its initial deck size
        for(int i = 0; i < cardSize; i++){
            //Pick a random card, then add it to tempDeck
            randomCard = random.nextInt(deckSize());
            tempDeck.add(getCard(randomCard));
            //Remove the randomized card from cards
            removeCard(randomCard);
        }
        //Replace the now empty deck with the shuffled ArrayList
        this.cards = tempDeck;
    }
    
    /**
    * Removes a drawn card from the deck.
    */
    public void removeCard(int i){
        this.cards.remove(i);
    }
    
    /**
     * Returns the card at the specified index.
     * @param cardPick The chosen card's index.
     * @return The chosen card from the Deck.
     */
    public Card getCard(int cardPick){
        return this.cards.get(cardPick);
    }
    
    /**
     * Adds the card object to the Deck.
     * @param addCard The card to be added into the Deck.
     */
    public void addCard(Card addCard){
        this.cards.add(addCard);
    }
    
    /**
     * Draws a card from another Deck object and adds it here.
     * @param fromDeck The deck source of cards to remove card object(s) from.
     */
    public void draw(Deck fromDeck){
        addCard(fromDeck.getCard(0));
        fromDeck.removeCard(0);
    }
    
    /**
     * Retrieves the current size of the Deck.
     * @return The size of the cards ArrayList.
     */
    public int deckSize(){
        return this.cards.size();
    }
    
    /**
     * Transfers cards from this Deck to a target Deck.
     * @param moveCards The deck that will receive all of this object's cards.
     */
    public void moveCardstoDeck(Deck moveCards){
        //Retrieve the current deck size
        int thisDeckSize = deckSize();
        
        //Add all of the cards into the input deck and remove them from this deck object loop-wise
        for(int i = 0; i < thisDeckSize; i++){
            moveCards.addCard(getCard(0));
            removeCard(0);
        }
    }
    
    /**
     * Calculate the total value of the deck.
     * By BlackJack rules, Aces can be 11 or 1 depending on the current value of the deck before taking them into account.
     * All Face cards are equivalent to 10s.
     * @return The deck's total value.
     */
    public int cardsValue(){
        //Declare the deck's total value and the number of aces present
        int totalValue = 0;
        int aces = 0;
        
        //Loop over the deck and assign values via switch case
        //Aces will be calculated after checking the other cards
        for (Card _card: this.cards){
            switch(_card.getValue()){
                case TWO: totalValue += 2; break;
                case THREE: totalValue += 3; break;
                case FOUR: totalValue += 4; break;
                case FIVE: totalValue += 5; break;
                case SIX: totalValue += 6; break;
                case SEVEN: totalValue += 7; break;
                case EIGHT: totalValue += 8; break;
                case NINE: totalValue += 9; break;
                case TEN: totalValue += 10; break;
                case JACK: totalValue += 10; break;
                case QUEEN: totalValue += 10; break;
                case KING: totalValue += 10; break;
                case ACE: aces += 1; break;
            }
        }
        //Add the Ace value(s) based on the deck's value beforehand
        for(int i = 0; i < aces; i++){
            if(totalValue > 10)
                totalValue += 1;
            else
                totalValue +=11;
        }
        return totalValue;
    }
    
    /**
     * Outputs all of the present cards' toStrings.
     * @return The toString of all Card objects inside cards.
     */
    public String toString(){
        //Declare empty string
        String cardOutput = " ";
        //Populate the string with each card's toString
        for(Card _card : this.cards)
            cardOutput += "\n" + _card.toString();
        return cardOutput;
    }
}
