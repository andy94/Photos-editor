package messaging;

/**
 * Clasa specifica pentru ID-uri unice
 * 
 * @author andrei
 *
 */
public class IDGenerator {
	private static int ID = -1;

	/**
	 * Genereaza un ID unic
	 * 
	 * @return ID
	 */
	public static int getID() {
		ID++;
		return ID;
	}
}
