package br.unb.cic.lp.gol;

public class Main {

    public static final int GRID_HEIGHT = 20;
    public static final int GRID_WIDTH = 30;
    
    public static void main(String args[]) {
        GameController controller = new GameController();

        Statistics statistics = new Statistics();

        GameEngine engine = new GameEngine(GRID_HEIGHT, GRID_WIDTH, statistics);

        GameView board = new GameView(controller, engine);
        GameViewSwing view = new GameViewSwing(controller, engine);
        view.setVisible(true);

        controller.setBoard(board);
        controller.setEngine(engine);
        controller.setStatistics(statistics);
        controller.start();
    }
}
