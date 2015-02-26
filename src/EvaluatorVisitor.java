
/**
 * Vizitatorul cu ajutorul caruia se parcurge si se evalueaza arborele de
 * parsare. Prin recursivitate calculeaza valoare pe care fiecare nod, in
 * functie de tipul sau, o returneaza cand este evaluat.
 * 
 * @author andrei
 *
 */
public class EvaluatorVisitor implements Visitor {

	@Override
	public double visit(ExpressionTreeNode node) throws EvaluatorException {
		return node.accept(this);
	}

	@Override
	public double visit(AdditionOperator node) throws EvaluatorException {
		return node.getLeft().accept(this) + node.getRight().accept(this);
	}

	@Override
	public double visit(SubtractionOperator node) throws EvaluatorException {
		return node.getLeft().accept(this) - node.getRight().accept(this);
	}

	@Override
	public double visit(MultiplicationOperator node) throws EvaluatorException {
		return node.getLeft().accept(this) * node.getRight().accept(this);
	}

	@Override
	public double visit(DivisionOperator node) throws EvaluatorException {
		double val = node.getRight().accept(this);

		/* Se trateaza cazul impartirii la zero */
		if (val == 0) {
			throw new EvaluatorException(EvaluatorException.ZERO_DIV);
		}
		return node.getLeft().accept(this) / val;
	}

	@Override
	public double visit(PowerOperator node) throws EvaluatorException {
		return Math.pow(node.getLeft().accept(this),
				node.getRight().accept(this));
	}

	@Override
	public double visit(LogOperator node) throws EvaluatorException {
		double val = node.getArgument().accept(this);

		/* Se trateaza cazul argumentului nul */
		if (val == 0) {
			throw new EvaluatorException(EvaluatorException.ZERO_LOG);
		}

		/* Se trateaza cazul argumentului negativ */
		if (val < 0) {
			throw new EvaluatorException(EvaluatorException.NEG_LOG);
		}
		return Math.log10(val);
	}

	@Override
	public double visit(SqrtOperator node) throws EvaluatorException {
		double val = node.getArgument().accept(this);

		/* Se trateaza cazul argumentului negativ */
		if (val < 0) {
			throw new EvaluatorException(EvaluatorException.NEG_SQRT);
		}
		return Math.sqrt(val);
	}

	@Override
	public double visit(SinOperator node) throws EvaluatorException {
		return Math.sin(node.getArgument().accept(this));
	}

	@Override
	public double visit(CosOperator node) throws EvaluatorException {
		return Math.cos(node.getArgument().accept(this));
	}

	@Override
	public double visit(NegativeOperator node) throws EvaluatorException {
		return (-1) * node.getArgument().accept(this);
	}

	@Override
	public double visit(Constant node) throws EvaluatorException {
		return node.getValue();
	}

}
