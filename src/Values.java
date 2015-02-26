/**
 * Clasa care parseaza si retine informatiile despre poze
 * 
 * @author andrei
 *
 */
public class Values {

	/**
	 * Numele fisierului de intrare
	 */
	private String in;
	/**
	 * Numele fisierului de iesire
	 */
	private String out;

	/**
	 * Numele tipului de flash
	 */
	private String flashType;

	/**
	 * Comanda contine sau nu zoom
	 */
	private boolean zoom;
	/**
	 * Punctele intre care se face zoom
	 */
	private int upperLeftX;
	private int upperLeftY;
	private int lowerDownX;
	private int lowerDownY;

	/**
	 * Orientarea pozei
	 */
	private String photoType;

	/**
	 * Operatiile de dupa capturarea imaginii
	 */
	private String[] postOperations;
	/**
	 * Numarul de operatii
	 */
	private int numberOfPostOperations;

	/**
	 * Metoda care seteaza informatiile despre operatiile care se realizeaza
	 * asupra unei poze, pe baza unui string primit ca parametru
	 * 
	 * @param line
	 *            informatiile despre poza in formatul specificat
	 */
	public void set(String line) {
		String[] info = line.split(" ");

		/* In/Out */
		this.in = info[0];
		this.out = info[1];

		/* Flash */
		info[2] = info[2].substring(4, info[2].length() - 1);
		String[] pre = info[2].split("=");
		this.flashType = pre[1];

		/* Zoom */
		if (pre.length == 3) {
			zoom = true;
			this.flashType = pre[1].split(";")[0];
			String[] values = pre[2].split(",");
			this.upperLeftX = Integer.parseInt(values[0]);
			this.upperLeftY = Integer.parseInt(values[1]);
			this.lowerDownX = Integer.parseInt(values[2]);
			this.lowerDownY = Integer.parseInt(values[3]);
		} else {
			zoom = false;
		}

		/* PhotoType */
		this.photoType = info[3].split("=")[1];
		photoType = photoType.substring(0, photoType.length() - 1);

		/* Post Operations */
		info[4] = info[4].substring(5, info[4].length() - 1);
		this.numberOfPostOperations = 0;
		if (info[4].length() > 1) {
			String[] mode = info[4].split(";");
			this.numberOfPostOperations = mode.length;
			this.postOperations = new String[this.numberOfPostOperations];
			for (int i = 0; i < this.numberOfPostOperations; i++) {
				this.postOperations[i] = mode[i];
			}
		}

	}

	/**
	 * @return fisierul de intrare
	 */
	public String getIn() {
		return in;
	}

	/**
	 * @return fisierul de iesire
	 */
	public String getOut() {
		return out;
	}

	/**
	 * @return tipul de flash
	 */
	public String getFlashType() {
		return flashType;
	}

	/**
	 * @return se face sau nu zoom
	 */
	public boolean isZoom() {
		return zoom;
	}

	/**
	 * @return punctele intre care se face zoom
	 */
	public int getUpperLeftX() {
		return upperLeftX;
	}

	public int getUpperLeftY() {
		return upperLeftY;
	}

	public int getLowerDownX() {
		return lowerDownX;
	}

	public int getLowerDownY() {
		return lowerDownY;
	}

	/**
	 * @return orientarea pozei
	 */
	public String getPhotoType() {
		return photoType;
	}

	/**
	 * @param i
	 *            numarul operatiei de dupa capturarea pozei
	 * @return numele operatiei
	 */
	public String getOperation(int i) {
		return this.postOperations[i];
	}

	/**
	 * @return numarul de operatii de dupa capturarea pozei
	 */
	public int getNumberOfPostOperations() {
		return numberOfPostOperations;
	}

}
