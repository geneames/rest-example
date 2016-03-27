package pri.sema.deck.shuffler;


import org.junit.Test;
import pri.sema.deck.model.Card;
import pri.sema.deck.model.Deck;
import pri.sema.deck.model.DeckFactory;
import pri.sema.deck.shuffler.impl.InterleaveShuffler;
import pri.sema.deck.shuffler.impl.RandomShuffler;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class ShufflerTest {

    @Test
    public void testRandomShuffler(){
        Shuffler randomShuffler = new RandomShuffler();
        this.testShuffler(randomShuffler);
    }

    @Test
    public void testInterleaveShuffler(){
        Shuffler interleaveShuffler = new InterleaveShuffler();
        this.testShuffler(interleaveShuffler);
    }

    private void testShuffler(Shuffler shuffler){
        Deck deck = DeckFactory.newInstance("TestDeck");

        // Copy Original Deck Order
        List<Card> origCardList = new ArrayList<>(deck.getCards());

        // Shuffle Deck
        shuffler.shuffle(deck);

        // Get Shuffled Deck Order
        List<Card> shuffledCardList = deck.getCards();

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
