package doke;

/**
 * An Exception to handle the Exception specific to the Doke program.
 *
 * @author Stevan Gerard Gunawan
 */
public class DokeException extends RuntimeException {
    private String text = null;

    /**
     * Constructs the DokeException with the string field set to the given text
     *
     * @param text the text to be outputted.
     */
    public DokeException(String text) {
        this.text = text;
    }

    /**
     * Constructs the DokeException with the string field set to the given text
     */
    public DokeException() {
    }

    /**
     * Returns a String representation of the DokeException.
     *
     * @return a string.
     */
    @Override
    public String toString() {
        String temp = text == null
                ? "What language are you talking? JK. Something went wrong."
                : "I'm sorry, your " + text + "'s need more information.";
        return "_________________________" + "\n" + temp
                + "\n" + "_________________________";
    }
}
