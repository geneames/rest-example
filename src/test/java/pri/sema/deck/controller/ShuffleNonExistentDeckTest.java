package pri.sema.deck.controller;


import com.jayway.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.context.web.WebAppConfiguration;
import pri.sema.deck.TestCaseBase;
import pri.sema.deck.model.Deck;
import pri.sema.deck.model.DeckFactory;
import pri.sema.deck.persistence.DeckRepository;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@WebAppConfiguration
@IntegrationTest(value = "server.port:0")
public class ShuffleNonExistentDeckTest extends TestCaseBase{

    @Autowired
    DeckRepository repo;

    @Value("${local.server.port}")
    int port;

    @Before
    public void setUp(){
        this.repo.deleteAll();

        Deck newDeck = DeckFactory.newInstance("test-deck");
        this.repo.save(newDeck);

        RestAssured.port = this.port;
    }

    @Test
    public void shuffleDeck(){
        given().
                formParam("deckName", "different-deck").
        when().
                post("/api/decks/shuffle").
        then().
                statusCode(HttpStatus.SC_OK).
                contentType("application/json").
                body(equalTo("{}"));
    }
}