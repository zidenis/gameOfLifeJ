package br.unb.cic.lp.gol;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa um ambiente (environment) do jogo GameOfLife.
 *
 * Essa implementacao eh nao inifinita, ou seja, nem todas as celulas possuem
 * oito celulas vizinhas. Por exemplo, a celula de coordenada (0,0) possui
 * apenas tres celulas vizinhas, (0,1), (1,0) e (1,1).
 *
 * Um ambiente eh representado como um array bidimensional de celulas, com
 * altura (height) e comprimento (width).
 *
 * @version 2.0
 * @author rbonifacio (v1)
 * @author zidenis (v2)
 */
public class GameEngine {

    private final int MAX_SAVED_SATES = 10;

    private int height;
    private int width;

    private Memento activeState;
    private List<Memento> savedStates;
    private GameRule rule;

    /**
     * Construtor da classe Environment.
     *
     * @param height dimensao vertical do ambiente
     * @param width dimentsao horizontal do ambiente
     */
    public GameEngine(int height, int width, Statistics statistics) {
        this.height = height;
        this.width = width;
        savedStates = new ArrayList<Memento>();
        activeState = new Memento(new Cell[height][width], statistics);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                activeState.getCells()[i][j] = new Cell();
            }
        }
        rule = new GameRule("Conway's Life", new int[] {2, 3}, new int[] {3});
    }

    private void addState(Memento state) {
        if (savedStates.size() > MAX_SAVED_SATES) {
            savedStates.remove(0);
        }
        savedStates.add(state);
    }

    public void restorePreviousGeneraion() {
        activeState = savedStates.remove(savedStates.size() - 1);
    }

    public int getNumSavedStates() {
        return savedStates.size();
    }

    public Statistics getStatistics() {
        return activeState.getStatistics();
    }

    public void setStrategy(GameRule strategy) {
        this.rule = strategy;
    }

    public GameRule getStrategy() {
        return rule;
    }

    public Memento getActiveState() {
        return activeState;
    }

    /**
     * Calcula uma nova geracao do ambiente. Essa implementacao utiliza o
     * algoritmo do Conway, ou seja:
     *
     * a) uma celula morta com exatamente tres celulas vizinhas vivas se torna
     * uma celula viva.
     *
     * b) uma celula viva com duas ou tres celulas vizinhas vivas permanece
     * viva.
     *
     * c) em todos os outros casos a celula morre ou continua morta.
     */
    public void nextGeneration() {
        addState(activeState); // Adiciona estado atual na lista de estados salvos
        activeState = activeState.duplicate();
        List<Cell> mustGenerate = new ArrayList<Cell>();
        List<Cell> mustKill = new ArrayList<Cell>();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (!activeState.getCells()[i][j].isAlive()) {
                    if (rule.shouldRevive(numberOfNeighborhoodAliveCells(i, j))) {
                        mustGenerate.add(activeState.getCells()[i][j]);
                    }
                } else if (!rule.shouldKeepAlive(numberOfNeighborhoodAliveCells(i, j))) {
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
    }

    /**
     * Torna a celula de posicao (i, j) viva
     *
     * @param i posicao vertical da celula
     * @param j posicao horizontal da celula
     *
     * @throws InvalidParameterException caso a posicao (i, j) nao seja valida.
     */
    private void generateCell(int i, int j) throws InvalidParameterException {
        if (validPosition(i, j)) {
            activeState.getCells()[i][j].rise();
            activeState.getStatistics().incGeneratedCells();
        } else {
            new InvalidParameterException("Invalid position (" + i + ", " + j + ")");
        }
    }

    public void createCell(int i, int j) throws InvalidParameterException {
        if (validPosition(i, j)) {
            activeState.getCells()[i][j].rise();
            activeState.getStatistics().incCreatedCells();
        } else {
            new InvalidParameterException("Invalid position (" + i + ", " + j + ")");
        }
    }

    /**
     * Verifica se uma celula na posicao (i, j) estah viva.
     *
     * @param i Posicao vertical da celula
     * @param j Posicao horizontal da celula
     * @return Verdadeiro caso a celula de posicao (i,j) esteja viva.
     *
     * @throws InvalidParameterException caso a posicao (i,j) nao seja valida.
     */
    public boolean isCellAlive(int i, int j) throws InvalidParameterException {
        if (validPosition(i, j)) {
            return activeState.getCells()[i][j].isAlive();
        } else {
            throw new InvalidParameterException("Invalid position (" + i + ", " + j + ")");
        }
    }

    /**
     * Retorna o numero de celulas vivas no ambiente. Esse metodo eh
     * particularmente util para o calculo de estatisticas e para melhorar a
     * testabilidade.
     *
     * @return numero de celulas vivas.
     */
    public int numberOfAliveCells() {
        return activeState.getStatistics().getAlivedCells();
    }

    /*
     * Computa o numero de celulas vizinhas vivas, dada uma posicao no ambiente
     * de referencia identificada pelos argumentos (i,j).
     */
    private int numberOfNeighborhoodAliveCells(int i, int j) {
        int alive = 0;
        for (int a = i - 1; a <= i + 1; a++) {
            for (int b = j - 1; b <= j + 1; b++) {
                if (validPosition(a, b) && (!(a == i && b == j)) && activeState.getCells()[a][b].isAlive()) {
                    alive++;
                }
            }
        }
        return alive;
    }

    /*
     * Verifica se uma posicao (a, b) referencia uma celula valida no tabuleiro.
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
    }

    public void killAllCells() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (isCellAlive(i, j)) {
                    killCell(i, j);
                }
            }
        }
    }

    public void reset() {
        killAllCells();
        activeState.setStatistics(new Statistics());
    }
}
