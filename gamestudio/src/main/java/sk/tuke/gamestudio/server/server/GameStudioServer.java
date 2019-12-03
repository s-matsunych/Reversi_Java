package sk.tuke.gamestudio.server.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sk.tuke.gamestudio.server.service.*;

@SpringBootApplication
@Configuration
@EntityScan({"sk.tuke.gamestudio.server.entity"})
//@EntityScan({"sk.tuke.gamestudio.server.entity.Score"})
public class GameStudioServer {
	public static void main(String[] args) {
		SpringApplication.run(GameStudioServer.class, args);
	}

	@Bean(name="scoreServiceServer")
	public ScoreService scoreService() {return new ScoreServiceJPA();}
	@Bean(name="commentServiceServer")
	public CommentService commentService(){return new CommentServiceJPA();}
	@Bean(name="ratingServiceServer")
	public RatingService ratingService(){return new RatingServiceJPA();}
}
