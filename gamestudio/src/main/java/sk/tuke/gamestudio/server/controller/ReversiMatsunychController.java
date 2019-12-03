package sk.tuke.gamestudio.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import sk.tuke.gamestudio.game.reversi.Matsunych.core.Fleld;
import sk.tuke.gamestudio.game.reversi.Matsunych.core.GameState;
import sk.tuke.gamestudio.game.reversi.Matsunych.core.Tile;
import sk.tuke.gamestudio.game.reversi.Matsunych.core.TileState;
import sk.tuke.gamestudio.game.reversi.Matsunych.webui.Command;
import sk.tuke.gamestudio.server.entity.Comment;
import sk.tuke.gamestudio.server.entity.Rating;
import sk.tuke.gamestudio.server.entity.Score;
import sk.tuke.gamestudio.server.service.*;

import javax.servlet.ServletContext;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

//import sk.tuke.gamestudio.entity.Score;
//import sk.tuke.gamestudio.game.mines.myname.webui.Command;
//import sk.tuke.gamestudio.game.mines.myname.webui.WebUI;
//import sk.tuke.gamestudio.service.ScoreService;

//http://localhost:8080/reversi-Matsunych
@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
@RequestMapping("reversi-Matsunych")
public class ReversiMatsunychController {
    private static Map<Integer, Fleld> fields = new HashMap<>();
    @Autowired
    private ServletContext servletContext;

    private Fleld field ;//= new Fleld();
    private int displayId;
    private String loggedUser1;
    private String loggedUser2;
    private int s;
    private String Blacks;
    private String Reds;
    private int blacks;
    private int reds;
    private GameState gameState = GameState.PLAYING;
    @Autowired
    private RatingService ratingService;

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private CommentService commentService;

    //private final ScoreService scoreService;






    @RequestMapping("")
    public String reversi(String row, String column, Model model) throws CommentException, RatingException {
//        gameState = GameState.PLAYING;
//        if (field == null)
//            newGame();
//        try {
//          if (    field.chekUp(s, Integer.parseInt(column), Integer.parseInt(row)) || field.chekDown(s, Integer.parseInt(column), Integer.parseInt(row)) ||
//                  field.chekLeft(s,Integer.parseInt(column),Integer.parseInt(row)) || field.chekRight(s, Integer.parseInt(column), Integer.parseInt(row)) ||
//                  field.chekLU(s, Integer.parseInt(column), Integer.parseInt(row)) || field.chekLD(s, Integer.parseInt(column), Integer.parseInt(row)) ||
//                  field.chekRU(s, Integer.parseInt(column), Integer.parseInt(row)) || field.chekRD(s, Integer.parseInt(column),Integer.parseInt(row))) {
//              field.chekAndRevers(s, Integer.parseInt(column), Integer.parseInt(row));
//              s++;
//              updateModel(model);
//          }else if(!checkGameState(s)){
//              gameState = GameState.SOLVED;
//              blacks = field.blacks();
//              reds = field.whites();
//              scoreService.addScore(new Score(loggedUser1,field.blacks(),new java.util.Date()));
//              scoreService.addScore(new Score(loggedUser2,field.whites(),new java.util.Date()));
//
//              logout(model);
//
//          }
//
//        } catch (NumberFormatException e) {
//            //Jaro: Zle poslane nic sa nedeje
//            e.printStackTrace();
//        } catch (RatingException e) {
//            e.printStackTrace();
//        }

//        updateModel(model);
        field(row,column,model);
        return "reversi-Matsunych";
    }

    @RequestMapping("/field")
    public String field(String row, String column, Model model) throws CommentException, RatingException {
        gameState = GameState.PLAYING;
        if (field == null)
            newGame();
        try {
            if (    field.chekUp(s, Integer.parseInt(column), Integer.parseInt(row)) || field.chekDown(s, Integer.parseInt(column), Integer.parseInt(row)) ||
                    field.chekLeft(s,Integer.parseInt(column),Integer.parseInt(row)) || field.chekRight(s, Integer.parseInt(column), Integer.parseInt(row)) ||
                    field.chekLU(s, Integer.parseInt(column), Integer.parseInt(row)) || field.chekLD(s, Integer.parseInt(column), Integer.parseInt(row)) ||
                    field.chekRU(s, Integer.parseInt(column), Integer.parseInt(row)) || field.chekRD(s, Integer.parseInt(column),Integer.parseInt(row))) {
                field.chekAndRevers(s, Integer.parseInt(column), Integer.parseInt(row));
                s++;
                updateModel(model);
            }else if(!checkGameState(s)){
                gameState = GameState.SOLVED;
                blacks = field.blacks();
                reds = field.whites();
                scoreService.addScore(new Score(loggedUser1,field.blacks(),new java.util.Date()));
                scoreService.addScore(new Score(loggedUser2,field.whites(),new java.util.Date()));

                logout(model);

            }

        } catch (NumberFormatException e) {
            //Jaro: Zle poslane nic sa nedeje
            e.printStackTrace();
        } catch (RatingException e) {
            e.printStackTrace();
        }

        updateModel(model);

        return "field";
    }



    public String getPoints(){
    StringBuilder sb = new StringBuilder();
       Blacks = Integer.toString (field.blacks());
       Reds = Integer.toString (field.whites());
       sb.append("Blacks = " + Blacks + " " + "Reds = " + Reds);
        return sb.toString();
}
    public String DefinitionPlayer(){
        StringBuilder sb = new StringBuilder();
        if(loggedUser1 != null && loggedUser2 != null) {

            if (s % 2 == 0) sb.append("<h2><p class=\"blink\">Move " + loggedUser1 + " BLACK " + "</p></h2>");
            if (s % 2 != 0)
                sb.append("<h2 style=\"color:#FF0011\"><p class=\"blink\">Move " + loggedUser2 + " RED " + "</p></h2>");
            //sb.append("Blacks = " + Blacks + " " + "Reds = " + Reds);
        }
            return sb.toString();

    }

//darkred #ff0000
public String getHtmlField() {
    return getHtmlField(field);
}


