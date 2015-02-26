
/**
 * Clasa cu ajutorul careia se determina prioritatea unui operator.
 * 
 * @author andrei
 *
 */
public class Priority {

	private Priority() {
	}

	/**
	 * Determinarea prioritatii
	 * 
	 * @param type
	 *            tipul operatorului (+, - , log, ...)
	 * @return prioritatea
	 */
	public static int getPriority(String type) {
		int value;

		switch (type) {

		case "+":
		case "-":
			value = 1;
			break;

		case "*":
		case "/":
			value = 2;
			break;

		case "^":
			value = 3;
			break;

		case "negative":
			value = 4;
			break;

		case "log":
		case "sqrt":
		case "sin":
		case "cos":
			value = 5;
			break;

		default:
			value = 0;
			break;
		}

		return value;
	}

}
