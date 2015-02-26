package messaging;

import java.util.ArrayList;

import types.TaskType;
import components.Component;

/**
 * Clasa care descrie un centru de mesaje
 * 
 * @author andrei
 *
 */
public class Center extends MessageCenter {

	/**
	 * Lista de vecini
	 */
	private ArrayList<MessageCenter> neighbors;
	/**
	 * Lista de componente
	 */
	private ArrayList<Component> components;
	/**
	 * Id-urile care au mai trecut prin acest centru
	 */
	private ArrayList<Integer> IDs;

	public Center(String centerName) {
		super(centerName);
		this.neighbors = new ArrayList<MessageCenter>();
		this.components = new ArrayList<Component>();
		this.IDs = new ArrayList<Integer>();
	}

	/**
	 * Metoda de adaugare a unui vecin in lista
	 * 
	 * @param neighbor
	 */
	public void addNeighbor(MessageCenter neighbor) {
		neighbors.add(neighbor);
	}

	/**
	 * Metoda de adaugare a unei componente in lista
	 * 
	 * @param component
	 */
	public void addComponent(Component component) {
		components.add(component);
	}

	/**
	 * Metoda de verificare a unui ID
	 * 
	 * @param id
	 *            ID-ul primit
	 * @return -1 daca nu se afla in lista, indexul la care se afla in caz
	 *         contrar
	 */
	public boolean isNewID(int id) {
		return IDs.indexOf((Integer) id) == -1 ? true : false;
	}

	/**
	 * Metoda de adaugare a unui ID in lista
	 * 
	 * @param id
	 */
	public void addID(int id) {
		IDs.add(id);
	}

	/**
	 * Metoda de verificare a unei componente
	 * 
	 * @param tasktype
	 *            Tipul taskului pe care il realizeaza
	 * @return -1 daca nu se afla in lista, indexul la care se afla in caz
	 *         contrar
	 */
	public int checkComponent(TaskType tasktype) {
		for (int i = 0; i < components.size(); i++) {
			if (components.get(i).getTaskType() == tasktype) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * @return numele centrului
	 */
	public String getCenterName() {
		return this.centerName;
	}

	@Override
	protected Message publishAlgorithm(Message message) {

		/* Verifica valabilitate ID */
		if (!isNewID(message.getId())) {
			return null;
		}
		
		/* Adauga ID in lista */
		addID(message.getId());

		int index;
		
		/* Verifica existenta Componentei */
		index = checkComponent(message.getTaskType());

		if (index != -1) {

			return components.get(index).notify(message);

		} else {

			/* Trimite mesajul mai departe */
			for (MessageCenter c : neighbors) {
				Message received = c.publish(message);
				if (received != null) {
					return received;
				}
			}

			return null;
		}

	}

}
