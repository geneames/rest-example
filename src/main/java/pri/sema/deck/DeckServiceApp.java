package pri.sema.deck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class DeckServiceApp extends SpringBootServletInitializer {


    public static void main(String[] args){
        SpringApplication.run(DeckServiceApp.class, args);
    }

    // This is needed to run the service in a stand-alone application server
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DeckServiceApp.class);
    }
}
