
/**
 * Clasa de baza pentru operatorii unari. Operatorii unari sunt noduri in
 * arborele de parsare.
 * 
 * @author andrei
 *
 */
public abstract class UnaryOperator extends ExpressionTreeNode {

	private ExpressionTreeNode argument;

	/**
	 * Constructor
	 * 
	 * @param argument
	 *            Argumentul operatorului
	 */
	public UnaryOperator(ExpressionTreeNode argument) {
		this.argument = argument;
	}

	/**
	 * Returneaza argumentul
	 * 
	 * @return argument
	 */
	public ExpressionTreeNode getArgument() {
		return argument;
	}

}

/**
 * Clasa pentru operatorul unar logaritm
 * 
 * @author andrei
 *
 */
class LogOperator extends UnaryOperator {

	public LogOperator(ExpressionTreeNode argument) {
		super(argument);
	}

	@Override
	public double accept(Visitor v) {
		return v.visit(this);
	}
}

/**
 * Clasa pentru operatorul unar radical
 * 
 * @author andrei
 *
 */
class SqrtOperator extends UnaryOperator {

	public SqrtOperator(ExpressionTreeNode argument) {
		super(argument);
	}

	@Override
	public double accept(Visitor v) {
		return v.visit(this);
	}
}

/**
 * Clasa pentru operatorul unar sinus
 * 
 * @author andrei
 *
 */
class SinOperator extends UnaryOperator {

	public SinOperator(ExpressionTreeNode argument) {
		super(argument);
	}

	@Override
	public double accept(Visitor v) {
		return v.visit(this);
	}
}

/**
 * Clasa pentru operatorul unar cosinus
 * 
 * @author andrei
 *
 */
class CosOperator extends UnaryOperator {

	public CosOperator(ExpressionTreeNode argument) {
		super(argument);
	}

	@Override
	public double accept(Visitor v) {
		return v.visit(this);
	}
}

/**
 * Clasa pentru operatorul unar minus (schimbare de semn)
 * 
 * @author andrei
 *
 */
class NegativeOperator extends UnaryOperator {

	public NegativeOperator(ExpressionTreeNode argument) {
		super(argument);
	}

	@Override
	public double accept(Visitor v) {
		return v.visit(this);
	}
}