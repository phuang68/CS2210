import java.util.Iterator;

public class LinkedList<T> implements ListADT<T> {
	protected int count;
	protected LinearNode<T> head, tail;

	LinearNode<T> current;

	/**
	 * Creates an empty list.
	 */
	public LinkedList() {
		count = 0;
		head = tail = null;
	}

	public void insertFirst(LinearNode<T> node) {

		if (isEmpty()) {
			head = node;
			node.setNext(null);
		} else {
			node.setNext(head.getNext());
			head = node;
		}
		count++;

	}

	public void insertLast(LinearNode<T> node) {
		current = head;
		while (current != null) {

			current = current.getNext();
		}
		current = node;
		node = null;
		head = current;
		count++;
	}

	public T removeFirst() throws EmptyCollectionException {
		LinearNode<T> temp = head;
		head = head.getNext();
		count--;
		return temp.getElement();

	}

	public T removeLast() throws EmptyCollectionException {
		while (current != null) {
			current = current.getNext();
		}
		count--;
		return current.getElement();
	}

	public T remove(T targetElement) throws EmptyCollectionException, ElementNotFoundException {
		if (isEmpty()) {
			System.out.println("EmptyCollectionException");
			throw new EmptyCollectionException("LinkedList");
		}

		boolean found = false;
		LinearNode<T> previous = null;
		LinearNode<T> current = head;

		while (current != null && !found)
			if (targetElement.equals(current.getElement()))
				found = true;
			else {
				previous = current;
				current = current.getNext();
			}

		if (!found) {
			System.out.println("Element not found exception");
			throw new ElementNotFoundException("List");
		}
		if (size() == 1)
			head = tail = null;
		else if (current.equals(head))
			head = current.getNext();
		else if (current.equals(tail)) {
			tail = previous;
			tail.setNext(null);
		} else
			previous.setNext(current.getNext());

		count--;

		return current.getElement();
	}

	public boolean contains(T targetElement) throws EmptyCollectionException {
		while (current != null) {
			if (targetElement.equals(current.getElement())) {
				return true;
			}
			current = current.getNext();
		}
		return false;
	}

	public boolean isEmpty() {
		return (head == null);
	}

	public int size() {
		return count;
	}

	public String toString() {
		String result = "";
		LinearNode<T> current = head;

		if (isEmpty()) {
			result = "";
		} else {
			while (current.getNext() != null) {
				current = current.getNext();
				System.out.println("The value of current is " + current.toString());
				result += current.getElement() + ", ";
			}
		}
		return "List: " + result;
	}

	public T first() {
		return head.getElement();
	}

	public T last() {
		return tail.getElement();
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
