package conwaysempire.Buildings.Movers;

/**
 *
 * @author Bram
 */
public class Glider extends Mover {
	public Glider () {
		super(new boolean[][] {{true, true, true},
							   {false, false, true},
							   {false, true, false}},
			Direction.NORTHEAST);
	}
}
