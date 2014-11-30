package br.unb.cic.lp.gol;

public class Main {
    public static final int GRID_WIDTH = 25;
    public static final int GRID_HEIGHT = 25;
    
    public static void main(String args[]) {
        GameController controller = new GameController();
        controller.initController();
        GameView board = new GameViewSwing(controller);
        controller.setBoard(board);
    }
}
