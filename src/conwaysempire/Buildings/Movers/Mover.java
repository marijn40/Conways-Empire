package conwaysempire.Buildings.Movers;

import conwaysempire.Buildings.Building;

/**
 *
 * @author Bram
 */
public abstract class Mover extends Building {
	private Direction d;
	private DirectionType dt;
	
	/**
	 * Constructor
	 * @param cells the boolean-array
	 * @param numCells
	 * @param d 
	 */
	protected Mover (boolean[][] cells, Direction d) {
		super(cells);
		this.d = d;
		if (d == Direction.NORTH || d == Direction.EAST || d == Direction.SOUTH || d == Direction.WEST)
			dt = DirectionType.ORTHOGONAL;
		else
			dt = DirectionType.DIAGONAL;
	}

	/**
	 * Getter for the Direction of the Mover
	 * @return the Direction of the Mover
	 */
	public Direction getDirection () {
		return d;
	}

	/**
	 * Getter for the DirectionType of the Mover
	 * @return the DirectionType of the Mover
	 */
	public DirectionType getDirectionType () {
		return dt;
	}
	
	/**
	 *
	 * @param clockwise
	 */
	@Override
	public void turn(boolean clockwise) {
		super.turn(clockwise);
		d = d.turned(clockwise);
	}
	
	@Override
	public void mirror(boolean horizontal) {
		super.mirror(horizontal);
		d = d.mirrored(horizontal);
	}
}
