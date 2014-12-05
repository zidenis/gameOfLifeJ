/**
 * Game of Life
 * @author zidenis
 * @version 1.0
 * @since dec/2014
*/
package br.unb.gol;

/**
 * Console UI component
 * @version 1.0
 * @author zidenis
 */
public class ConsoleGrid implements Runnable {

    private static final String LINE = "---";
    private static final String DEAD_CELL = "[ ]";
    private static final String ALIVE_CELL = "[@]";
    GameController controller;

    public ConsoleGrid(GameController controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        System.out.print("\n\n");
        printGrid();
        printStatistics();
        //TODO get user input from console without block the program
        //printOptions();
    }

    private void printGrid() {
        printGridRowOfNumbers();
        printGridLine();
        for (int i = 0; i < Main.GRID_HEIGHT; i++) {
            System.out.print(((i < 10) ? " " + i : i) + "| ");
            for (int j = 0; j < Main.GRID_WIDTH; j++) {
                System.out.print(controller.isCellAlive(i, j) ? ALIVE_CELL : DEAD_CELL);
            }
            System.out.println(" |" + i);
        }
        printGridLine();
        printGridRowOfNumbers();
    }

    /*
     * Imprime os identificadores das colunas na primeira linha do tabuleiro
     */
    private void printGridRowOfNumbers() {
        System.out.print("     ");
        for (int j = 0; j < Main.GRID_WIDTH; j++) {
            System.out.print(j + (j < 10 ? "  " : " "));
        }
        System.out.print("\n");
    }

    /* Imprime uma linha usada como separador das linhas do tabuleiro */
    private void printGridLine() {
        System.out.print("   ");
        for (int j = 0; j < Main.GRID_WIDTH; j++) {
            System.out.print(LINE);
        }
        System.out.print("--\n");
    }

    private void printStatistics() {
        Statistics stats = controller.getStatistics();
        System.out.println("\nCreated Cells: " + stats.getCreatedCells());
        System.out.println("Generated Cells: " + stats.getGeneratedCells());
        System.out.println("Killed Cells: " + stats.getKilledCells());
        System.out.println("Alived Cells: " + stats.getAlivedCells());
        System.out.println("Number of Generations: " + stats.getNumOfGenerations());
    }

    private void printOptions() {
        System.out.print("\n");
        for (ConsoleOption opt : ConsoleOption.values()) {
            System.out.print("  [" + opt.getValue() + "]" + opt);
        }
    }
}
