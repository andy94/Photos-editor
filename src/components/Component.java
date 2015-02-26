package components;

import messaging.Message;
import types.TaskType;

/**
 * Clasa de baza pentru componente
 * 
 * @author andrei
 *
 */
public abstract class Component {
	private TaskType taskType;

	public Component(TaskType taskType) {
		super();
		this.taskType = taskType;
	}

	/**
	 * @return Tipul de task pe care il realizeaza componenta
	 */
	public TaskType getTaskType() {
		return taskType;
	}

	/**
	 * Seteaza tipul taskului
	 * 
	 * @param taskType
	 */
	public void setTaskType(TaskType taskType) {
		this.taskType = taskType;
	}

	/**
	 * Metoda care realizeaza actiunea specifica fiecarei componente
	 * 
	 * @param message
	 *            mesajul primit
	 * @return mesajul specific modificat
	 */
	public abstract Message notify(Message message);

}
