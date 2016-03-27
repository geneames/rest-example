package pri.sema.deck;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pri.sema.deck.service.DeckService;

/**
 * This base class sets the
 * Spring Application context for
 * all test cases that inherit from
 * it.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(DeckServiceApp.class)
public abstract class TestCaseBase {}
