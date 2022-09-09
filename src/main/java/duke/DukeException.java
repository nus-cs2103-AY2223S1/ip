package duke;

/** A class that creates a DukeException for invalid user inputs. */
public class DukeException extends Exception{

    /**
     * A constructor for the DukeException.
     *
     * @param errorMessage Error message to be printed out.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

}
