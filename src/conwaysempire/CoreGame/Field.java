package conwaysempire.CoreGame;

import conwaysempire.Buildings.Building;

/**
 *
 * @author Bram
 */
public class Field {

	private int size;
	private int[][] field;
	private Match m;
	private TieTactic tt;
	private int viewDistance;

	/**
	 * Constructor
	 * @param size width and height of the made field
	 * @param m the Match that rules this Field
	 * @param tt the TieTactic of the field
	 */
	public Field (int size, Match m, TieTactic tt, int viewDistance) {
		this.size = size;
		field = new int[size][size];
		this.m = m;
		this.tt = tt;
		this.viewDistance = viewDistance;

		for (int i = 0; i < field.length; i++)
			for (int j = 0; j < field[0].length; j++)
				field[i][j] = -1;
	}

	/**
	 * Iterates a certain amount of steps in the field
	 * @param n the number of steps to skip
	 * @pre n >= 0 (else this function does nothing)
	 */
	public void iterate (int n) {
		for (int i = 0; i < n; i++)
			iterate();
	}

	/**
	 * Iterates the field to the next step
	 */
	public void iterate () {
		int[][] n_field = new int[size][size];
		int[] activeCells = new int[m.getNumPlayers()];

		for (int i = 0; i < size; i++) { // loopt vast wanneer i = 1, j = 1
			for (int j = 0; j < size; j++) {
				int newOwner = getNewOwner(i, j);
				n_field[i][j] = newOwner;
				if (newOwner != -1) {
					activeCells[newOwner]++;
				}
			}
		}

		field = n_field;

		for (int i = 0; i < activeCells.length; i++)
			m.getPlayer(i).setActiveCells(activeCells[i]);
	}

	/**
	 * Calculates the new owner of a cell in the field.
	 * @param y y-coordinate of the cell
	 * @param x x-coordinate of the cell
	 * @return the player-number of the new owner
	 */
	private int getNewOwner (int y, int x) {
		int teller = 0;
		int[] points = new int[m.getNumPlayers()];

		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				try {
					if (field[y + i][x + j] != -1 && (i != 0 || j != 0)) {
						points[field[y + i][x + j]]++;
						teller++;
					}
				} catch (ArrayIndexOutOfBoundsException ex) {
				}
			}
		}

		// als de cel levend wordt/blijft
		if (teller == 3 || (teller == 2 && field[y][x] != -1)) {
			try {
				return max(points);
			} catch (TieException ex) {
				switch (tt) {
					case TOTAL_RANDOM:
						return (int) (Math.random() * m.getNumPlayers());
					case TIED_RANDOM:
						int i = (int) (Math.random() * ex.getNumTied());
						teller = 0;
						for (int j = 0; j < m.getNumPlayers(); j++) {
							if (ex.getTies()[i]) {
								if (teller == i) {
									return j;
								} else {
									teller++;
								}
							}
						}
						return 0;
					case LOSERS_GAIN:
						int mini = -1;
						for (i = 0; i < m.getNumPlayers(); i++)
							if (mini == -1 || ex.getTies()[i] && m.getPlayer(i).getTotalCells() < m.getPlayer(mini).getTotalCells())
								mini = i;

						if (mini == -1)
							return (int) (Math.random() * m.getNumPlayers());
						
						return mini;
					case WINNERS_GAIN:
						int maxi = -1;
						for (i = 0; i < m.getNumPlayers(); i++)
							if (maxi == -1 || ex.getTies()[i] && m.getPlayer(i).getTotalCells() > m.getPlayer(maxi).getTotalCells())
								maxi = i;
						
						if (maxi == -1)
							return (int) (Math.random() * m.getNumPlayers());
						
						return maxi;
				}
			}
		}

		return -1;
	}

	/**
	 * returns the index of the value of the table
	 * @param table the table to get the maximum value of
	 * @return the index of the maximum value
	 * @throws TieException in case of a tie, an initialised TieException is thrown
	 */
	private int max (int[] table) throws TieException {
		int maxi = 0;
		boolean meergevonden = false;
		boolean[] ties = new boolean[m.getNumPlayers()];
		int i;
		ties[0] = true;

		for (i = 1; i < table.length; i++) {
			if (table[i] > table[maxi]) {
				maxi = i;
				ties = new boolean[m.getNumPlayers()];
				ties[i] = true;
				meergevonden = false;
			} else if (table[i] == table[maxi]) {
				ties[i] = true;
				meergevonden = true;
			}
		}

		if (meergevonden) {
			throw new TieException(ties);
		}

		return maxi;
	}

	public boolean inView (int y, int x, int player) {
		for (int i = -viewDistance; i <= viewDistance; i++) {
			for (int j = -viewDistance; j <= viewDistance; j++) {
				try {
					if (field[y + i][x + j] == player) {
						return true;
					}
				} catch (ArrayIndexOutOfBoundsException ex) {
				}
			}
		}

		return false;
	}

	/**
	 * Places a building on the field
	 * @param p the player that builds
	 * @param b the building to build
	 * @param y to-be y-coordinate of the top of the building
	 * @param x to-be x-coordinate of the left of the building
	 * @throws BuildNotPossibleException
	 */
	public void build (Player p, Building b, int y, int x) throws BuildNotPossibleException {
		int playerNumber = m.getPlayerNumber(p);
		if (!buildPossible(playerNumber, b, y, x)) {
			throw new BuildNotPossibleException();
		}

		// else
		for (int i = 0; i < b.getHeight(); i++) {
			for (int j = 0; j < b.getWidth(); j++) {
				if (b.getCell(i, j)) {
					field[y + i][x + j] = playerNumber;
				}
			}
		}

		p.subUnactiveCells(b.getCost());
	}

	/**
	 *
	 * @param playerNumber the number of the player in the field
	 * @param b the building to be built
	 * @param to-be y y-coordinate of the top of the building
	 * @param to-be x x-coordinate of the left of the building
	 * @return
	 */
	public boolean buildPossible (int playerNumber, Building b, int y, int x) {
		if (y + b.getHeight() > size || x + b.getWidth() > size) {
			return false;
		}

		if (m.getPlayer(playerNumber).getUnactiveCells() < b.getCost()) {
			return false;
		}

		for (int i = 0; i < b.getHeight(); i++) {
			for (int j = 0; j < b.getWidth(); j++) {
				if (b.getCell(i, j) && (field[y + i][x + j] != -1) && (field[y + i][x + j] != playerNumber)) {
					return false;
				}
			}
		}

		return true;
	}

	public int getSize () {
		return size;
	}

	public int get (int i, int j) {
		return field[i][j];
	}

	public int getViewDistance () {
		return viewDistance;
	}
}