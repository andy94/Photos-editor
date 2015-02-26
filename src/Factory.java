import types.FlashType;
import components.*;

/**
 * Creeare de instante pe baza de stringuri
 * 
 * @author andrei
 *
 */
public class Factory {
	private static Factory instance = new Factory();

	private Factory() {
	}

	/**
	 * @return instanta a clasei Factory
	 */
	public static Factory createFactory() {
		return instance;
	}

	/**
	 * Functie care creeaza o componenta
	 * 
	 * @param name
	 *            numele componentei
	 * 
	 * @return componenta specificata prin nume
	 */
	public Component getComponent(String name) {
		switch (name) {
		case "Zoom":
			return new Zoom();
		case "Flash":
			return new Flash();
		case "RawPhoto":
			return new RawPhoto();
		case "NormalPhoto":
			return new NormalPhoto();
		case "Blur":
			return new Blur();
		case "BlackWhite":
			return new BlackWhite();
		case "Sepia":
			return new Sepia();
		case "ImageLoader":
			return new ImageLoader();
		case "ImageSaver":
			return new ImageSaver();
		default:
			return null;
		}
	}

	/**
	 * Functie care returneaza o instanta de FlashType
	 * 
	 * @param string
	 *            numele tipului de flash
	 * 
	 * @return tipul de flash specificat prin nume
	 */
	public FlashType getFlashType(String string) {
		switch (string) {
		case "on":
			return FlashType.ON;
		case "auto":
			return FlashType.AUTO;
		default:
			return FlashType.OFF;
		}
	}

}
