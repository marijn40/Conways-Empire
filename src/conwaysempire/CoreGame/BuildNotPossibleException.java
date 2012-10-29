package conwaysempire.CoreGame;

/**
 *
 * @author Bram
 */
public class BuildNotPossibleException extends Exception {

	public BuildNotPossibleException () {}
	
	@Override
	public String toString() {
		return "Build not possible.";
	}
}
		
