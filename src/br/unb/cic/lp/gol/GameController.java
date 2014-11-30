package br.unb.cic.lp.gol;

import java.security.InvalidParameterException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Classe que atua como um controlador do padrao MVC, separando os componentes
 * da camada de apresentacao e model.
 *
 * @version 2.0
 * @author rbonifacio (v1)
 * @author zidenis (v2)
 */
public class GameController {

    private GameEngine engine;
    private GameView board;

    private boolean running;
    private int delayTime = 1000;

    public void initController() {
        this.engine = new GameEngine(Main.GRID_HEIGHT, Main.GRID_WIDTH, new Statistics());
    }

    public void setBoard(GameView board) {
        this.board = board;
    }

    public Statistics getStatistics() {
        return engine.getStatistics();
    }
    
    public void setDelayTime(int delayTime) {
        this.delayTime = delayTime;
    }

    public void start() {
        running = true;
        nextGeneration();
    }

    public void halt() {
        running = false;
    }

    public void reset() {
        engine.reset();
        board.update();
    }

    public void nextGeneration() {
        engine.nextGeneration();
        board.update();
        runningTest();
    }

    private void runningTest() {
        if (running) {
            new Timer().schedule(
                    new TimerTask() {
                        @Override
                        public void run() {
                            nextGeneration();
                        }
                    },
                    delayTime
            );
        }
    }

    public void makeCellAlive(int line, int col) {
        try {
            engine.makeCellAlive(line, col);
            board.update();
        } catch (InvalidParameterException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public boolean isCellAlive(int lin, int col) {
        return engine.isCellAlive(lin, col);
    }

    public void killCell(int lin, int col) {
        engine.killCell(lin, col);
        board.update();
    }

    public void killAllCells() {
        engine.killAllCells();
        halt();
        board.update();
    }
}
