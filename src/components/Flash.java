package components;

import types.TaskType;
import messaging.Message;
import messaging.MessageFlash;
import messaging.MessageImage;

/**
 * Componenta care aplica efectul flash asupra unei poze
 * 
 * @author andrei
 *
 */
public class Flash extends Component {

	public final int ValueOfFlash = 50;

	public Flash() {
		super(TaskType.FLASH);
	}

	@Override
	public Message notify(Message message) {

		if (!(message instanceof MessageFlash)) {
			return null;
		}

		MessageFlash flash = (MessageFlash) message;

		int[][][] pixels = flash.getPixels();
		int width = flash.getWidth();
		int height = flash.getHeight();

		switch (flash.getFlashType()) {

		case ON:
			flash(pixels, width, height, ValueOfFlash);
			break;

		case AUTO:
			if (getAvgLuminosity(pixels, width, height) < 60) {
				flash(pixels, width, height, ValueOfFlash);
			}
			break;

		case OFF:
			break;
		}

		return new MessageImage(TaskType.FLASH, pixels, width, height);
	}

	/**
	 * Realizeaza efectul flash
	 * 
	 * @param pixels
	 *            matricea de pixeli
	 * @param width
	 *            latime
	 * @param height
	 *            inaltime
	 * @param value
	 *            valoarea flash-ului
	 */
	private void flash(int[][][] pixels, int width, int height, int value) {
		int i, j, k;
		for (i = 0; i < height; i++) {
			for (j = 0; j < width; j++) {
				for (k = 0; k < 3; k++) {
					pixels[i][j][k] = Math.min(pixels[i][j][k] + value, 255);
				}
			}
		}
	}

	/**
	 * Calculeaza luminozitatea medie a unei poze
	 * 
	 * @param pixels
	 *            matricea de pixeli
	 * @param width
	 *            latime
	 * @param height
	 *            inaltime
	 * @return luminozitatea medie
	 */
	private int getAvgLuminosity(int[][][] pixels, int width, int height) {
		int i, j, sum = 0;
		for (i = 0; i < height; i++) {
			for (j = 0; j < width; j++) {
				sum += Math.round(0.2126f * pixels[i][j][0] + 0.7152f
						* pixels[i][j][1] + 0.0722f * pixels[i][j][2]);
			}
		}
		return sum / (width * height);
	}
}
