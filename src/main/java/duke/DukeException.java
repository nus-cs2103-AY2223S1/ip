package duke;

/**
 * Class for handling Duke exceptions.
 *
 * @author benjytan45678
 * @version 0.1
 */
public class DukeException extends Exception {
    private String description;

    /**
     * Creates a DukeException with specified error message.
     *
     * @param description The error description for the exception.
     */
    public DukeException(String description) {

        this.description = description;
    }

    /**
     * Returns specified error message.
     *
     * @return Error message in String
     */
    @Override
    public String toString() {

        return description;
    }

}
