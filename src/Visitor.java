/**
 * Interfata pentru obiectele ce pot vizita alte obiecte. Este folosita pentru
 * parcurgerea si evaluarea nodurilor din arborele de parsare.
 * 
 * @author andrei
 *
 */
public interface Visitor {

	/**
	 * Este parcurs si evaluat un nod de tip "ExpressionTreeNode"
	 * 
	 * @param node
	 * @return valoarea pe care o intoarce in urma evaluarii
	 * @throws EvaluatorException
	 *             exceptia la evaluare
	 */
	public double visit(ExpressionTreeNode node);

	/**
	 * Este parcurs si evaluat un nod de tip "AdditionOperator"
	 * 
	 * @param node
	 * @return valoarea pe care o intoarce in urma evaluarii
	 * @throws EvaluatorException
	 *             exceptia la evaluare
	 */
	public double visit(AdditionOperator node);

	/**
	 * Este parcurs si evaluat un nod de tip "SubtractionOperator"
	 * 
	 * @param node
	 * @return valoarea pe care o intoarce in urma evaluarii
	 * @throws EvaluatorException
	 *             exceptia la evaluare
	 */
	public double visit(SubtractionOperator node);

	/**
	 * Este parcurs si evaluat un nod de tip "MultiplicationOperator"
	 * 
	 * @param node
	 * @return valoarea pe care o intoarce in urma evaluarii
	 * @throws EvaluatorException
	 *             exceptia la evaluare
	 */
	public double visit(MultiplicationOperator node);

	/**
	 * Este parcurs si evaluat un nod de tip "DivisionOperator"
	 * 
	 * @param node
	 * @return valoarea pe care o intoarce in urma evaluarii
	 * @throws EvaluatorException
	 *             exceptia la evaluare
	 */
	public double visit(DivisionOperator node);

	/**
	 * Este parcurs si evaluat un nod de tip "PowerOperator"
	 * 
	 * @param node
	 * @return valoarea pe care o intoarce in urma evaluarii
	 * @throws EvaluatorException
	 *             exceptia la evaluare
	 */
	public double visit(PowerOperator node);

	/**
	 * Este parcurs si evaluat un nod de tip "LogOperator"
	 * 
	 * @param node
	 * @return valoarea pe care o intoarce in urma evaluarii
	 * @throws EvaluatorException
	 *             exceptia la evaluare
	 */
	public double visit(LogOperator node);

	/**
	 * Este parcurs si evaluat un nod de tip "SqrtOperator"
	 * 
	 * @param node
	 * @return valoarea pe care o intoarce in urma evaluarii
	 * @throws EvaluatorException
	 *             exceptia la evaluare
	 */
	public double visit(SqrtOperator node);

	/**
	 * Este parcurs si evaluat un nod de tip "SinOperator"
	 * 
	 * @param node
	 * @return valoarea pe care o intoarce in urma evaluarii
	 * @throws EvaluatorException
	 *             exceptia la evaluare
	 */
	public double visit(SinOperator node);

	/**
	 * Este parcurs si evaluat un nod de tip "CosOperator"
	 * 
	 * @param node
	 * @return valoarea pe care o intoarce in urma evaluarii
	 * @throws EvaluatorException
	 *             exceptia la evaluare
	 */
	public double visit(CosOperator node);

	/**
	 * Este parcurs si evaluat un nod de tip "NegativeOperator"
	 * 
	 * @param node
	 * @return valoarea pe care o intoarce in urma evaluarii
	 * @throws EvaluatorException
	 *             exceptia la evaluare
	 */
	public double visit(NegativeOperator node);

	/**
	 * Este parcurs si evaluat un nod de tip "Constant"
	 * 
	 * @param node
	 * @return valoarea pe care o intoarce in urma evaluarii
	 * @throws EvaluatorException
	 *             exceptia la evaluare
	 */
	public double visit(Constant node);
}
