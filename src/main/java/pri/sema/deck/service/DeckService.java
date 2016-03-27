package pri.sema.deck.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pri.sema.deck.model.Deck;
import pri.sema.deck.model.DeckListVO;
import pri.sema.deck.shuffler.Shuffler;

import java.util.List;

@Service
public interface DeckService {

    Deck shuffleDeck(String deckName);
    Deck createDeck(String deckName);
    List<DeckListVO> getDeckList();
    Deck getDeck(String deckName);
    void deleteDeck(String deckName);
    boolean deckExists(String deckName);
}
