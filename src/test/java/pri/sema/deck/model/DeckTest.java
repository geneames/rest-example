package pri.sema.deck.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class DeckTest {

    private Deck firstDeck;
    private Deck secondDeck;

    @Before
    public void setUp(){
        firstDeck = DeckFactory.newInstance("test-deck");
        secondDeck = DeckFactory.newInstance("test-deck");
    }

    @Test
    public void equalityTest(){
        assertTrue(firstDeck.equals(secondDeck) && secondDeck.equals(firstDeck));
        assertTrue(firstDeck.hashCode() == secondDeck.hashCode());
        assertTrue(firstDeck.toString().equals(secondDeck.toString()));
    }
}