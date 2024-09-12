package doke;

/**
 * An Exception to handle the Exception specific to the Doke program.
 */
public class DokeException extends RuntimeException {
    private String text = null;

    /**
     * a public constructor for the DokeException class.
     *
     * @param text the text to be outputted.
     */
    public DokeException(String text) {
        assert text.equals("todo") || text.equals("deadline") || text.equals("event")
                : "we only have 3 types of task";
        this.text = text;
    }

    /**
     * a public constructor for the DokeException class.
     */
    public DokeException() {}

    /**
     * Returns a String representation of the DokeException.
     *
     * @return a string.
     */
    @Override
    public String toString() {
        String temp = text == null
                ? "What language are you talking? JK. Something went wrong."
                : "I'm sorry, your " + text + "'s description can't be empty";
        return "_________________________" + "\n" + temp
                + "\n" + "_________________________";
    }
}
