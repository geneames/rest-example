package pri.sema.deck.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class DeckFactoryTest {

    @Test
    public void properDeckInitialized(){
        Deck testDeck = DeckFactory.newInstance("TestDeck");

        // Deck Instance
        assertNotNull(testDeck);

        // Proper Card Count
        assertTrue(testDeck.getCards().size() == 52);
    }
}