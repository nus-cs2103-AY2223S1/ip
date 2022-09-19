package pikachu;

/**
 * Represents exception threw by Pikachu task manager bot. A <code>PikachuException</code> object corresponds to
 * an exception threw by Pikachu task manager bot.
 */
public class PikachuException extends Exception {

    /**
     * Takes in a String.
     * Throw exception with that string as the error message.
     */
    public PikachuException(String s) {
        super(s);
    }
}
