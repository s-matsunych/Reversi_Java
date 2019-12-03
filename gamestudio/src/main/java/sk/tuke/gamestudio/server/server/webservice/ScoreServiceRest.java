package sk.tuke.gamestudio.server.server.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import sk.tuke.gamestudio.server.entity.Score;
import sk.tuke.gamestudio.server.service.CommentException;
import sk.tuke.gamestudio.server.service.ScoreException;
import sk.tuke.gamestudio.server.service.ScoreService;
import sk.tuke.gamestudio.server.service.ScoreServiceJPA;

import javax.validation.constraints.Null;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/score")
public class ScoreServiceRest {
    @Autowired
    private ScoreService scoreService;//TODO: pridat deklaraciu premennej pre servisny komponent JPA, anotovanu @Autowired

    //http://localhost:8080/rest/score
    @POST
    @Consumes("application/json")
    public Response addScore(Score score) throws ScoreException {
        //TODO: pridat score prostrednictvom servisneho komponentu JPA
        scoreService.addScore(score);
        return Response.ok().build();
    }

    //http://localhost:8080/rest/score/mines
//    @GET
//    @Path("/{game}")
//    @Produces("application/json")
//    public List<Score> getTopScores(@PathParam("game") String game) throws ScoreException {
//        //TODO: vrátit skore prostrednictovm servisneho komponentu JPA
//        return scoreService.getTopScores(game);
//
//    }
    @GET
    @Path("/{game}")
    @Produces("application/json")
    public List<Score> getTopScores(@PathParam("game") String game) throws ScoreException {
        return scoreService.getTopScores(game);
    }
}

