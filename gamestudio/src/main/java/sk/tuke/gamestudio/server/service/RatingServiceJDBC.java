package sk.tuke.gamestudio.server.service;

//import sk.tuke.gamestudio.entity.Rating;

import sk.tuke.gamestudio.server.entity.Rating;

import java.sql.*;
import java.sql.Date;
//CREATE TABLE score (
//        player VARCHAR(64) NOT NULL,
//        game VARCHAR(64) NOT NULL,
//        points INTEGER NOT NULL,
//        playedon TIMESTAMP NOT NULL
//    );

public class RatingServiceJDBC implements RatingService {

    public static final String URL = "jdbc:postgresql://localhost/postgres";
    public static final String USER = "postgres";
    public static final String PASSWORD = "pondelok892";

    public static final String INSERT_RARING =
            "INSERT INTO rating (game, player, rating, ratedon) VALUES (?, ?, ?, ?)";

    public static final String SELECT_RATING =
            "SELECT player, rating FROM rating where player = player;";

    public static final String SELECT_AVERAGE_RATING = "SELECT AVG (rating) FROM rating;";

    @Override
    public void setRating(Rating rating) throws RatingException {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try (PreparedStatement ps = connection.prepareStatement(INSERT_RARING)) {
                ps.setString(1, rating.getGame());
                ps.setString(2, rating.getPlayer());
                ps.setInt(3, rating.getRating());
                ps.setDate(4, new Date(rating.getRatedon().getTime()));

                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new ScoreException("Error saving rating", e);
        }
    }

    @Override
    public int getAverageRating(String game) throws RatingException {
        int a = 0;

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try (PreparedStatement ps = connection.prepareStatement(SELECT_AVERAGE_RATING)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Rating rating = new Rating(null, rs.getInt(1), null);
                        a = rating.getRating();

                    }

                }
            }
        } catch (SQLException e) {
            throw new ScoreException("Error loading rating", e);
        }
        return a;
    }

    @Override
    public int getRating(String game, String player1) throws RatingException {
        int r = 0;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try (PreparedStatement ps = connection.prepareStatement("SELECT  rating FROM rating where player = ('"+player1+"');")) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Rating rating = new Rating(null, rs.getInt(1), null);
                        r = rating.getRating();
                    }
                }
            }
        } catch (SQLException e) {
            throw new ScoreException("Error loading rating", e);
        }
        return r;
    }
}
