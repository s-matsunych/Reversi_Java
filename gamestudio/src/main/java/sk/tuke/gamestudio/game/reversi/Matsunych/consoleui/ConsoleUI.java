package sk.tuke.gamestudio.game.reversi.Matsunych.consoleui;



import org.springframework.beans.factory.annotation.Autowired;
import sk.tuke.gamestudio.game.reversi.Matsunych.core.Fleld;
import sk.tuke.gamestudio.game.reversi.Matsunych.core.GameState;
import sk.tuke.gamestudio.server.entity.Comment;
import sk.tuke.gamestudio.server.entity.Rating;
import sk.tuke.gamestudio.server.entity.Score;
import sk.tuke.gamestudio.server.service.*;

import java.lang.reflect.Field;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleUI {

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    Scanner in = new Scanner(System.in);
    Pattern patern_Yes = Pattern.compile("(YES)|(Y)|(YE)");
    Pattern patern_No = Pattern.compile("(NO)|(N)");
   // ScoreServiceJDBC scoreService = new ScoreServiceJDBC();
   // CommentServiceJDBC commentService = new CommentServiceJDBC();
   // RatingServiceJDBC ratingService = new RatingServiceJDBC();

    private Fleld field;
    private GameState gameState;
    private String player_1;
    private String player_2;

   @Autowired
   private ScoreService  scoreService;
   @Autowired
   private CommentService commentService;
   @Autowired
   private RatingService ratingService;



    public void run() throws CommentException {
        player_1 = inPlayer(1);
        player_2 = inPlayer(2);
        play();
    }
    public void play() throws CommentException {


        gameState = GameState.PLAYING;
        this.field = new Fleld();
        field.genrerFled();
        field.printFled();
        move();
        scoreService.addScore(new Score(player_1,field.blacks(),new java.util.Date()));
        scoreService.addScore(new Score(player_2,field.whites(),new java.util.Date()));
        try {
            addComment_1();
        } catch (CommentException e) {
            e.printStackTrace();
        }
        addRating();
        try {
            addComment_2();
        } catch (CommentException e) {
            e.printStackTrace();
        }
        addRating2();
    }
    private int scan_X(){

        try {

            System.out.print("X - ");
            String string = in.next();
            int x = Integer.parseInt(string);
            return x;
        }
        catch (NumberFormatException e){
            System.out.println("No int format");
            return -1;
        }

    }

    private int scan_Y(){
        try {
            System.out.print("Y - ");
            String string = in.next();
            int y = Integer.parseInt(string);
            return y;
        }
        catch (NumberFormatException e){
            System.out.println("No int format");
            return -1;
        }

    }


    public void move() throws CommentException {
        int s = 0;
        while (gameState == GameState.PLAYING) {

            printPlayer(s);
            try {
                int x = scan_X();
                int y = scan_Y();
                if (field.chekUp(s, x, y) || field.chekDown(s, x, y) || field.chekLeft(s, x, y) || field.chekRight(s, x, y) || field.chekLU(s, x, y) || field.chekLD(s, x, y) || field.chekRU(s, x, y) || field.chekRD(s, x, y)) {
                    field.chekAndRevers(s, x, y);
                    field.printFled();
                } else {
                    System.out.println("Incorrect choice");
                    continue;
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("X or Y More " + (field.getX() - 1) + " or less " + 0);
                continue;
            }


            System.out.print("Black - " + field.blacks());
            System.out.println("    White - " + field.whites());
            s++;
            gameState(s);

        }
    }

    private String inPlayer(int i){
        String player = null;
        String player2 = null;
        if(i == 1 && player == null){
            System.out.print("Set the name of the first player - ");
            player = in.next();
            return player;
        }
        if(i == 2 && player2 == null){
            System.out.print("Set the name of the second player - ");
            player2 = in.next();
            return player2;
        }
        return null;

    }

    private void printPlayer(int s) {
        if (s % 2 == 0) System.out.println("BLACK - " + player_1);
        if (s % 2 != 0) System.out.println("RED - " + player_2);
    }

    public boolean checkGameState(int s) {

        for (int y = 0; y < field.getY(); y++) {
            for (int x = 0; x < field.getX(); x++) {

                if (field.chekUp(s, x, y) || field.chekDown(s, x, y) || field.chekLeft(s, x, y) || field.chekRight(s, x, y) || field.chekLU(s, x, y) || field.chekLD(s, x, y) || field.chekRU(s, x, y) || field.chekRD(s, x, y)) {
                    return true;
                }

            }

        }
        return false;
    }

    public void gameState(int s) throws CommentException {
        if (!checkGameState(s)) {
            if (field.blacks() > field.whites()) System.out.println(ANSI_RED + "Winner BLACK - " + player_1 + ANSI_RESET);
            if (field.blacks() < field.whites()) System.out.println(ANSI_RED + "Winner RED - " + player_2 + ANSI_RESET);
            if (field.blacks() == field.whites()) System.out.println(ANSI_RED + "DRWA" + player_1 + " = " + player_2 + ANSI_RESET);
            scoreService.addScore(new Score(player_1,field.blacks(),new java.util.Date()));
            scoreService.addScore(new Score(player_2,field.whites(),new java.util.Date()));
            game_Restart();

        }
    }

    public void game_Restart() throws CommentException {
        System.out.println("Want to repeat the game?(Yes/No)");
        String string = in.next().toUpperCase();
        Matcher matcher = patern_Yes.matcher(string);
        Matcher matcher2 = patern_No.matcher(string);
        if(matcher.find()){
            play();
        }
        else if(matcher2.find()) gameState = GameState.SOLVED;
        else game_Restart();
    }

    public void addComment_1() throws CommentException {
        System.out.println(player_1 +  " - Want to leave a comment?(Yes/No)");
        String string = in.next().toUpperCase();
        Matcher matcher = patern_Yes.matcher(string);
        Matcher matcher2 = patern_No.matcher(string);
        if(matcher.find()){
            System.out.print("Comment - ");
            String comment = in.next();
            if(comment.length() < 121)commentService.addComment(new Comment(player_1,comment,new java.util.Date()));
            else {
                System.out.println("Too long comment. Lim 121 Symbol");
                //addComment_1();
            }
        }else if(matcher2.find()) return;
        else if (!matcher.find() && !matcher2.find())addComment_1();
    }

    public void addComment_2() throws CommentException {
        System.out.println(player_2 +  " - Want to leave a comment?(Yes/No)");
        String string = in.next().toUpperCase();
        Matcher matcher = patern_Yes.matcher(string);
        Matcher matcher2 = patern_No.matcher(string);
        if(matcher.find()){
            System.out.print("Comment - ");
            String comment = in.next();
            if(comment.length() < 121)commentService.addComment(new Comment(player_2,comment,new java.util.Date()));
            else {
                System.out.println("Too long comment. Lim 121 Symbol");
                //addComment_2();
            }
        }else if(matcher2.find()) return;

        else if (!matcher.find() && !matcher2.find())addComment_2();
    }


    public void addRating(){
        System.out.println(player_1 +  " - Want to rate our app?(Yes/No)");
        String string = in.next().toUpperCase();
        Matcher matcher = patern_Yes.matcher(string);
        Matcher matcher2 = patern_No.matcher(string);
        if(matcher.find()){
            try {
                System.out.print("Rating(0-5) - ");
                String string1 = in.next();
                int rate = Integer.parseInt(string1);
                if( rate>=0 && rate<=5){
                    ratingService.setRating(new Rating(player_1,rate,new java.util.Date()));
                }else if(matcher2.find())return;
                else if (!matcher.find() && !matcher2.find())addRating();
            }
            catch (NumberFormatException e){
                System.out.println("No int format");
                addRating();
            } catch (RatingException e) {
                e.printStackTrace();
            }
        }
    }

    public void addRating2(){
        System.out.println(player_2 +  " - Want to rate our app?(Yes/No)");
        String string = in.next().toUpperCase();
        Matcher matcher = patern_Yes.matcher(string);
        Matcher matcher2 = patern_No.matcher(string);
        if(matcher.find()){
            try {
                System.out.print("Rating(0-5) - ");
                String string1 = in.next();
                int rate = Integer.parseInt(string1);
                if( rate>=0 && rate<=5){
                    ratingService.setRating(new Rating(player_2,rate,new java.util.Date()));
                }else if(matcher2.find())return;
                else if (!matcher.find() && !matcher2.find())addRating2();
            }
            catch (NumberFormatException e){
                System.out.println("No int format");
                addRating2();
            } catch (RatingException e) {
                e.printStackTrace();
            }
        }
    }

}




