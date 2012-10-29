/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package conwaysempire.CoreGame;

/**
 *
 * @author Bram
 */
class TieException extends Exception {
	private boolean[] ties;
	private int numTied;

	public TieException (boolean[] ties) {
		this.ties = ties;
		numTied = 0;
		for (int i = 0; i < ties.length; i++)
			if (ties[i])
				numTied++;
	}

	public boolean[] getTies () {
		return ties;
	}

	public int getNumTied () {
		return numTied;
	}
}
