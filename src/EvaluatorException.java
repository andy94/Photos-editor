
/**
 * Exceptie ce poate sa apara in urma evaluarii unei operatii
 * 
 * @author andrei
 *
 */
public class EvaluatorException extends RuntimeException {

	private static final long serialVersionUID = 5729607299984502799L;

	public static String NEG_SQRT = "negative value passed to square root";
	public static String NEG_LOG = "negative value passed to logarithm";
	public static String ZERO_LOG = "expression under logarithm evaluates to zero";
	public static String ZERO_DIV = "division by zero";

	private String message;

	/**
	 * Constructor
	 * 
	 * @param message
	 *            mesajul ce va fi aruncat
	 */
	public EvaluatorException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return "EvaluatorException: " + message + ".";
	}
}
