package components;

import types.TaskType;
import messaging.Message;

/**
 * Componenta care aplica efectul raw asupra unei poze. Pot fi adaugate alte
 * instructiuni specifice unei poze su orientare normala
 * 
 * @author andrei
 *
 */
public class NormalPhoto extends Component {

	public NormalPhoto() {
		super(TaskType.NORMAL_PHOTO);
	}

	@Override
	public Message notify(Message message) {
		
		return RawPhoto.raw(message);
	}

}
