package messaging;

import types.TaskType;

/**
 * Clasa de baza pentru mesaje
 * 
 * @author andrei
 *
 */
public abstract class Message {
	private TaskType taskType;
	private int messageId;

	public Message(TaskType taskType) {
		super();
		this.taskType = taskType;
		generateId();
	}

	/**
	 * Genereaza ID
	 */
	public void generateId() {
		this.messageId = IDGenerator.getID();
	}

	/**
	 * Seteaza tipul task-ului
	 * @param taskType
	 */
	public void setTaskType(TaskType taskType) {
		this.taskType = taskType;
	}

	/**
	 * @return tipul task-ului
	 */
	public TaskType getTaskType() {
		return taskType;
	}

	/**
	 * @return id-ul mesajului
	 */
	public int getId() {
		return messageId;
	}

}
