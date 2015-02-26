
/**
 * Clasa de baza pentru constante. Constantele sunt noduri in arborele de
 * parsare.
 * 
 * @author andrei
 *
 */
public class Constant extends ExpressionTreeNode {

	private double value;

	/**
	 * Constructor
	 * 
	 * @param value
	 *            valoarea constantei
	 */
	public Constant(double value) {
		this.value = value;
	}

	/**
	 * Returneaza valoarea
	 * 
	 * @return value
	 */
	public double getValue() {
		return value;
	}

	@Override
	public double accept(Visitor v) {
		return v.visit(this);
	}

}
