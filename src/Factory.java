
/**
 * Clasa de tip singleton cu ajutorul careia se creeaza noduri din arborele de
 * parsare
 * 
 * @author andrei
 *
 */
public class Factory {

	private static Factory instance;

	private Factory() {
	}

	/**
	 * Returnarea istantei factory
	 * 
	 * @return instance
	 */
	public static Factory getInstance() {
		if (instance == null) {
			instance = new Factory();
		}

		return instance;
	}

	/**
	 * Creearea unui nod de tip operator binar
	 * 
	 * @param type
	 *            tipul operatorului (+, - , ...)
	 * @param left
	 *            nodul din stanga
	 * @param right
	 *            nodul din dreapta
	 * @return nodul creat
	 */
	public ExpressionTreeNode getBinaryOperator(String type,
			ExpressionTreeNode left, ExpressionTreeNode right) {

		switch (type) {

		case "+":
			return new AdditionOperator(left, right);

		case "-":
			return new SubtractionOperator(left, right);

		case "*":
			return new MultiplicationOperator(left, right);

		case "/":
			return new DivisionOperator(left, right);

		case "^":
			return new PowerOperator(left, right);

		default:
			return null;

		}

	}

	/**
	 * Creearea unui nod de tip operator unar
	 * 
	 * @param type
	 *            tipul operatorului (log, sin , ...)
	 * @param argument
	 *            nodul pe care se aplica
	 * @return nodul creat
	 */
	public ExpressionTreeNode getUnaryOperator(String type,
			ExpressionTreeNode argument) {

		switch (type) {

		case "log":
			return new LogOperator(argument);

		case "sqrt":
			return new SqrtOperator(argument);

		case "sin":
			return new SinOperator(argument);

		case "cos":
			return new CosOperator(argument);

		case "negative":
			return new NegativeOperator(argument);

		default:
			return null;

		}

	}

}
