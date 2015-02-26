/**
 * Interfata pentru clasele ce pot fi vizitate. Nodurile din arborele de parsare
 * implementeaza aceasta interfata.
 * 
 * @author andrei
 *
 */
public interface Visitable {

	/**
	 * Metoda prin care obiectul accepta vizitatorul. Nodul este parcurs si
	 * evaluat.
	 * 
	 * @param v
	 *            vizitator
	 * 
	 * @return valoarea pe care o returneaza vizitatorul
	 * 
	 * @throws EvaluatorException
	 *             exceptiile ce pot sa apara
	 */
	public double accept(Visitor v);

}