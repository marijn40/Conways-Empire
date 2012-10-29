package conwaysempire.CoreGame;

/**
 *
 * @author Bram
 */
public class Match {

	private int numPlayers;
	private Player[] players;
	private Field field;
	
	private static Match main;
	
	public Match (Player[] p, int fieldSize, TieTactic tt) {
		numPlayers = p.length;
		players = p;
		field = new Field(fieldSize, this, tt, 3);
	}

	public int getNumPlayers () {
		return numPlayers;
	}

	public Player getPlayer (int i) {
		return players[i];
	}

	public static Match getInstance () {
		return main;
	}
	
	public int getPlayerNumber (Player p) {
		for (int i = 0; i < players.length; i++)
			if (players[i].equals(p))
				return i;
		
		return -1;
	}

	public Field getField () {
		return field;
	}
}