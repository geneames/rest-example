package pri.sema.deck.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class DeckListVOTest {

    @Test
    public void getDeckName() throws Exception {
        DeckListVO vo = new DeckListVO("test-deck");
        assertTrue(vo.getDeckName().equals("test-deck"));
    }

    @Test
    public void setDeckName() throws Exception {
        DeckListVO vo = new DeckListVO("test-deck");
        assertTrue(vo.getDeckName().equals("test-deck"));

        vo.setDeckName("new-deck-name");
        assertTrue(vo.getDeckName().equals("new-deck-name"));
    }
}