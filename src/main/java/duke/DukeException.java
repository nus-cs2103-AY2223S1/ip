package duke;

public class DukeException extends Exception {

    /**
     * Constructor for the duke.DukeException
     * @param errorMessage message to be shown when duke.DukeException is caught
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Text to be shown
     * @return text to be shown when duke.DukeException is caught
     */
    @Override
    public String getMessage() {
        return "Oops! " + super.getMessage();
    }
}
