/*
 * Team: Team Unknown
 * Project: Blackjack
 */
package TestBlackJack;

import BlackJack.Blackjack;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author Shengdao Jin
 */
public class BlackJackUnitTest {
    public BlackJackUnitTest(){}
    
    /**
     * Test of checkNameValidation method, of class Balckjack.
     */
    @Test
    public void testCheckNameGood() {// A valid name
        System.out.println("checkNameGood");
        String pass = "Roger";
        boolean expResult = true;
        boolean result = Blackjack.checkNameValidation(pass);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testCheckNameBad() {// An empty name
        System.out.println("checkNameBad");
        String pass = "";
        boolean expResult = false;
        boolean result = Blackjack.checkNameValidation(pass);
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckNameBoundary() {// A valid input but not a name
        System.out.println("checkNameBoundary");
        String pass = "a";
        boolean expResult = true;
        boolean result = Blackjack.checkNameValidation(pass);
        assertEquals(expResult, result);
    }
}
