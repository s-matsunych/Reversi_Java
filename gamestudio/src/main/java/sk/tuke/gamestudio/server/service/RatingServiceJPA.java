package sk.tuke.gamestudio.server.service;

import org.springframework.transaction.annotation.Transactional;
import sk.tuke.gamestudio.server.entity.Rating;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Transactional
public class RatingServiceJPA implements RatingService {


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void setRating(Rating rating) throws RatingException {
        entityManager.merge(rating);
    }

    @Override
    public int getAverageRating(String game) throws RatingException {
//        return (int)entityManager.createNamedQuery("Rating.getAverageRating")
//                .setParameter("game",game).getSingleResult();
        return (int)Math.round((Double)entityManager.createQuery("SELECT AVG(e.rating) FROM Rating e WHERE e.game=:game")
                .setParameter("game",game).getSingleResult());
    }

    @Override
    public int getRating(String game, String player) throws RatingException {
        Rating current = (Rating) entityManager.createNamedQuery("Rating.getRating").setParameter("game", game)
                .setParameter("player", player).getSingleResult();

        return current.getRating();
    }

}
