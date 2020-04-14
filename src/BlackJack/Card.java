/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as a modifier and the date!
 */
package BlackJack;

/**
 * A class to be used as the base Card class for the project. Must be general
 * enough to be instantiated for any Card game. Students wishing to add to the code 
 * should remember to add themselves as a modifier.
 * @author megha, 2020
 * @author Calvin V
 */
public abstract class Card 
{
    private Suit suit;
    private Value value;
    
    
    /**
     * This is the Card class' main constructor.
     * @param suit Card's suit
     * @param value Card's value
     */
    public Card(Suit suit, Value value){
        this.suit = suit;
        this.value = value;
        
    }
    
    /**
     * Returns the value of the card.
     * @return The card's value. Ace = 1/11, Face cards = 10
     */
    public Value getValue(){
        return this.value;
    }
    
    /**
     * Outputs the full name of the card.
     * @return The card's suit, followed by its value.
     */
    public String toString(){
        return this.suit.toString() + " " + this.value.toString();
    }
}
