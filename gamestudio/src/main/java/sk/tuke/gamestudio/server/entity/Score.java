package sk.tuke.gamestudio.server.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


@Entity
@NamedQuery( name = "Score.getBestScores",
        query = "SELECT s FROM Score s WHERE s.game=:game ORDER BY s.points DESC")
public class Score implements Comparable<Score>, Serializable {

    @Id
    @GeneratedValue
    private int ident;
    private String game = "Reversi";

    private String player;

    private int points;


    private Date playedOn;


    public Score() {}
    public Score( String player, int points, Date playedOn) {
        this.player = player;
        this.points = points;
        this.playedOn = playedOn;

    }




    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Date getPlayedOn() {
        return playedOn;
    }

    public int getIdent() {
        return ident;
    }

    public void setIdent(int ident) {
        this.ident = ident;
    }

    public void setPlayedOn(Date playedOn) {
        this.playedOn = playedOn;
    }

    @Override
    public String toString() {
        return "Score{" +
                "game='" + game + '\'' +
                ", player='" + player + '\'' +
                ", points=" + points +
                ", playedOn=" + playedOn +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Score)) return false;
        Score score = (Score) o;
        return ident == score.ident &&
                points == score.points &&
                Objects.equals(game, score.game) &&
                Objects.equals(player, score.player) &&
                Objects.equals(playedOn, score.playedOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ident, game, player, points, playedOn);
    }

    @Override
    public int compareTo(Score o) {
        if(o == null) return -1;
        return this.getPoints() - o.getPoints();
    }
}







