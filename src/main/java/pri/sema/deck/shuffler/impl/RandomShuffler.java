package pri.sema.deck.shuffler.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pri.sema.deck.model.Card;
import pri.sema.deck.model.Deck;
import pri.sema.deck.shuffler.Shuffler;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This shuffler implements the modern version of the
 * Fisher-Yates shuffle algorithm.
 *
 * I could have just as easily used the Collections.shuffle(collection)
 * method, but the intent of the exercise to demonstrate the
 * ability to implement an external algorithm.
 */
@Component
public class RandomShuffler implements Shuffler {

    private final Logger logger = LoggerFactory.getLogger(RandomShuffler.class);

    @Override
    public void shuffle(Deck deck) {
        // Used Linked List for faster remove operations than Array List
        List<Card> origCardList = new LinkedList<Card>(deck.getCards());

        // Remove all cards from the deck
        // list. Items will be re-added in
        // random order.
        deck.getCards().clear();

        for(int i = 52; i >= 1; i--){
            // Generate a random integer between 0 and the
            // remaining number of elements.
            int randomIndex = ThreadLocalRandom.current().nextInt(0, i);

            // Add randomly selected card into the deck's
            // card list.
            deck.getCards().add(origCardList.get(randomIndex));

            // Remove Card from Original Card List
            origCardList.remove(randomIndex);
        }
    }
}