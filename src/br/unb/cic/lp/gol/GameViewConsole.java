package br.unb.cic.lp.gol;

import java.util.Scanner;

/**
 * Atua como um componente de apresentacao (view), exibindo o estado atual do
 * game com uma implementacao baseada em caracteres ASCII.
 * @version 2.0
 * @author rbonifacio (v1)
 * @author zidenis (v2)
 */
public class GameViewConsole implements GameView {
	private static final String LINE = "+-----+";
	private static final String DEAD_CELL = "|     |";
	private static final String ALIVE_CELL = "|  o  |";
	
	private static final int INVALID_OPTION = 0;
	private static final int MAKE_CELL_ALIVE = 1;
	private static final int NEXT_GENERATION = 2;
	private static final int HALT = 3; 

	private GameEngine engine;
	private GameController controller;

	/**
	 * Construtor da classe GameBoard
	 */
	public GameViewConsole(GameController controller, GameEngine engine) {
		this.controller = controller;
		this.engine = engine;
	}

	/**
	 * Atualiza o componente view (representado pela classe GameBoard),
	 * possivelmente como uma resposta a uma atualiza��o do jogo.
	 */
	public void update() {
		printFirstRow();
		printLine();
		for (int i = 0; i < Main.GRID_HEIGHT; i++) {
			for (int j = 0; j < Main.GRID_WIDTH; j++) {
				System.out.print(controller.isCellAlive(i, j) ? ALIVE_CELL : DEAD_CELL);
			}
			System.out.println("   " + i);
			printLine();
		}
		printOptions();
	}

	private void printOptions() {
		Scanner s = new Scanner(System.in);
		int option;
		System.out.println("\n \n");
		
		do {
			System.out.println("Select one of the options: \n \n"); 
			System.out.println("[1] Make a cell alive");
			System.out.println("[2] Next generation");
			System.out.println("[3] Halt");
		
			System.out.print("\n \n Option: ");
			
			option = parseOption(s.nextLine());
		}while(option == 0);
		
		switch(option) {
			case MAKE_CELL_ALIVE : makeCellAlive(); break;
			case NEXT_GENERATION : nextGeneration(); break;
			case HALT : halt();
		}
	}
	
	private void makeCellAlive() {
		int i, j = 0;
		Scanner s = new Scanner(System.in);
		
		do {
			System.out.print("\n Inform the row number (0 - " + Main.GRID_HEIGHT + "): " );
			
			i = s.nextInt();
			
			System.out.print("\n Inform the column number (0 - " + Main.GRID_WIDTH + "): " );
			
			j = s.nextInt();
		}while(!validPosition(i,j));
		
		controller.createCell(i, j);
	}
	
	private void nextGeneration() {
		controller.nextGeneration();
	}
	
	private void halt() {
		controller.halt();
	}
	
	private boolean validPosition(int i, int j) {
		System.out.println(i);
		System.out.println(j);
		return i >= 0 && i < Main.GRID_HEIGHT && j >= 0 && j < Main.GRID_WIDTH;
	}

	private int parseOption(String option) {
		if(option.equals("1")) {
			return MAKE_CELL_ALIVE;
		}
		else if (option.equals("2")) {
			return NEXT_GENERATION;
		}
		else if (option.equals("3")) {
			return HALT;
		}
		else return INVALID_OPTION;
	}

	/* Imprime uma linha usada como separador das linhas do tabuleiro */
	private void printLine() {
		for (int j = 0; j < Main.GRID_WIDTH; j++) {
			System.out.print(LINE);
		}
		System.out.print("\n");
	}

	/*
	 * Imprime os identificadores das colunas na primeira linha do tabuleiro
	 */
	private void printFirstRow() {
		System.out.println("\n \n");
		for (int j = 0; j < Main.GRID_WIDTH; j++) {
			System.out.print("   " + j + "   ");
		}
		System.out.print("\n");
	}
}
