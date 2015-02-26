package components;

import types.TaskType;
import messaging.Message;
import messaging.MessageImage;

/**
 * Componenta care aplica efectul sepia asupra unei poze
 * 
 * @author andrei
 *
 */
public class Sepia extends Component {

	private double[] rValue = { 0.393, 0.769, 0.189 };
	private double[] gValue = { 0.349, 0.686, 0.168 };
	private double[] bValue = { 0.272, 0.534, 0.131 };

	public Sepia() {
		super(TaskType.SEPIA);
	}

	@Override
	public Message notify(Message message) {

		if (!(message instanceof MessageImage)) {
			return null;
		}

		MessageImage sepia = (MessageImage) message;

		sepia(sepia.getPixels(), sepia.getWidth(), sepia.getHeight());

		return message;
	}

	/**
	 * Metoda care realizeaza efectul sepia pe imagine
	 * 
	 * @param pixels
	 *            matricea de pixeli
	 * @param width
	 *            latime
	 * @param height
	 *            inaltime
	 */
	private void sepia(int[][][] pixels, int width, int height) {
		int i, j;
		int outputR, outputG, outputB;

		for (i = 0; i < height; i++) {
			for (j = 0; j < width; j++) {

				outputR = Math.min(
						(int) Math.round(pixels[i][j][0] * rValue[0]
								+ pixels[i][j][1] * rValue[1] + pixels[i][j][2]
								* rValue[2]), 255);

				outputG = Math.min(
						(int) Math.round(pixels[i][j][0] * gValue[0]
								+ pixels[i][j][1] * gValue[1] + pixels[i][j][2]
								* gValue[2]), 255);

				outputB = Math.min(
						(int) Math.round(pixels[i][j][0] * bValue[0]
								+ pixels[i][j][1] * bValue[1] + pixels[i][j][2]
								* bValue[2]), 255);

				pixels[i][j][0] = outputR;
				pixels[i][j][1] = outputG;
				pixels[i][j][2] = outputB;

			}
		}
	}
}
