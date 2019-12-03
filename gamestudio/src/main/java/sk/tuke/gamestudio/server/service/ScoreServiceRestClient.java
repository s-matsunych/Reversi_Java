package sk.tuke.gamestudio.server.service;

import com.sun.research.ws.wadl.Response;
import sk.tuke.gamestudio.server.entity.Score;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
//import javax.ws.rs.client.*;
//import javax.ws.rs.core.*;
import java.util.List;

public class ScoreServiceRestClient implements ScoreService {
    private static final String URL = "http://localhost:8080/rest/score";

    @Override
    public void addScore(Score score) throws ScoreException {
        try {
            Client client = ClientBuilder.newClient();
            Response response = client.target(URL)
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(score, MediaType.APPLICATION_JSON), Response.class);
        } catch (Exception e) {
            throw new ScoreException("Error saving score", e);
        }
    }

    @Override
    public List<Score> getTopScores(String game) throws ScoreException {
        try {
            Client client = ClientBuilder.newClient();
            return client.target(URL)
                    .path("/" + game)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<>() {
                    });
        } catch (Exception e) {
            throw new ScoreException("Error loading score", e);
        }
    }
}