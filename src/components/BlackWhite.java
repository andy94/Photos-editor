package components;

import types.TaskType;
import messaging.Message;
import messaging.MessageImage;

/**
 * Componenta care aplica efectul black_white asupra unei poze
 * 
 * @author andrei
 *
 */
public class BlackWhite extends Component {

	public BlackWhite() {
		super(TaskType.BLACK_WHITE);
	}

	@Override
	public Message notify(Message message) {

		if (!(message instanceof MessageImage)) {
			return null;
		}

		MessageImage blackWhite = (MessageImage) message;

		int[][][] pixels = blackWhite.getPixels();

		blackWhite(pixels, blackWhite.getWidth(), blackWhite.getHeight());

		return message;
	}

	/**
	 * Metoda care realizeaza efectul de black_white
	 * 
	 * @param pixels
	 *            matricea de pixeli
	 * @param width
	 *            latime
	 * @param height
	 *            inaltime
	 */
	private void blackWhite(int[][][] pixels, int width, int height) {
		int i, j;
		int outputR, outputG, outputB;

		for (i = 0; i < height; i++) {
			for (j = 0; j < width; j++) {

				outputR = Math.min(
						(int) Math.round(pixels[i][j][0] * 0.3
								+ pixels[i][j][1] * 0.59 + pixels[i][j][2]
								* 0.11), 255);

				outputG = Math.min(
						(int) Math.round(pixels[i][j][0] * 0.3
								+ pixels[i][j][1] * 0.59 + pixels[i][j][2]
								* 0.11), 255);

				outputB = Math.min(
						(int) Math.round(pixels[i][j][0] * 0.3
								+ pixels[i][j][1] * 0.59 + pixels[i][j][2]
								* 0.11), 255);

				pixels[i][j][0] = outputR;
				pixels[i][j][1] = outputG;
				pixels[i][j][2] = outputB;

			}
		}
	}

}
