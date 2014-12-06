/**
 * Game of Life
 * @author zidenis
 * @version 1.0
 * @since dec/2014
*/
package br.unb.gol.model;

import br.unb.gol.view.GameView;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa um ambiente (environment) do jogo GameOfLife.
 *
 * Essa implementacao eh nao inifinita, ou seja, nem todas as celulas possuem
 oito celulas vizinhas. Por exemplo, nghbrLine celula de coordenada (0,0) possui
 apenas tres celulas vizinhas, (0,1), (1,0) e (1,1).

 Um ambiente eh representado como um array bidimensional de celulas, com
 altura (height) e comprimento (width).
 *
 * @version 2.0
 * @author rbonifacio (v1)
 * @author zidenis (v2)
 */
public class GameEngine {

    private final int MAX_SAVED_SATES = 10;

    private final int height;
    private final int width;

    private Memento activeState;
    private final List<Memento> savedStates;
    private final GameRuleList rules;
    
    private final List<GameView> listeners;

    /**
     * Construtor da classe Environment.
     *
     * @param height dimensao vertical do ambiente
     * @param width dimentsao horizontal do ambiente
     * @param statistics objeto de estatisticas
     */
    public GameEngine(int height, int width, Statistics statistics) {
        this.height = height;
        this.width = width;
        listeners = new ArrayList();
        savedStates = new ArrayList<>();
        activeState = new Memento(new Cell[height][width], statistics);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                activeState.getCells()[i][j] = new Cell();
            }
        }
        rules = new GameRuleList();
    }

    private void addState(Memento state) {
        if (savedStates.size() > MAX_SAVED_SATES) {
            savedStates.remove(0);
        }
        savedStates.add(state);
    }

    public void restorePreviousGeneraion() {
        activeState = savedStates.remove(savedStates.size() - 1);
        updateViews();
    }

    public int getNumSavedStates() {
        return savedStates.size();
    }

    public Statistics getStatistics() {
        return activeState.getStatistics();
    }

    public GameRuleList getRules() {
        return rules;
    }

    public Memento getActiveState() {
        return activeState;
    }

    /**
     * Calcula uma nova geracao do ambiente. Essa implementacao utiliza o
 algoritmo do Conway, ou seja:

 nghbrLine) uma celula morta com exatamente tres celulas vizinhas vivas se torna
 uma celula viva.

 nghbrClmn) uma celula viva com duas ou tres celulas vizinhas vivas permanece
 viva.

 c) em todos os outros casos nghbrLine celula morre ou continua morta.
     */
    public void nextGeneration() {
        addState(activeState); // Adiciona estado atual na lista de estados salvos
        activeState = activeState.duplicate();
        List<Cell> mustGenerate = new ArrayList<>();
        List<Cell> mustKill = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (!activeState.getCells()[i][j].isAlive()) {
                    if (rules.getActiveRule().shouldRevive(numOfNghbrAliveCells(i, j))) {
                        mustGenerate.add(activeState.getCells()[i][j]);
                    }
                } else if (!rules.getActiveRule().shouldKeepAlive(numOfNghbrAliveCells(i, j))) {
                    mustKill.add(activeState.getCells()[i][j]);
                }
            }
        }
        for (Cell cell : mustGenerate) {
            cell.rise();
            activeState.getStatistics().incGeneratedCells();
        }
        for (Cell cell : mustKill) {
            cell.kill();
            activeState.getStatistics().incKilledCells();
        }
        activeState.getStatistics().incNumOfGenerations();
        updateViews();
    }

    /**
     * Torna nghbrLine celula de posicao (i, j) viva
     *
     * @param i posicao vertical da celula
     * @param j posicao horizontal da celula
     *
     * @throws InvalidParameterException caso nghbrLine posicao (i, j) nao seja valida.
     */
    private void generateCell(int i, int j) throws InvalidParameterException {
        if (validPosition(i, j)) {
            activeState.getCells()[i][j].rise();
            activeState.getStatistics().incGeneratedCells();
        } else {
            throw new InvalidParameterException("Invalid position (" + i + ", " + j + ")");
        }
        updateViews();
    }

    public void createCell(int i, int j) throws InvalidParameterException {
        if (validPosition(i, j)) {
            activeState.getCells()[i][j].rise();
            activeState.getStatistics().incCreatedCells();
        } else {
            throw new InvalidParameterException("Invalid position (" + i + ", " + j + ")");
        }
        updateViews();
    }

    /**
     * Verifica se uma celula na posicao (i, j) estah viva.
     *
     * @param i Posicao vertical da celula
     * @param j Posicao horizontal da celula
     * @return Verdadeiro caso nghbrLine celula de posicao (i,j) esteja viva.
     *
     * @throws InvalidParameterException caso nghbrLine posicao (i,j) nao seja valida.
     */
    public boolean isCellAlive(int i, int j) throws InvalidParameterException {
        if (validPosition(i, j)) {
            return activeState.getCells()[i][j].isAlive();
        } else {
            throw new InvalidParameterException("Invalid position (" + i + ", " + j + ")");
        }
    }

    /*
     * Computa o numero de celulas vizinhas vivas, dada uma posicao no ambiente
     * de referencia identificada pelos argumentos (line,clmn).
     */
    private int numOfNghbrAliveCells(int line, int clmn) {
        int alive = 0;
        if (validPosition(line, clmn)) {
            for (int nghbrLine : new int[]{line - 1, line, line + 1}) {
                for (int nghbrClmn : new int[]{clmn - 1, clmn, clmn + 1}) {
                    int i = nghbrLine;
                    int j = nghbrClmn;
                    //Infinite grid transformation
                    if (i== -1) {
                        i = height - 1;
                    } else if (i == height) {
                        i = 0;
                    }
                    if (j == -1) {
                        j = width - 1;
                    } else if (j == width) {
                        j = 0;
                    }
                    if ((!(nghbrLine == line && nghbrClmn == clmn)) &&
                        (activeState.getCells()[i][j].isAlive())) {
                            alive++;
                        }
                }
            }
        }
        return alive;
    }

