package br.unb.cic.lp.gol;

import java.security.InvalidParameterException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Classe que atua como um controlador do padrao MVC, separando os componentes
 * da camada de apresentacao e model.
 *
 * @author rbonifacio
 */
public class GameController {

    private GameEngine engine;
    private GameView board;
    private Statistics statistics;

    private boolean running;
    private int delayTime = 1000;

    public GameEngine getEngine() {
        return engine;
    }

    public void setEngine(GameEngine engine) {
        this.engine = engine;
    }

    public GameView getBoard() {
        return board;
    }

    public void setBoard(GameView board) {
        this.board = board;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public void start() {
        running = true;
        nextGeneration();
    }

    public void stop() {
        running = false;
    }

    public void halt() {
        //oops, nao muito legal fazer sysout na classe Controller
        System.out.println("\n \n");
        statistics.display();
        System.exit(0);
    }

    public void makeCellAlive(int line, int col) {
        try {
            engine.makeCellAlive(line, col);
            board.update();
        } catch (InvalidParameterException e) {
            System.out.println(e.getMessage());
        }
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
}
