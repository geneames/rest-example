package pri.sema.deck.persistence;


import org.springframework.data.jpa.repository.JpaRepository;
import pri.sema.deck.model.Deck;

public interface DeckRepository extends JpaRepository<Deck, String> {


}
