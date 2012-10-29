package conwaysempire.Buildings;

/**
 *
 * @author Bram
 */
public abstract class Building {

	/**
	 * The cells that will become alive.
	 */
	private boolean[][] cells;
	/**
	 * number of living cells, 'cost' of this building
	 */
	private int numCells;
	
	/**
	 * Constructor
	 * @param cells the boolean-table of living cells
	 */
	protected Building (boolean[][] cells) {
		this.cells = cells;
		
		numCells = 0;
		for (int i = 0; i < cells.length; i++)
			for (int j = 0; j < cells[0].length; j++)
				if (cells[i][j])
					numCells++;
	}

	/**
	 * Gets the height of the building
	 * @return the height
	 */
	public int getHeight () {
		return cells.length;
	}

	/**
	 * Gets the width of the building
	 * @return 
	 */
	public int getWidth () {
		return cells[0].length;
	}
	
	/**
	 * getter for the field of cells this Building sets
	 * @param i row
	 * @param j column
	 * @return whether the building sets the cell or not
	 */
	public boolean getCell (int i, int j) {
		return cells[i][j];
	}

	/**
	 * Getter for the number of the number of cells in the building
	 * @return the number of to-be live cells in the building
	 */
	public int getCost () {
		return numCells;
	}
	
	
	
	/**
	 * turns the building
	 * @param clockwise I what direction the building has to be turned
	 */
	public void turn(boolean clockwise) {
		boolean[][] n_cells = new boolean [cells[0].length][cells.length];
		if (clockwise) {
			for (int i = 0; i < cells.length; i++)
				for (int j = 0; j < cells[0].length; j++)
					n_cells[j][cells.length - i - 1] = cells[i][j];
			
		} else {
			for (int i = 0; i < cells.length; i++)
				for (int j = 0; j < cells[0].length; j++)
					n_cells[cells[0].length - j - 1][i] = cells[i][j];
		}
		
		cells = n_cells;
	}
	
	/**
	 * Mirrors the building
	 * @param horizontal If true, left and right will be swapped. If false, up and down.
	 */
	public void mirror (boolean horizontal) {
		if (horizontal) {
			boolean help;
			for (int i = 0; i < cells.length; i++) {
				for (int j = 0; j < cells[0].length / 2; j++) {
					help = cells[i][j];
					cells[i][j] = cells[i][cells[0].length - j - 1];
					cells[i][cells[0].length - j - 1] = help;
				}
			}
		} else {
			boolean[] help;
			for (int i = 0; i < cells.length / 2; i++) {
				help = cells[i];
				cells[i] = cells[cells.length - i - 1];
				cells[cells.length - i - 1] = help;
			}
		}
	}
}
