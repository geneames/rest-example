package pri.sema.deck.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pri.sema.deck.TestCaseBase;
import pri.sema.deck.model.Card;
import pri.sema.deck.model.Deck;
import pri.sema.deck.model.DeckListVO;
import pri.sema.deck.service.DeckService;

import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

@Component
public class DeckServiceImplTest  extends TestCaseBase{

    @Autowired
    private DeckService deckService;

    @Test
    public void createDeck() throws Exception {
        Deck testDeck = this.deckService.createDeck("created-deck-test");

        assertNotNull(testDeck);
        assertNotNull(testDeck.getCards());

        // 52 Cards?
        assertTrue(testDeck.getCards().size() == 52);
    }

    @Test
    public void getDeckList() throws Exception {
        Deck testDeck = this.deckService.createDeck("deck-list-test");
        List<DeckListVO> deckNameList = this.deckService.getDeckList();

        // Should be empty
        assertTrue(deckNameList.size() > 0);

        // Should have 1 entry
        deckNameList = this.deckService.getDeckList();
        assertTrue(deckNameList.size() == 1);
    }

    @Test
    public void getDeck() throws Exception {
        this.deckService.createDeck("get-deck-test");
        Deck retrievedDeck = this.deckService.getDeck("get-deck-test");


        assertTrue(retrievedDeck.equals(retrievedDeck));
    }

    @Test
    public void deleteDeck() throws Exception {
        this.deckService.createDeck("deleted-deck-test");

        // Confirm 'test-deck' is in the database
        assertTrue(this.deckService.getDeck("deleted-deck-test") != null);

        // Delete 'test-deck' from the database
        this.deckService.deleteDeck("deleted-deck-test");

        // Confirm 'test-deck' is not in the database
        assertTrue(this.deckService.getDeck("deleted-deck-test") == null);
    }

    @Test
    public void shuffleDeck() throws Exception {
        Deck newDeck = this.deckService.createDeck("shuffled-deck-test");
        List<Card> origCardList = newDeck.getCards();

        Deck shuffledDeck = this.deckService.shuffleDeck("shuffled-deck-test");
        List<Card> shuffledCardList = shuffledDeck.getCards();

        // Compare Decks
        /*
         * According to JavaDocs list1.equals(list2) is true if and only if the specified object
         * is also a list, both lists have the same size, and all corresponding pairs of elements in the two lists are equal.
         * (Two elements e1 and e2 are equal if (e1==null ? e2==null : e1.equals(e2)).) In other words, two lists are
         * defined to be equal if they contain the same elements in the same order. This definition ensures that the equals method
         * works properly across different implementations of the List interface.
         *
         * Therefore, assertFalse(origDeckCards.equals(shuffledDeckCards)) and assertTrue(origDeckCards.equals(shuffledDeckCards))
         * should both return 'true'. Both assertions insure both lists contain the same elements, but not in the same order.
         */
        assertFalse(origCardList.equals(shuffledCardList));
        assertTrue(origCardList.containsAll(shuffledCardList));
    }
}