package components;

import types.TaskType;
import messaging.Message;
import messaging.MessageImage;
import messaging.MessageZoom;

/**
 * Componenta care aplica efectul zoom asupra unei poze
 * 
 * @author andrei
 *
 */
public class Zoom extends Component {

	public Zoom() {
		super(TaskType.ZOOM);
	}

	@Override
	public Message notify(Message message) {

		if (!(message instanceof MessageZoom)) {
			return null;
		}

		MessageZoom zoom = (MessageZoom) message;

		int width = zoom.getLowerRightX() - zoom.getUpperLeftX() + 1;
		int height = zoom.getLowerRightY() - zoom.getUpperLeftY() + 1;
		int[][][] pixels = new int[height][width][3];

		int i, j, k, m = 0, n = 0;
		for (i = zoom.getUpperLeftY(); i <= zoom.getLowerRightY(); i++) {
			n = 0;
			for (j = zoom.getUpperLeftX(); j <= zoom.getLowerRightX(); j++) {
				for (k = 0; k < 3; k++) {
					pixels[m][n][k] = zoom.getValue(i, j, k);
				}
				n++;
			}
			m++;
		}

		return new MessageImage(TaskType.ZOOM, pixels, width, height);
	}
}