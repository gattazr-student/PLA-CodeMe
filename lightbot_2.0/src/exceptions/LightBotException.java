package exceptions;

public class LightBotException extends Exception {

	/**
	 * generated UID
	 */
	private static final long serialVersionUID = 8264750889477024411L;

	/**
	 * Construction d'une exception OutOfBounds avec le message spécifié
	 *
	 * @param aString
	 *            message
	 */
	public LightBotException(String aString) {
		super(aString);
	}

}
