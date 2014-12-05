/**
 * Game of Life
 * @author zidenis
 * @version 1.0
 * @since dec/2014
*/
package br.unb.gol;

/**
 * Game of Life Cell
 * @version 2.0
 * @author rbonifacio (v1.0)
 * @author zidenis (v2.0)
 */
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