    public String getHtmlField(Fleld field) {
       //field.genrerFled();

        StringBuilder sb = new StringBuilder();
        sb.append("<table class='field'>\n");
        for (int row = 0; row < field.getY(); row++) {
            sb.append("<tr>\n");
            for (int column = 0; column < field.getX(); column++) {
                Tile tile = field.getTile(column, row);
                sb.append("<td>\n");
                sb.append("<a href='" +
                        String.format("%s/reversi-Matsunych?row=%s&column=%s",  servletContext.getContextPath(),row, column)
                        + "'>\n");

                if(tile.getState() == TileState.BLACK)sb.append("<img src='/images/Black.png'/>");
                if(tile.getState() == TileState.EMPTY)sb.append("<img src='/images/Empty.png'/>");
                if(tile.getState() == TileState.WHITE)sb.append("<img src='/images/Read.png'/>");

                sb.append("</a>\n");
                sb.append("</td>\n");
            }
            sb.append("</tr>\n");
        }
        sb.append("</table>\n");

        return sb.toString();
        // return null;
    }




//    public String DefinitionPlayer(int s,Model model) {
////        if (s % 2 == 0) AckivPlayer = Black;
////        if (s % 2 != 0) AckivPlayer = null;
////        updateModel(model);
////
////        return "reversi-Matsunych";
////    }



    @RequestMapping("/new")
    public String newGame(Model model) throws CommentException, RatingException {
        newGame();
        updateModel(model);
        //if (displayId != 0)fields.put(displayId,field);
        return "reversi-Matsunych";
    }

    private void updateModel(Model model) throws CommentException, RatingException {
        model.addAttribute("displayId", displayId);
        model.addAttribute("loggedUser1", loggedUser1);
        model.addAttribute("loggedUser2", loggedUser2);
        model.addAttribute("score", scoreService.getTopScores("Reversi"));
        model.addAttribute("comment",commentService.getComments("Reversi"));
       // model.addAttribute("rating",ratingService.getAverageRating("Reversi"));


    }


    private void newGame() {
        s = 0;
        field = new Fleld();
        field.genrerFled();
        if (displayId != 0)
            fields.put(displayId, field);


    }
    @RequestMapping("/gameState")
    public String gameState(Model model){
        if(checkGameState(s))return String.valueOf(GameState.PLAYING);
        else return String.valueOf(GameState.SOLVED);
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

    @RequestMapping("/login")
    public String login(String login1,String login2, Model model) throws CommentException, RatingException {
        loggedUser1 = login1;
        loggedUser2 = login2;
        if(login1 != null && login2 != null)newGame(model);

        return "reversi-Matsunych";
    }

    @RequestMapping("/logout")
    public String logout(Model model) throws CommentException, RatingException {
        newGame(model);
        loggedUser1 = null;
        loggedUser2 = null;
        updateModel(model);
        return "reversi-Matsunych";
    }

    @RequestMapping("/comment")
    public String Comment(String comment1, String comment2,Model model) throws CommentException, RatingException {
        commentService.addComment(new Comment(loggedUser1,comment1,new java.util.Date()));
        commentService.addComment(new Comment(loggedUser2,comment2,new java.util.Date()));
        updateModel(model);
        return "reversi-Matsunych";
    }
//    @RequestMapping("/printWinner")
    public String printWinner() {
        StringBuilder sb = new StringBuilder();
        if(gameState == GameState.SOLVED) {
            if (blacks > reds) sb.append("<br><h1>WON BLACKS </h1>");
            if (blacks < reds) sb.append("<br><h1>WON REDS </h1>");
            if (field.blacks() == field.whites()) sb.append("<br><h1>Equally</h1>");
        }else sb.append("<br>Proces");
        return sb.toString();

    }
    public String getRatingAVG() throws RatingException {
        StringBuilder sb = new StringBuilder();
        sb.append("<h2>RatingAVG - "+ ratingService.getAverageRating("Reversi")+"</h2>");
        return sb.toString();
    }
    @RequestMapping("/setRating")
    public String setRating(String rate,Model model) throws CommentException, RatingException {
        int r = Integer.parseInt(rate);
        ratingService.setRating(new Rating(loggedUser1,r,new java.util.Date()));
        updateModel(model);
        return "reversi-Matsunych";

    }


//_------------________________------------------_______________________------------------------_____----________-----___


    @RequestMapping("/display/{id}")
    public String display(@PathVariable int id, Model model) throws CommentException, RatingException {
        displayId = id;
        Fleld field = fields.get(id);
        if (field != null)
            model.addAttribute("htmlField", getHtmlField(field));
        updateModel(model);
        return "reversi-Matsunych-view";
    }

    @RequestMapping("/display/pair/{id}")
    public String pair(@PathVariable int id, Model model) throws CommentException, RatingException {
        displayId = id;
        if (field == null)
            newGame();
        fields.put(id, field);
        updateModel(model);
        return "reversi-Matsunych";
    }

    @RequestMapping("/display/unpair")
    public String pair(Model model) throws CommentException, RatingException {
        fields.remove(displayId);
        displayId = 0;
        updateModel(model);
        return "reversi-Matsunych";
    }

}


