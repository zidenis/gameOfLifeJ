/**
 * Game of Life
 * @author zidenis
 * @version 1.0
 * @since dec/2014
*/
package br.unb.gol.controller;

import br.unb.gol.model.GameRule;
import br.unb.gol.model.GameRuleList;
import br.unb.gol.model.GameEngine;
import br.unb.gol.model.Statistics;
import br.unb.gol.view.GameView;

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

    private final GameEngine engine;

    private boolean running;
    private int delayTime = 1000;

    public GameController(GameEngine engine) {
        this.engine = engine;
    }

    public void setView(GameView view) {
        engine.attachView(view);
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
        
    }

    public void nextGeneration() {
        engine.nextGeneration();
        continuousRunningTest();
    }
    
    private void continuousRunningTest() {
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

    
    public void previousGeneration() {
        engine.restorePreviousGeneraion();
    }
    
    public int numOfSavedGenerations() {
        return engine.getNumSavedStates();
    }

    public void createCell(int line, int col) {
        try {
            engine.createCell(line, col);
        } catch (InvalidParameterException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public boolean isCellAlive(int lin, int col) {
        return engine.isCellAlive(lin, col);
    }

    public void killCell(int lin, int col) {
        engine.killCell(lin, col);
    }

    public void killAllCells() {
        engine.killAllCells();
        halt();
    }
    
    public GameRuleList getRules() {
        return engine.getRules();
    }
    
    public void setActiveRule(int index) {
        engine.getRules().setActiveRule(index);
        //System.out.println(engine.getRules().getActiveRule() + "selected");
    }
    
    public void addRule(GameRule rule) {
        engine.getRules().addNewRule(rule);
    }

    public boolean isRunning() {
        return running;
    }
}
