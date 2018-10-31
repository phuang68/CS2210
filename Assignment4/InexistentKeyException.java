/*
 *Course£ºCS2210B
 *Author: Pu Huang
 *Student Number: 250986943
 */
public class InexistentKeyException extends RuntimeException {

	public InexistentKeyException(String key) {
		super("The " + key);
	}
}
