package br.unb.gol;

public class Main {
    public static final int GRID_WIDTH = 25;
    public static final int GRID_HEIGHT = 25;
    
    public static void main(String args[]) {
        GameController controller = new GameController(new GameEngine(GRID_HEIGHT, GRID_WIDTH, new Statistics()));
        controller.setView(new GameViewSwing(controller));
        controller.setView(new GameViewConsole(controller));
    }
}
