/*
 *Course£ºCS2210B
 *Author: Pu Huang
 *Student Number: 250986943
 */
public class GraphicalObject implements GraphicalObjectADT {
	/*
	 * This class represents the GraphicalObject items that has an id as the
	 * identifier of the object,width and height as its width and height type as its
	 * type, and pos as the offset of the object
	 */
	private int id, width, height;
	private String type;
	private Location pos;
	private BinarySearchTree Gtree;

	public GraphicalObject(int id, int width, int height, String type, Location pos) {
		// Constructor that takes id,width,height,type and pos
		this.id = id;
		this.width = width;
		this.height = height;
		this.type = type;
		this.pos = pos;
		this.Gtree = new BinarySearchTree();
	}

	private Pixel findPixel(Location p) {
		if (Gtree.get(Gtree.getRoot(), p) == null)
			return null;
		else
			return Gtree.get(Gtree.getRoot(), p);
	}

	public void addPixel(Pixel pix) throws DuplicatedKeyException {
		// Inserts pix into the binary search tree associated with this graphical
		// object.
		// Throws a DuplicatedKeyException if an error occurs when inserting the Pixel
		// into the tree.
		if (findPixel(pix.getLocation()) == null)
			Gtree.put(Gtree.getRoot(), pix);
		else
			throw new DuplicatedKeyException("Error:Duplicated Key!");
	}

	public String getType() {
		// Returns the type of this graphical object.
		return type;
	}

	public void setType(String type) {
		// Sets the type of this graphical object to the specified value.
		this.type = type;
	}

	public Location getOffset() {
		// Returns the offset of this graphical object.
		return pos;
	}

	public void setOffset(Location pos) {
		// Changes the offset of this graphical object to the specified value
		this.pos = pos;
	}

	public int getId() {
		// Returns the id of this graphical object.
		return id;
	}

	public int getWidth() {
		// Returns the width of the enclosing rectangle for this graphical object.
		return width;
	}

	public int getHeight() {
		// Returns the height of the enclosing rectangle for this graphical object.
		return height;
	}

	public boolean intersects(GraphicalObject graphicalObject) {
		// Returns true if this graphical object intersects the one specified in the
		// parameter.
		// It returns false otherwise.

		// return the smallest location of the graphical Object
		Location thisSL = this.Gtree.smallest(this.Gtree.getRoot()).getLocation();
		// return the largest location of the graphical Object
		Location thisLL = this.Gtree.largest(this.Gtree.getRoot()).getLocation();
		// return the smallest location of the comparing graphical Object
		Location otherSL = graphicalObject.Gtree.smallest(graphicalObject.Gtree.getRoot()).getLocation();
		// return the largest location of the comparing graphical Object
		Location otherLL = graphicalObject.Gtree.largest(graphicalObject.Gtree.getRoot()).getLocation();

		while (thisSL != null && otherSL != null) {// check if the smallest location of both object has data or not
			if (thisSL.compareTo(thisLL) == 0 || otherSL.compareTo(otherLL) == 0) {// if they only have size of one
																					// pixel
				return false;
			} else {
				// return the relative location of the graphical object
				Location thisOffSet = new Location(thisSL.xCoord() + this.getOffset().xCoord(),
						thisSL.yCoord() + this.getOffset().yCoord());
				// return the relative location of the comparing graphical object
				Location otherOffSet = new Location(otherSL.xCoord() + graphicalObject.getOffset().xCoord(),
						otherSL.yCoord() + graphicalObject.getOffset().yCoord());
				// Comparing Offset,if they are insected they have the same relative location
				if (thisOffSet.compareTo(otherOffSet) == 0) {
					return true;
				} // if the offset is smaller than the comparing offset,check the next point if it
					// has the same offset
				else if (thisOffSet.compareTo(otherOffSet) == -1) {
					thisSL = this.Gtree.successor(this.Gtree.getRoot(), thisSL).getLocation();
				} // if the offset is bigger than the comparing offset,check the next point of
					// comparing offset if it has the same offset
				else {
					otherSL = graphicalObject.Gtree.successor(graphicalObject.Gtree.getRoot(), otherSL).getLocation();
				}
			}
		} // check every point and still couldn't find an intersect point,they are not
			// intersected
		return false;
	}

}
