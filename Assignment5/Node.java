/*
 *Course£ºCS2210B
 *Author: Pu Huang
 *Student Number: 250986943
 */

public class Node {
	private int name;
	private boolean mark;

	// This is the constructor for the class. Creates a node with the given name.
	Node(int name) {
		this.name = name;
	}

	// This method returns the value with which the node has been marked
	public boolean getMark() {
		return mark;
	}

	// This method marks the node with the specified value.
	public void setMark(boolean mark) {
		this.mark = mark;
	}

	// This method returns the name of the node
	public int getName() {
		return name;
	}
}
