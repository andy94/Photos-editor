
/**
 * Clasa de baza pentru operatorii binari. Operatorii binari sunt noduri in
 * arborele de parsare.
 * 
 * @author andrei
 *
 */
public abstract class BinaryOperator extends ExpressionTreeNode {

	private ExpressionTreeNode left;
	private ExpressionTreeNode right;

	/**
	 * Constructor
	 * 
	 * @param left
	 *            Nodul din stanga
	 * @param right
	 *            Nodul din dreapta
	 */
	public BinaryOperator(ExpressionTreeNode left, ExpressionTreeNode right) {
		this.left = left;
		this.right = right;
	}

	/**
	 * Returneaza nodul din stanga
	 * 
	 * @return left
	 */
	public ExpressionTreeNode getLeft() {
		return left;
	}

	/**
	 * Returneaza nodul din dreapta
	 * 
	 * @return right
	 */
	public ExpressionTreeNode getRight() {
		return right;
	}

}

/**
 * Clasa pentru operatorul de adunare
 * 
 * @author andrei
 *
 */
class AdditionOperator extends BinaryOperator {

	public AdditionOperator(ExpressionTreeNode left, ExpressionTreeNode right) {
		super(left, right);
	}

	@Override
	public double accept(Visitor v) {
		return v.visit(this);
	}
}

/**
 * Clasa pentru operatorul de scadere
 * 
 * @author andrei
 *
 */
class SubtractionOperator extends BinaryOperator {

	public SubtractionOperator(ExpressionTreeNode left, ExpressionTreeNode right) {
		super(left, right);
	}

	@Override
	public double accept(Visitor v) {
		return v.visit(this);
	}
}

/**
 * Clasa pentru operatorul de inmultire
 * 
 * @author andrei
 *
 */
class MultiplicationOperator extends BinaryOperator {

	public MultiplicationOperator(ExpressionTreeNode left,
			ExpressionTreeNode right) {
		super(left, right);
	}

	@Override
	public double accept(Visitor v) {
		return v.visit(this);
	}
}

/**
 * Clasa pentru operatorul de impartire
 * 
 * @author andrei
 *
 */
class DivisionOperator extends BinaryOperator {

	public DivisionOperator(ExpressionTreeNode left, ExpressionTreeNode right) {
		super(left, right);
	}

	@Override
	public double accept(Visitor v) {
		return v.visit(this);
	}
}

/**
 * Clasa pentru operatorul de ridicare la putere
 * 
 * @author andrei
 *
 */
class PowerOperator extends BinaryOperator {

	public PowerOperator(ExpressionTreeNode left, ExpressionTreeNode right) {
		super(left, right);
	}

	@Override
	public double accept(Visitor v) {
		return v.visit(this);
	}
}
