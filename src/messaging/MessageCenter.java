package messaging;

/**
 * Clasa generala a centrelor de mesaje
 * 
 * @author andrei
 *
 */
public abstract class MessageCenter {
	protected String centerName;

	public MessageCenter(String centerName) {
		this.centerName = centerName;
	}

	/**
	 * Realizeaza actiunea continuta de mesaj
	 * 
	 * @param message
	 *            mesajul care contine task-ul dorit
	 * @return mesajul specific modificat
	 */
	public Message publish(Message message) {
		System.out.println(centerName);
		return publishAlgorithm(message);
	}

	/**
	 * Algoritmul de rezolvare a unui task, implementat in centrul de mesaje
	 * 
	 * @param message
	 *            mesajul dorit
	 * @return mesajul specific modificat
	 */
	protected abstract Message publishAlgorithm(Message message);
}
