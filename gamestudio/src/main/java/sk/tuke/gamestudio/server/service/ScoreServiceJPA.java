package sk.tuke.gamestudio.server.service;

import sk.tuke.gamestudio.server.entity.Score;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class ScoreServiceJPA implements ScoreService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addScore(Score score) throws ScoreException {
        entityManager.persist(score);
    }

    @Override
    public List<Score> getTopScores(String game) throws ScoreException {
       return entityManager.createNamedQuery("Score.getBestScores")
               .setParameter("game",game).setMaxResults(10).getResultList();
    }





}