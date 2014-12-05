package br.unb.gol;

/**
 *
 * @author zidenis
 */
public class Memento {
    private Cell[][] cells;
    private Statistics stats;
    
    public Memento(Cell[][] cells, Statistics statistics) {
        this.cells = cells;
        this.stats = statistics;
    }
    
    public Cell[][] getCells() {
        return cells;
    }

    public Statistics getStatistics() {
        return stats;
    }

    public void setStatistics(Statistics statistics) {
        this.stats = statistics;
    }
    
    public Memento duplicate() {
        Statistics newStatistics = new Statistics(stats.getCreatedCells(), stats.getGeneratedCells(), stats.getKilledCells(), stats.getAlivedCells(), stats.getNumOfGenerations());
        Memento newState = new Memento(new Cell[Main.GRID_HEIGHT][Main.GRID_WIDTH], newStatistics);
        for (int i = 0; i < Main.GRID_HEIGHT; i++) {
            for (int j = 0; j < Main.GRID_WIDTH; j++) {
                newState.getCells()[i][j] = this.getCells()[i][j].duplicate();
            }
        }
        return newState;
    }
}
