
/**
 * Clasa de parsare a expresiei
 * 
 * @author andrei
 *
 */
public class ExpressionParser {

	/**
	 * Metoda ce evalueaza expresia primita ca parametru
	 * 
	 * @param expression
	 *            expresia data
	 * @return valoarea expresiei
	 * @throws SyntacticException
	 *             exceptie ce tine de forma expresiei
	 * @throws EvaluatorException
	 *             exceptie ce tine de calculle in operatii
	 */
	public float eval(String expression) throws SyntacticException,
			EvaluatorException {

		/* Se creeaza constructorul arborelui de parsare */
		TreeBuilder treeBuilder = new TreeBuilder(expression);

		/* Se creeaza arborele si se determina radacina */
		ExpressionTreeNode root = treeBuilder.buildTree();

		/* Se creaza evaluatorul arborelui (vizitatorul nodurilor) */
		EvaluatorVisitor eval = new EvaluatorVisitor();

		/*
		 * Se returneaza valoarea expresiei - se alica vizitatorul pe nodul
		 * radacina
		 */
		return (float) root.accept(eval);
	}

}
