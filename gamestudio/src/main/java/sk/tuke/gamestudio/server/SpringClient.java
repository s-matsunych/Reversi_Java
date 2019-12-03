package sk.tuke.gamestudio.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sk.tuke.gamestudio.server.service.*;

@Configuration
@SpringBootApplication
public class SpringClient {
    public static void main(String[] args) {
      //new SpringApplicationBuilder(SpringClient.class).web(WebApplicationType.NONE).run(args);
      SpringApplication.run(SpringClient.class, args);

    }

//    @Bean
//    public CommandLineRunner runner(ConsoleUI ui) {
//        return args -> ui.run();
//    }

//    @Bean
//    public ConsoleUI ReversiMatsunychConsoleUI() {
//        return new ConsoleUI();
//    }

    @Bean
    public ScoreService scoreService() {
        return new ScoreServiceJPA();
//        return new ScoreServiceRestClient();
    }

    @Bean
    public CommentService commentService() {
        return new CommentServiceJPA();
//       return new CommentServiceRestClient();
    }

    @Bean
    public RatingService ratingService() {
        return new RatingServiceJPA();
//        return new RatingServiceRestClient();
    }
}
