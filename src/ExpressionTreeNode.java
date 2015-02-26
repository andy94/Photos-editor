
/**
 * Clasa de baza pentru nodurile din arborele de parsare
 * 
 * @author andrei
 *
 */
public abstract class ExpressionTreeNode implements Visitable {

	@Override
	public double accept(Visitor v) {
		return v.visit(this);
	}

}
