package pri.sema.deck;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import pri.sema.deck.shuffler.Shuffler;
import pri.sema.deck.shuffler.impl.RandomShuffler;

@Configuration
@Profile("random-shuffle")
public class RandomShufflerConfig {

    @Bean
    public Shuffler shuffler(){
        return new RandomShuffler();
    }
}
