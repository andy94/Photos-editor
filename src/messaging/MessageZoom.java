package messaging;

import types.TaskType;

/**
 * Mesajul pentru efectul zoom. Contine informatii de baza ale imaginii.
 * 
 * @author andrei
 *
 */
public class MessageZoom extends Message {

	private int[][][] pixels;
	private int width, height;
	private int lowerRightX, lowerRightY, upperLeftX, upperLeftY;

	public MessageZoom(TaskType taskType, int[][][] pixels, int width,
			int height, int upperLeftX, int upperLeftY, int lowerRightX,
			int lowerRightY) {
		super(taskType);

		this.pixels = pixels;
		this.width = width;
		this.height = height;
		this.lowerRightX = lowerRightX;
		this.lowerRightY = lowerRightY;
		this.upperLeftX = upperLeftX;
		this.upperLeftY = upperLeftY;
	}

	public MessageZoom(TaskType taskType) {
		super(taskType);
	}

	public int[][][] getPixels() {
		return pixels;
	}

	public void setPixels(int[][][] pixels) {
		this.pixels = pixels;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getValue(int i, int j, int k) {
		return pixels[i][j][k];
	}

	public int getLowerRightX() {
		return lowerRightX;
	}

	public void setLowerRightX(int lowerRightX) {
		this.lowerRightX = lowerRightX;
	}

	public int getLowerRightY() {
		return lowerRightY;
	}

	public void setLowerRightY(int lowerRightY) {
		this.lowerRightY = lowerRightY;
	}

	public int getUpperLeftX() {
		return upperLeftX;
	}

	public void setUpperLeftX(int upperLeftX) {
		this.upperLeftX = upperLeftX;
	}

	public int getUpperLeftY() {
		return upperLeftY;
	}

	public void setUpperLeftY(int upperLeftY) {
		this.upperLeftY = upperLeftY;
	}

}
