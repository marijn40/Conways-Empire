package conwaysempire.Buildings.Movers;

/**
 *
 * @author Bram
 */
public class LightWeight extends Mover {
	public LightWeight () {
		super(new boolean[][]{{false, true, true, true, true},
							  {true, false, false, false, true},
							  {false, false, false, false, true},
							  {true, false, false, true, false}}, Direction.EAST);
	}
}
