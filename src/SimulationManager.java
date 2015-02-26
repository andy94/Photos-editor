import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import messaging.*;
import types.*;

public class SimulationManager {
	private MessageCenter messageCenter;

	public SimulationManager(String networkConfigFile) {
		this.messageCenter = buildNetwork(networkConfigFile);
	}

	/**
	 * Creeaza reteaua de centre de mesaje
	 * 
	 * @param networkConfigFile
	 *            numele fisierului de configurare
	 * @return primul centru de mesaje din fisierul de configurare
	 */
	private MessageCenter buildNetwork(String networkConfigFile) {

		File file = new File(networkConfigFile);
		Scanner line;
		try {
			line = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}

		String[] info;

		int numberOfCenters = Integer.parseInt(line.nextLine());

		MessageCenter[] centers = new Center[numberOfCenters];
		Factory factory = Factory.createFactory();

		int i, j;

		/* Adauga la fiecare centru componentele specifice */
		for (i = 0; i < numberOfCenters; i++) {
			info = line.nextLine().split(" ");

			centers[i] = new Center(info[0]);

			for (j = 1; j < info.length; j++) {
				((Center) centers[i]).addComponent(factory
						.getComponent(info[j]));
			}
		}

		/* Adauga la fiecare centru vecinii sai */
		for (i = 0; i < numberOfCenters; i++) {
			info = line.nextLine().split(" ");
			for (j = 1; j < info.length; j++) {
				((Center) centers[i]).addNeighbor(findCenter(centers, info[j]));
			}
		}

		line.close();
		return centers[0];
	}

	/**
	 * Cauta un centru de mesaje dupa numele sau
	 * 
	 * @param centers
	 *            vectorul de centre
	 * @param name
	 *            numele centrului cauta
	 * @return centrul gasit
	 */
	private MessageCenter findCenter(MessageCenter[] centers, String name) {
		for (int i = 0; i < centers.length; i++) {
			if (((Center) centers[i]).getCenterName().equals(name)) {
				return centers[i];
			}
		}
		return null;
	}

	/**
	 * Citeste de la stdin informatiile despre poze si rezolva toate task-urile
	 */
	public void start() {

		Scanner read = new Scanner(System.in);
		String line = read.nextLine();
		Values info = new Values();
		Factory factory = Factory.createFactory();

		while (!line.equals("exit")) {

			info.set(line);

			/* Load */
			MessageLoad load = new MessageLoad(TaskType.IMAGE_LOAD,
					info.getIn());

			MessageImage image = (MessageImage) this.messageCenter
					.publish(load);
			image.generateId();

			/* Flash */
			MessageFlash flash = new MessageFlash(TaskType.FLASH,
					image.getPixels(), image.getWidth(), image.getHeight(),
					factory.getFlashType(info.getFlashType()));

			image = (MessageImage) this.messageCenter.publish(flash);
			image.generateId();

			/* Zoom */
			if (info.isZoom()) {
				MessageZoom zoom = new MessageZoom(TaskType.ZOOM,
						image.getPixels(), image.getWidth(), image.getHeight(),
						info.getUpperLeftX(), info.getUpperLeftY(),
						info.getLowerDownX(), info.getLowerDownY());

				image = (MessageImage) this.messageCenter.publish(zoom);
				image.generateId();
			}

			/* Raw */
			MessageImage raw = new MessageImage(TaskType.RAW_PHOTO,
					image.getPixels(), image.getWidth(), image.getHeight());

			image = (MessageImage) this.messageCenter.publish(raw);
			image.generateId();

			/* Normal */
			if (info.getPhotoType().equals("normal")) {
				MessageImage normal = new MessageImage(TaskType.NORMAL_PHOTO,
						image.getPixels(), image.getWidth(), image.getHeight());

				image = (MessageImage) this.messageCenter.publish(normal);
				image.generateId();
			}

			/* Post Operations */
			for (int i = 0; i < info.getNumberOfPostOperations(); i++) {

				switch (info.getOperation(i)) {

				case "sepia":
					MessageImage sepia = new MessageImage(TaskType.SEPIA,
							image.getPixels(), image.getWidth(),
							image.getHeight());

					image = (MessageImage) this.messageCenter.publish(sepia);
					image.generateId();
					break;

				case "blur":
					MessageImage blur = new MessageImage(TaskType.BLUR,
							image.getPixels(), image.getWidth(),
							image.getHeight());

					image = (MessageImage) this.messageCenter.publish(blur);
					image.generateId();
					break;

				case "black_white":
					MessageImage black_white = new MessageImage(
							TaskType.BLACK_WHITE, image.getPixels(),
							image.getWidth(), image.getHeight());

					image = (MessageImage) this.messageCenter
							.publish(black_white);
					image.generateId();
					break;

				default:
					break;
				}
			}

			/* Save */
			MessageSave save = new MessageSave(TaskType.IMAGE_SAVE,
					image.getPixels(), image.getWidth(), image.getHeight(),
					info.getOut());

			this.messageCenter.publish(save);

			line = read.nextLine();
		}

		read.close();
	}

	/**
	 * Metoda Main
	 * 
	 * @param args
	 *            argumente in linia de comanda
	 */
	public static void main(String[] args) {

		if (args.length != 1) {
			System.out
					.println("Usage: java SimulationManager <network_config_file>");
			return;
		}

		SimulationManager simulationManager = new SimulationManager(args[0]);
		simulationManager.start();

	}

}
