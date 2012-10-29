package conwaysempire.CoreGame;

/**
 * A player in a match
 * @author Bram
 */
public class Player {
	/**
	 * Living cells of the player on the field.
	 */
	private int activeCells;
	/**
	 * "Money" of the player.
	 * <br />
	 * The player can use these cells to build buildings
	 */
	private int unactiveCells;

	/**
	 * Constructor
	 * @param unactiveCells The starting amount of unactive cells this player has.
	 */
	public Player (int unactiveCells) {
		this.unactiveCells = unactiveCells;
	}
	
	/**
	 * Getter for the amount of living cells of the player.
	 * @return 
	 */
	public int getActiveCells () {
		return activeCells;
	}

	public int getUnactiveCells () {
		return unactiveCells;
	}
	
	public int getTotalCells () {
		return activeCells + unactiveCells;
	}

	/**
	 * setter for activeCells
	 * @param activeCells the new amount of active cells of the player
	 */
	void setActiveCells (int activeCells) {
		this.activeCells = activeCells;
	}
	
	/**
	 * adds an amount of to-be-spent cells to the player
	 * @param amount the amount of cells to be added
	 * @pre amount >=0
	 */
	void addUnactiveCells(int amount) {
		if (amount >=0)
			this.unactiveCells += amount;
	}
	
	/**
	 * removes an amount of to-be-spent cells from the player
	 * @param amount the amount of cells to be removed
	 * @pre amount >=0
	 */
	void subUnactiveCells(int amount) {
		if (amount >=0)
			this.unactiveCells -= amount;
	}
}
