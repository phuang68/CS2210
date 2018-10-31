public class LinearNode<E> {
	private LinearNode<E> next;
	private E element;

	public LinearNode() {
		next = null;
		element = null;
	}

	// Creates a node storing the inputed element
	/**
	 * @param elem
	 */
	public LinearNode(E elem) {
		next = null;
		element = elem;
	}

	// Returns the next node after the current one
	/**
	 * @return
	 */
	public LinearNode<E> getNext() {
		return next;
	}

	// Sets the node after the current one to the inputed node
	/**
	 * @param node
	 */
	public void setNext(LinearNode<E> node) {
		next = node;
	}

	// Returns the element stores in the node
	/**
	 * @return
	 */
	public E getElement() {
		return element;
	}

	// Sets the element stored in the node to the inputed element
	/**
	 * @param record
	 */
	public void setElement(E record) {
		element = record;
	}

}