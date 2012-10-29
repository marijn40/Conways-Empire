package conwaysempire.CoreGame;

/**
 *
 * @author Bram
 */
public class Printer {
	private Field field;

	public Printer (Match match) {
		this.field = match.getField();
	}
	
	/**
	 * Prints the field out to the standard out stream.
	 */
	public void print () {
		String[] tekens = {"██", "  ", "░░", "▒▒", "▓▓"};
		
		System.out.print("╔");
		for (int i = 0; i < field.getSize(); i++)
			System.out.print("══");
		
		System.out.println("╗");

		for (int i = 0; i < field.getSize(); i++) {
			System.out.print("║");
			for (int j = 0; j < field.getSize(); j++)
				System.out.print(tekens[field.get(i, j) + 1]);
			System.out.println("║");
		}

		System.out.print("╚");
		for (int i = 0; i < field.getSize(); i++)
			System.out.print("══");

		System.out.println("╝");
	}

	public void print (int num) {
		System.out.print("╔");
		for (int i = 0; i < field.getSize(); i++) 
			System.out.print("══");
		
		System.out.println("╗");

		for (int i = 0; i < field.getSize(); i++) {
			System.out.print("║");
			for (int j = 0; j < field.getSize(); j++) {
				if (field.get(i, j) == num) { // eigen
					System.out.print("  "); // 1
				} else if (field.inView(i, j, num)) { // binnen gezitchtsveld
					if (field.get(i, j) == -1) { // leeg, binnen gezichtsveld
						System.out.print("▒▒"); // 3
					} else { // tegenstander
						System.out.print("██"); // 5
					}
				} else { // onbekend
					System.out.print("▓▓"); //4
				}
			}
			System.out.println("║");
		}

		System.out.print("╚");
		for (int i = 0; i < field.getSize(); i++)
			System.out.print("══");
		
		System.out.println("╝");
	}

	
	public int getSize () {
		return field.getSize();
	}
}
