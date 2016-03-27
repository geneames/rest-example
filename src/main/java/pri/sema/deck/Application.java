package pri.sema.deck;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import pri.sema.deck.controller.DeckRestController;

@SpringBootApplication
public class Application {
	
	private static Logger LOGGER = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		LOGGER.info("Starting Deck Service Application");
		SpringApplication.run(DeckRestController.class, args);
		LOGGER.info("Deck Service Application started");
	}
}
