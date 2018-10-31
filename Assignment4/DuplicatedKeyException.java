/*
 *Course£ºCS2210B
 *Author: Pu Huang
 *Student Number: 250986943
 */
public class DuplicatedKeyException extends RuntimeException {
	/**
	 * It returns the error message when there's duplicated key
	 */
	public DuplicatedKeyException(String key) {
		super("The " + key + " is duplicated");
	}
}
