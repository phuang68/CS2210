/*
 *Course£ºCS2210B
 *Author: Pu Huang
 *Student Number: 250986943
 */
public class Location {
	// This class represents the position (x, y) of a pixel.
	private int x, y, n;

	public Location(int x, int y) {
		// constructor that initializes the Location object with the specified
		// coordinates.
		this.x = x;
		this.y = y;
	}

	public int yCoord() {
		// Returns the y coordinate of this Location.
		return this.y;
	}

	public int xCoord() {
		// Returns the x coordinate of this Location.
		return this.x;
	}

	public int compareTo(Location p) {
		// Compares this Location with p using column order
		int n = 0;
		if (x > p.xCoord())
			n = 1;
		else if (x == p.xCoord() && y == p.yCoord())
			n = 0;
		else if (x == p.xCoord() && y > p.yCoord())
			n = 1;
		else if (x == p.xCoord() && y < p.yCoord())
			n = -1;
		else if (x < p.xCoord())
			n = -1;
		return n;
	}
}
