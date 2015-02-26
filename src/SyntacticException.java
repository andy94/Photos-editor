
/**
 * Exceptie ce poate sa apara la construirea arborelui de parsare
 * 
 * @author andrei
 *
 */
public class SyntacticException extends Exception {

	private static final long serialVersionUID = 2916684190445948659L;

	public static String W_PAR = "wrong paranthesis";
	public static String UN_OP = "undefined operator";
	public static String W_OPRND = "wrong operand";

	private String message;

	/**
	 * Constructor
	 * 
	 * @param message
	 *            mesajul ce va fi aruncat
	 */
	public SyntacticException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return "SyntacticException: " + message + ".";
	}
}
