/*
 *Course£ºCS2210B
 *Author: Pu Huang
 *Student Number: 250986943
 */
public class BinaryNode {
	/*
	 * This class represents the nodes of the binary search tree. Each node will
	 * store an object of the class Pixel and it will have references to its left
	 * child, its right child, and its parent
	 */
	private Pixel value;
	private BinaryNode left;
	private BinaryNode right;
	private BinaryNode parent;

	public BinaryNode() {
		this.value = null;
		this.left = null;
		this.right = null;
		this.parent = null;
	}

	public BinaryNode(Pixel value, BinaryNode left, BinaryNode right, BinaryNode parent) {
		// A constructor for the class. Stores the Pixel in the node and sets left
		// child, right child, and parent tothe specified values.
		this.value = value;
		this.left = left;
		this.right = right;
		this.parent = parent;
	}

	public Pixel getData() {
		// Returns the Pixel object stored in this node.
		return value;
	}

	public void setData(Pixel value) {
		// Stores the given Pixel in this node.
		this.value = value;
	}

	public BinaryNode getLeft() {
		// Returns the left child of this node.
		return left;
	}

	public void setLeft(BinaryNode left) {
		// Sets the left child of this node to the specified value.
		this.left = left;
	}

	public BinaryNode getRight() {
		// Returns the right child of this node.
		return right;
	}

	public void setRight(BinaryNode right) {
		// Sets the right child of this node to the specified value.
		this.right = right;
	}

	public BinaryNode getParent() {
		// Returns the parent of this node
		return parent;
	}

	public void setParent(BinaryNode parent) {
		// Sets the parent of this node to the specified value.
		this.parent = parent;
	}

	public boolean isLeaf() {
		// Returns true if this node is a leaf; returns false otherwise
		if (this.getRight() == null && this.getLeft() == null)
			return true;
		else
			return false;

	}
}
