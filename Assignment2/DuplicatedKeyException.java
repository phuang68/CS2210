
public class DuplicatedKeyException extends RuntimeException {
	/**
	 * Sets up this exception with an appropriate message.
	 * 
	 * @param collection
	 *            String representing the name of the collection
	 */
	public DuplicatedKeyException(String collection) {
		super("The " + collection + " is duplicated.");
	}
}
