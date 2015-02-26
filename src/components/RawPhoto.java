package components;

import types.TaskType;
import messaging.Message;
import messaging.MessageImage;

/**
 * Componenta care aplica efectul raw asupra unei poze
 * 
 * @author andrei
 *
 */
public class RawPhoto extends Component {

	public RawPhoto() {
		super(TaskType.RAW_PHOTO);
	}

	@Override
	public Message notify(Message message) {

		return raw(message);

	}

	/**
	 * Functie statica care aplica efectul raw asupra unei poze. Nu foloseste
	 * matrice auxiliare.
	 * 
	 * @param message
	 *            mesajul primit (imaginea)
	 * @return mesajul modificat (imaginea inversa)
	 */
	public static Message raw(Message message) {

		if (!(message instanceof MessageImage)) {
			return null;
		}

		MessageImage rawPhoto = (MessageImage) message;

		int[][][] pixels = rawPhoto.getPixels();
		int width = rawPhoto.getWidth();
		int height = rawPhoto.getHeight();

		int i, j;
		for (i = 0; i < height / 2; i++) {
			for (j = 0; j < width; j++) {
				switchPixels(pixels, height, i, j);
			}
		}

		return message;
	}

	/**
	 * Functie de interschimbare a doua valori din matricea de pixeli. Foloseste
	 * shift-area pe biti
	 * 
	 * @param pixels
	 *            matricea de pixeli
	 * @param height
	 *            inaltime
	 * @param i
	 *            coordonata x
	 * @param j
	 *            coordonata y
	 */
	private static void switchPixels(int[][][] pixels, int height, int i, int j) {

		int k = height - i - 1;

		pixels[i][j][0] = pixels[i][j][0] ^ pixels[k][j][0];
		pixels[k][j][0] = pixels[i][j][0] ^ pixels[k][j][0];
		pixels[i][j][0] = pixels[i][j][0] ^ pixels[k][j][0];

		pixels[i][j][1] = pixels[i][j][1] ^ pixels[k][j][1];
		pixels[k][j][1] = pixels[i][j][1] ^ pixels[k][j][1];
		pixels[i][j][1] = pixels[i][j][1] ^ pixels[k][j][1];

		pixels[i][j][2] = pixels[i][j][2] ^ pixels[k][j][2];
		pixels[k][j][2] = pixels[i][j][2] ^ pixels[k][j][2];
		pixels[i][j][2] = pixels[i][j][2] ^ pixels[k][j][2];
	}
}
