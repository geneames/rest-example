package pri.sema.deck.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class CardTest {

    private Card firstCard;
    private Card secondCard;

    @Before
    public void setUp(){
        Deck testDeck = DeckFactory.newInstance("test-deck");

        // First Card
        firstCard = new Card();
        firstCard.setSuit("Spades");
        firstCard.setFace("A");
        firstCard.setParentDeck(testDeck);

        // Second Card
        secondCard = new Card();
        secondCard.setSuit("Spades");
        secondCard.setFace("A");
        secondCard.setParentDeck(testDeck);
    }

    @Test
    public void getSuit() throws Exception {
        assertTrue(firstCard.getSuit().equals("Spades"));
    }

    @Test
    public void getFace() throws Exception {
        assertTrue(firstCard.getFace().equals("A"));
    }

    @Test
    public void equalityTest(){
        assertTrue(firstCard.equals(secondCard)  && secondCard.equals(firstCard));
        assertTrue(firstCard.hashCode() == secondCard.hashCode());
    }

}