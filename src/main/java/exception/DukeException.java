package exception;
/**
 * A class that encapsulates exceptions that Duke throws
 *
 * @author  Wee Xin Yang, Markus
 * @version 0.1
 * @since   2022-8-24
 */
public class DukeException extends Exception {

    private String errorMessage;

    public DukeException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    /**
     * Returns a String object representing this DukeException's value.
     *
     * @return the string representation of the specified DukeException
     */
    @Override
    public String toString() {

        return (errorMessage + "LUNA reads words, not minds!");
    }

}