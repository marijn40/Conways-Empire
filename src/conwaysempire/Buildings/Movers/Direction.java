package conwaysempire.Buildings.Movers;

/**
 *
 * @author Bram
 */
public enum Direction {

	NORTH,
	NORTHEAST,
	EAST,
	SOUTHEAST,
	SOUTH,
	SOUTHWEST,
	WEST,
	NORTHWEST;

	public Direction turned (boolean clockwise) {
		if (clockwise) {
			switch (this) {
				case NORTH:
					return EAST;
				case NORTHEAST:
					return SOUTHEAST;
				case EAST:
					return SOUTH;
				case SOUTHEAST:
					return SOUTHWEST;
				case SOUTH:
					return WEST;
				case SOUTHWEST:
					return NORTHWEST;
				case WEST:
					return NORTH;
				case NORTHWEST:
					return NORTHEAST;
			}
		} // else
		switch (this) {
			case NORTH:
				return WEST;
			case NORTHEAST:
				return NORTHWEST;
			case EAST:
				return NORTH;
			case SOUTHEAST:
				return NORTHEAST;
			case SOUTH:
				return EAST;
			case SOUTHWEST:
				return SOUTHEAST;
			case WEST:
				return SOUTH;
			case NORTHWEST:
				return SOUTHWEST;
		}
		return null;
	}
	
	public Direction mirrored (boolean horizontal) {
		if (horizontal) {
			switch (this) {
				case NORTH:
					return NORTH;
				case NORTHEAST:
					return NORTHWEST;
				case EAST:
					return WEST;
				case SOUTHEAST:
					return SOUTHWEST;
				case SOUTH:
					return SOUTH;
				case SOUTHWEST:
					return SOUTHEAST;
				case WEST:
					return EAST;
				case NORTHWEST:
					return NORTHEAST;
			}
		} // else
		switch (this) {
			case NORTH:
				return SOUTH;
			case NORTHEAST:
				return SOUTHEAST;
			case EAST:
				return EAST;
			case SOUTHEAST:
				return NORTHEAST;
			case SOUTH:
				return NORTH;
			case SOUTHWEST:
				return NORTHWEST;
			case WEST:
				return WEST;
			case NORTHWEST:
				return SOUTHWEST;
		}
		return null;
	}
}
