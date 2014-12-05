package br.unb.gol;

import java.util.Scanner;

/**
 * Atua como um componente de apresentacao (view), exibindo o estado atual do
 * game com uma implementacao baseada em caracteres ASCII.
 *
 * @version 2.0
 * @author rbonifacio (v1)
 * @author zidenis (v2)
 */
public class GameViewConsole implements GameView {

    private final GameController controller;

    /**
     * Construtor da classe GameBoard
     *
     * @param controller gameController
     */
    public GameViewConsole(GameController controller) {
        this.controller = controller;
        update();
    }

    /**
     * Atualiza o componente view (representado pela classe GameBoard),
     * possivelmente como uma resposta a uma atualiza��o do jogo.
     */
    @Override
    public void update() {
        Thread gridThread = new Thread(new ConsoleGrid(controller));
        gridThread.start();
//TODO get user input from console without block the program
//        if (!controller.isRunning()) {
//            readOption();
//        }
    }

    public void readOption() {
        //TODO get user input from console without block the program
        ConsoleOption opt = null;
        switch (opt) {
            case CREATE_CELL:
                makeCellAlive();
                break;
            case PLAY:
                controller.start();
                break;
            case NEXT_GENERATION:
                controller.nextGeneration();
                break;
            case CLEAR:
                controller.killAllCells();
                break;
            case RESET:
                controller.reset();
                break;
        }
    }

    private void makeCellAlive() {
        int i, j = 0;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("Row number (0.." + Main.GRID_HEIGHT + "): ");
            i = scanner.nextInt();
            System.out.print("Column number (0.." + Main.GRID_WIDTH + "): ");
            j = scanner.nextInt();
        } while (!validPosition(i, j));
        controller.createCell(i, j);
    }

    private boolean validPosition(int i, int j) {
        return i >= 0 && i < Main.GRID_HEIGHT && j >= 0 && j < Main.GRID_WIDTH;
    }
}
