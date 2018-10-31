
/*
 *Course£ºCS2210B
 *Author: Pu Huang
 *Student Number: 250986943
 */
public class BinarySearchTree implements BinarySearchTreeADT {
	/*
	 * This class implements an ordered dictionary using a binary search tree. Each
	 * node of the tree will store a Pixel object; the attribute Location of the
	 * Pixel will be its key.
	 */
	private BinaryNode root, f;
	private int size;
	private boolean found = false;

	public BinarySearchTree() {
		this.root = null;
		size = 0;
	}

	public BinaryNode getRoot() {
		// return root
		return this.root;
	}

	private BinaryNode find(BinaryNode r, Location key) {
		while (r != null) {
			if (key.compareTo(r.getData().getLocation()) == 0) {
				found = true;
				return r;
			} else if (key.compareTo(r.getData().getLocation()) == 1) {
				r = r.getRight();
			} else {
				r = r.getLeft();
			}
		}
		if (!found) {
			return null;
		} else
			return r;
	}

	public Pixel get(BinaryNode r, Location key) {
		if (find(r, key) == null) {
			return null;
		} else {
			return find(r, key).getData();
		}
	}

	// Returns the Pixel storing the given key, if the key is stored in the tree;
	// returns null otherwise.

	public void put(BinaryNode r, Pixel data) throws DuplicatedKeyException {
		// Inserts the given data in the tree
		// if no data item with the same key is already there.
		// If a node already stores the same key,
		// the algorithm throws a DuplicatedKeyException.
		if (size == 0) {// if the tree is empty,set the node as the root node
			root = new BinaryNode(data, null, null, r);
			size++;
			return;
		} else {// check if the inserting data already in the tree
			if (r.getData().getLocation().compareTo(data.getLocation()) == 0)
				throw new DuplicatedKeyException("Error: Duplicated key found");
			// if the inserting data is bigger than the node's data,put it on the left side
			// of the node
			else if (data.getLocation().compareTo(r.getData().getLocation()) == -1) {
				if (r.getLeft() == null) {// check if the node has left child already
					r.setLeft(new BinaryNode(data, null, null, r));
					size++;
					return;
				} else
					put(r.getLeft(), data);
			} // if the inserting data is smaller than the node's data,put it on the right
				// side of the node
			else if (data.getLocation().compareTo(r.getData().getLocation()) == 1) {
				if (r.getRight() == null) {// check if the node has right child already
					r.setRight(new BinaryNode(data, null, null, r));
					size++;
					return;
				} else
					put(r.getRight(), data);
			}
		}
	}

	public void remove(BinaryNode r, Location key) throws InexistentKeyException {
		// Removes the data item with the given key,
		// if the key is not stored in the tree;
		// throws an InexistentKeyException otherwise.
		BinaryNode mv = find(r, key);
		BinaryNode smallest;

		if (mv == null || r != root)// check if the removing node is in the tree
			throw new InexistentKeyException("BinaryNode r doesn't exist in the tree");
		else if (mv.getRight() != null) {// check if the romoving node has a right child
			if (mv.getRight().isLeaf()) {// check if the right child of removing node is empty
				mv.getLeft().setParent(mv.getParent());
				if (mv.getParent() == null)
					root = mv.getLeft();
				else if (mv.getParent().getLeft() == mv)
					mv.getParent().setLeft(mv.getLeft());
				else {
					mv.getParent().setRight(mv.getLeft());
				}
			} // if the removing node has a right child,need to remove in a special way
			else {
				smallest = mv.getRight();
				// find the smallest node in the removing node's right tree
				while (!smallest.isLeaf() && smallest.getLeft() != null)
					smallest = smallest.getLeft();
				// removing the data and let the node become the node stroing the smallest data
				// and it won't destroy the balance
				mv.setData(smallest.getData());
				// check if the removing node is the smallest node's parent
				if (smallest.getParent() == mv)
					mv.setRight(smallest.getRight());
				else {// if not,
					smallest.getParent().setLeft(smallest.getRight());
					smallest.getRight().setParent(smallest.getParent());
				}
			}
		} else {
			if (mv == root) {
				root = null;
			} else if (mv.getParent().getRight() == mv)
				mv.getParent().setRight(null);
			else
				mv.getParent().setLeft(mv.getLeft());
		}
		size--;
	}

	public Pixel successor(BinaryNode r, Location key) {
		// Returns the Pixel with the smallest
		// key larger than the given one (note that the tree does not need to store a
		// node with the given key).
		// Returns null if the given key has no successor.
		if (r == null) {
			return null;
		}
		if (r.getData().getLocation().compareTo(key) <= 0) {
			return successor(r.getRight(), key);
		} else {
			Pixel next = successor(r.getLeft(), key);
			if (next == null)
				return r.getData();
			else
				return next;
		}
	}

	public Pixel predecessor(BinaryNode r, Location key) {
		// TODO Auto-generated method stub
		if (r == null || size == 0) {
			return null;
		}
		if (r.getData().getLocation().compareTo(key) >= 0) {
			return predecessor(r.getLeft(), key);
		} else {
			Pixel next = predecessor(r.getRight(), key);
			if (next == null)
				return r.getData();
			else
				return next;
		}
	}

	public Pixel smallest(BinaryNode r) throws EmptyTreeException {
		// Returns the Pixel with the smallest key.
		// Throws an EmptyTreeException if the tree does not contain any data.
		if (r.equals(null))
			throw new EmptyTreeException("Error: Empty Tree!");
		else {
			while (r.getLeft() != null)
				r = r.getLeft();
			return r.getData();
		}
	}

	public Pixel largest(BinaryNode r) throws EmptyTreeException {
		// Returns the Pixel with the largest key.
		// Throws an EmptyTreeException if the tree does not contain any data
		if (r.equals(null))
			throw new EmptyTreeException("Error: Empty Tree!");
		else {
			while (r.getRight() != null)
				r = r.getRight();
			return r.getData();
		}
	}

}
