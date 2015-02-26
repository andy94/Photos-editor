package messaging;

import types.TaskType;

/**
 * Mesajul de succes la scrierea unei poze pe disk
 *
 */
public class MessageSuccess extends Message {

	public MessageSuccess(TaskType taskType) {
		super(taskType);
	}

}
