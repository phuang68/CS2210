
public class InexistentKeyException extends Exception {
	/**
	 * Sets up this exception with an appropriate message.
	 * 
	 * @param collection
	 *            String representing the name of the collection
	 */
	public InexistentKeyException(String collection) {
		super("The " + collection + " is inexistent.");
	}
}