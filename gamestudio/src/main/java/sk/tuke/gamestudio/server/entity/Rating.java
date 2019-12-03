package sk.tuke.gamestudio.server.entity;

import javax.persistence.*;
import java.util.Date;

//@Entity
//@NamedQueries({
//        @NamedQuery( name = "Rating.getAverageRating",
//                query = "SELECT AVG(e.rating) FROM Rating e WHERE e.game=:game"),
//        @NamedQuery( name = "Rating.getRating",
//                query = "SELECT s FROM Rating s WHERE s.game=:game AND s.player=:player")
//})

@Entity
@NamedQuery( name = "Rating.getRating",
        query = "SELECT s FROM Rating s WHERE s.game=:game AND s.player=:player")
public class Rating {
    private String player;
    private String game = "Reversi";
    private int rating;
    private Date ratedon;
    @Id
    @GeneratedValue
    private int ident; //identifikator

    //doplneny prazdny konstruktor
    public Rating() {}
    public Rating(String player, int rating, Date ratedon) {
        this.player = player;
        this.rating = rating;
        this.ratedon = ratedon;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getRatedon() {
        return ratedon;
    }

    public void setRatedon(Date ratedon) {
        this.ratedon = ratedon;
    }

    public int getIdent() {
        return ident;
    }

    public void setIdent(int ident) {
        this.ident = ident;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Rating{");
        sb.append("player='").append(player).append('\'');
        sb.append(", game='").append(game).append('\'');
        sb.append(", rating=").append(rating);
        sb.append(", ratedon=").append(ratedon);
        sb.append('}');
        return sb.toString();
    }
}









