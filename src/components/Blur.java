package components;

import types.TaskType;
import messaging.Message;
import messaging.MessageImage;

/**
 * Componenta care aplica efectul blur asupra unei poze
 * 
 * @author andrei
 *
 */
public class Blur extends Component {

	public Blur() {
		super(TaskType.BLUR);
	}

	@Override
	public Message notify(Message message) {

		if (!(message instanceof MessageImage)) {
			return null;
		}

		MessageImage blur = (MessageImage) message;

		int[][][] pixels = blur.getPixels();

		int width = blur.getWidth();
		int height = blur.getHeight();

		int[][][] clone = new int[height][width][3];

		int i, j, k, o;
		double neighbors, rSum, gSum, bSum;

		for (o = 0; o < 10; o++) {

			for (i = 0; i < height; i++) {
				for (j = 0; j < width; j++) {

					neighbors = rSum = gSum = bSum = 0;

					/* Sus */
					if (i - 1 >= 0) {

						/* Sus -stanga */
						if (j - 1 >= 0) {
							neighbors++;
							rSum += pixels[i - 1][j - 1][0];
							gSum += pixels[i - 1][j - 1][1];
							bSum += pixels[i - 1][j - 1][2];
						}

						/* Sus - mijloc */
						neighbors++;
						rSum += pixels[i - 1][j][0];
						gSum += pixels[i - 1][j][1];
						bSum += pixels[i - 1][j][2];

						/* Sus - dreapta */
						if (j + 1 < width) {
							neighbors++;
							rSum += pixels[i - 1][j + 1][0];
							gSum += pixels[i - 1][j + 1][1];
							bSum += pixels[i - 1][j + 1][2];
						}
					}

					/* Mijloc - stanga */
					if (j - 1 >= 0) {
						neighbors++;
						rSum += pixels[i][j - 1][0];
						gSum += pixels[i][j - 1][1];
						bSum += pixels[i][j - 1][2];
					}

					/* Mijloc -dreapta */
					if (j + 1 < width) {
						neighbors++;
						rSum += pixels[i][j + 1][0];
						gSum += pixels[i][j + 1][1];
						bSum += pixels[i][j + 1][2];
					}

					/* Jos */
					if (i + 1 < height) {

						/* Jos - stanga */
						if (j - 1 >= 0) {
							neighbors++;
							rSum += pixels[i + 1][j - 1][0];
							gSum += pixels[i + 1][j - 1][1];
							bSum += pixels[i + 1][j - 1][2];
						}

						/* Jos - mijloc */
						neighbors++;
						rSum += pixels[i + 1][j][0];
						gSum += pixels[i + 1][j][1];
						bSum += pixels[i + 1][j][2];

						/* Jos - dreapta */
						if (j + 1 < width) {
							neighbors++;
							rSum += pixels[i + 1][j + 1][0];
							gSum += pixels[i + 1][j + 1][1];
							bSum += pixels[i + 1][j + 1][2];
						}
					}
					if (neighbors == 0) {
						clone[i][j][0] = clone[i][j][1] = clone[i][j][2] = 0;
					} else {
						clone[i][j][0] = (int) Math.round(rSum / neighbors);
						clone[i][j][1] = (int) Math.round(gSum / neighbors);
						clone[i][j][2] = (int) Math.round(bSum / neighbors);
					}

				}
			}

			for (i = 0; i < height; i++) {
				for (j = 0; j < width; j++) {
					for (k = 0; k < 3; k++) {
						pixels[i][j][k] = clone[i][j][k];
					}
				}
			}

		}

		return message;
	}
}
