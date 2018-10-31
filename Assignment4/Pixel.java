/*
 *Course£ºCS2210B
 *Author: Pu Huang
 *Student Number: 250986943
 */
public class Pixel {
	/*
	 * This class represents the data items to be stored in the binary search tree
	 * Each data item consists of two parts: a Location and an int color
	 */
	private int color;
	private Location location;

	public Pixel(Location p, int color) {
		// A constructor which initializes the new Pixel with the specified coordinates
		// and color.
		this.location = p;
		this.color = color;
	}

	public int getColor() {
		// Returns the color of the Pixel
		return this.color;
	}

	public Location getLocation() {
		// Returns the Location of the Pixel.
		return this.location;
	}

}
