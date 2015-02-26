import java.util.Stack;

/**
 * Clasa cu ajutorul careia se creeaza arborele de parsare si se determina
 * radacina sa
 * 
 * @author andrei
 *
 */
public class TreeBuilder {

	private String expression;

	private Stack<ExpressionTreeNode> result;
	private Stack<String> operator;

	private Factory factory;

	/**
	 * Constructor. Se initializeaza stivele, expresia si factory
	 * 
	 * @param expression
	 *            expresia data
	 */
	public TreeBuilder(String expression) {
		this.expression = expression;

		result = new Stack<ExpressionTreeNode>();
		operator = new Stack<String>();
		factory = Factory.getInstance();
	}

	private boolean isNumber(String str) {
		try {
			Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	private boolean isBinaryOperator(String str) {
		if (str.equals("+") || str.equals("-") || str.equals("*")
				|| str.equals("/") || str.equals("^")) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isUnaryOperator(String str) {
		if (str.equals("log") || str.equals("sqrt") || str.equals("sin")
				|| str.equals("cos") || str.equals("negative")) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isParenthesis(String str) {
		if (str.equals("(") || str.equals(")")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Metoda prin care se realizeaza prima operatie din stiva de operatori
	 * 
	 */
	private void operate() {

		/* Se scoate primul operator */
		String top = operator.pop();

		ExpressionTreeNode node;

		if (isUnaryOperator(top)) {

			ExpressionTreeNode argument = result.pop();

			/* Se "aplica" operatorul pe valoarea scoasa */
			node = factory.getUnaryOperator(top, argument);

			/* Se pune in stiva nodul rezultat */
			result.push(node);
		}

		if (isBinaryOperator(top)) {

			ExpressionTreeNode right = result.pop();
			ExpressionTreeNode left = result.pop();

			/* Se "aplica" operatorul pe valorile scoase */
			node = factory.getBinaryOperator(top, left, right);

			/* Se pune in stiva nodul rezultat */
			result.push(node);
		}
	}

	/**
	 * Se creeaza arborele de parsare si se determina radacina
	 * 
	 * @return radacina arborelui
	 * @throws SyntacticException
	 *             exceptie ce tine de forma expresiei
	 */
	public ExpressionTreeNode buildTree() throws SyntacticException {

		/* Eliminarea spatiilor albe */
		String[] values = expression.split("\\s+");

		int n = values.length;
		String val;

		/* Pentru fiecare valoare in parte */
		for (int i = 0; i < n; ++i) {

			val = values[i];

			/* Daca este constanta */
			if (isNumber(val)) {
				result.push(new Constant(Double.parseDouble(val)));

				if (i > 0 && isNumber(values[i - 1])) {
					throw new SyntacticException(SyntacticException.UN_OP);
				}

			}
			/* Daca este operator */
			else {

				if (!(isBinaryOperator(val) || isUnaryOperator(val) || isParenthesis(val))) {
					/* Nu este definit */
					throw new SyntacticException(SyntacticException.UN_OP);
				}

				/* Tratare paranteze */
				if (val.equals("(")) {
					operator.push("(");
					continue;
				}

				if (val.equals(")")) {

					while (!operator.isEmpty() && !operator.peek().equals("(")) {
						if (result.isEmpty()) {
							throw new SyntacticException(
									SyntacticException.W_PAR);
						}

						operate();

					}
					if (operator.isEmpty()) {
						throw new SyntacticException(SyntacticException.W_PAR);
					}
					operator.pop();
					continue;

				}

				/* Operatorul minus unar */
				if (val.equals("-") && ((i == 0) || values[i - 1].equals("("))) {

					val = "negative";
				}

				/* Daca stiva de operatori este vida */
				if (operator.isEmpty()) {
					operator.push(val);
					continue;
				}

				/* Stiva de operatori nu este vida */

				/*
				 * Operatorul curent are prioritate mai mare decat capul stivei
				 * 
				 * Se trateaza si cazul asociativitatii la dreapta a ridicarii
				 * la putere
				 */

				if (Priority.getPriority(val) > Priority.getPriority(operator
						.peek())
						|| (operator.peek().equals(val) && val.equals("^"))) {

					operator.push(val);
					continue;
				}

				/* Operatorul curent are prioritate mai mica decat capul stivei */

				while (!operator.isEmpty()
						&& Priority.getPriority(val) <= Priority
								.getPriority(operator.peek())) {

					operate();
				}

				operator.push(val);
			}
		}

		/* Realizarea operatiilor ramase */
		while (!operator.isEmpty()) {

			if (isParenthesis(operator.peek())) {
				throw new SyntacticException(SyntacticException.W_PAR);
			}

			operate();
		}

		if (result.isEmpty()) {
			throw new SyntacticException(SyntacticException.W_OPRND);
		}

		/* Determinare radacina */
		ExpressionTreeNode root = result.pop();

		if (!result.isEmpty() || !operator.isEmpty()) {
			throw new SyntacticException(SyntacticException.W_PAR);
		}

		return root;
	}

}
