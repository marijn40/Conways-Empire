package conwaysempire;

import conwaysempire.Buildings.Movers.Glider;
import conwaysempire.Buildings.Movers.LightWeight;
import conwaysempire.CoreGame.BuildNotPossibleException;
import conwaysempire.CoreGame.Match;
import conwaysempire.CoreGame.Player;
import conwaysempire.CoreGame.Printer;
import conwaysempire.CoreGame.TieTactic;

/**
 *
 * @author Bram
 */
public class ConwaysEmpire {

	/**
	 * @param args the command line arguments
	 */
	public static void main (String[] args) {
		Player a = new Player(10);
		Player b = new Player(9);
		Match m = new Match(new Player[] {a, b}, 20, TieTactic.WINNERS_GAIN);
		Printer p = new Printer(m);

		try {
			m.getField().build(a, new LightWeight(), 1, 7);
			m.getField().build(b, new Glider(), 7, 10);
		} catch (BuildNotPossibleException bmpex) {
		}

		for (int i = 0; i < 225; i++) {
			m.getField().iterate();
			p.print();
		}
		p.print();
	}
}
