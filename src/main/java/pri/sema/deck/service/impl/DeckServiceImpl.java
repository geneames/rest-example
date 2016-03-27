package pri.sema.deck.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pri.sema.deck.model.Deck;
import pri.sema.deck.model.DeckFactory;
import pri.sema.deck.model.DeckListVO;
import pri.sema.deck.persistence.DeckRepository;
import pri.sema.deck.service.DeckService;
import pri.sema.deck.shuffler.Shuffler;

import java.util.ArrayList;
import java.util.List;

/**
 * This component manages all of the data operations.
 */
@Service
class DeckServiceImpl implements DeckService {

    final private Logger logger = LoggerFactory.getLogger(DeckService.class);

    @Autowired
    private Shuffler shuffler;

    @Autowired
    private DeckRepository deckRepo;

    @Override
    public Deck createDeck(String deckName) {
        Deck newDeck = DeckFactory.newInstance(deckName);

        return this.deckRepo.save(newDeck);
    }

    @Override
    public boolean deckExists(String deckName) {
        return this.deckRepo.exists(deckName);
    }

    @Override
    public List<DeckListVO> getDeckList() {
        List<Deck> deckList = this.deckRepo.findAll();
        List<DeckListVO> deckNameList = new ArrayList<>();

        for(Deck deck : deckList){
            deckNameList.add(new DeckListVO(deck.getDeckName()));
        }

        return deckNameList;
    }

    @Override
    public Deck getDeck(String deckName) {
        return this.deckRepo.findOne(deckName);
    }

    @Override
    public void deleteDeck(String deckName) {
        this.deckRepo.delete(deckName);
    }

    @Override
    public Deck shuffleDeck(String deckName) {
        Deck deck = null;
        this.logger.debug("Shuffling deck {}", deckName);

        // Retrieve Deck
        if(this.deckRepo.exists(deckName)) {
            deck = this.deckRepo.findOne(deckName);
        }
        else {
            throw new RuntimeException(String.format("Deck named '%s' does not exist in database.", deckName));
        }

        // Shuffle deck
        this.shuffler.shuffle(deck);

        // Persist shuffled deck
        return this.deckRepo.save(deck);
    }
}