// Alternative implmentation
//    private int numOfNghbrAliveCells(int line, int clmn) {
//        int alive = 0;
//        for (int nghbrLine = line - 1; nghbrLine <= line + 1; nghbrLine++) {
//            for (int nghbrClmn = clmn - 1; nghbrClmn <= clmn + 1; nghbrClmn++) {
//                int i = nghbrLine;
//                int j = nghbrClmn;
//                //Infinite grid transformation
//                if (nghbrLine == -1) i = height-1;
//                else if (nghbrLine == height) i = 0;
//                if (nghbrClmn == -1) j = width-1;
//                else if (nghbrClmn == width) j = 0;
//                if (validPosition(i, j) && 
//                   (!(nghbrLine == line && nghbrClmn == clmn)) &&
//                   (activeState.getCells()[i][j].isAlive())) {
//                    alive++;
//                }
//            }
//        }
//        return alive;
//    }

    /*
     * Verifica se uma posicao (nghbrLine, nghbrClmn) referencia uma celula valida no tabuleiro.
     */
    private boolean validPosition(int a, int b) {
        return a >= 0 && a < height && b >= 0 && b < width;
    }

    public void killCell(int i, int j) throws InvalidParameterException {
        if (validPosition(i, j)) {
            activeState.getCells()[i][j].kill();
            activeState.getStatistics().incKilledCells();
        } else {
            throw new InvalidParameterException("Invalid position (" + i + ", " + j + ")");
        }
        updateViews();
    }

    public void killAllCells() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (isCellAlive(i, j)) {
                    activeState.getCells()[i][j].kill();
                    activeState.getStatistics().incKilledCells();
                }
            }
        }
        updateViews();
    }

    public void reset() {
        activeState.setStatistics(new Statistics());
        killAllCells();
    }
    
    public void attachView(GameView listener) {
        listeners.add(listener);
    }
    
    public void detachView(GameView listener) {
        listeners.remove(listener);
    }
    
    public void updateViews() {
        for (GameView gameView: listeners) {
            gameView.update();
        }
    }
}
