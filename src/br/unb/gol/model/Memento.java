/**
 * Game of Life
 * @author zidenis
 * @version 1.0
 * @since dec/2014
*/
package br.unb.gol.model;

import static br.unb.gol.Main.GRID_HEIGHT;
import static br.unb.gol.Main.GRID_WIDTH;

public class Memento {
    private final Cell[][] cells;
    private Statistics stats;
    
    public Memento(Cell[][] cells, Statistics statistics) {
        this.cells = cells;
        this.stats = statistics;
    }
    
    protected Cell[][] getCells() {
        return cells;
    }

    protected Statistics getStatistics() {
        return stats;
    }

    protected void setStatistics(Statistics statistics) {
        this.stats = statistics;
    }
    
    protected Memento duplicate() {
        Statistics newStatistics = new Statistics(stats.getCreatedCells(), stats.getGeneratedCells(), stats.getKilledCells(), stats.getAlivedCells(), stats.getNumOfGenerations());
        Memento newState = new Memento(new Cell[GRID_HEIGHT][GRID_WIDTH], newStatistics);
        for (int i = 0; i < GRID_HEIGHT; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                newState.getCells()[i][j] = this.getCells()[i][j].duplicate();
            }
        }
        return newState;
    }
}
