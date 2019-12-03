package sk.tuke.gamestudio.game.reversi.Matsunych.core;

public  class  Tile {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    private TileState state;

    public Tile() {
        this.state = TileState.EMPTY;
    }


    public String printState(){
        if(state == TileState.EMPTY )return ANSI_CYAN + "*" + ANSI_RESET;
        if(state == TileState.BLACK )return ANSI_WHITE + "1" + ANSI_RESET;
        if(state == TileState.WHITE )return ANSI_RED + "2" + ANSI_RESET;
        return null;
    }
    public TileState getState() {
        return state;
    }

    public void setState(TileState state) {
        this.state = state;
    }




}
 /* public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

                                                                потом якщо буде працювати зменю цвета
    private String E = ANSI_YELLOW +"●"; //"☆";
    private String B = ANSI_PURPLE +"●";//✪";
    private String W = ANSI_WHITE + "●"; //"★";*/

//    private String E = "☆";
//    private String B = "✪";
//    private String W = "★";
