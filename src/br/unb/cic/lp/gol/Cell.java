package br.unb.cic.lp.gol;

public class Cell {
	private boolean alive = false;

	public boolean isAlive() {
		return alive;
	}

	public void kill() {
		this.alive = false;
	}
	
	public void rise() {
		this.alive = true;
	}
        
        public Cell duplicate() {
            Cell newCell = new Cell();
            newCell.alive = this.alive;
            return newCell;
        }
}
