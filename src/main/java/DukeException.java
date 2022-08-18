public class DukeException extends Exception {

    /**
     * Constructor for the DukeException
     * @param errorMessage message to be shown when DukeException is caught
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Text to be shown
     * @return text to be shown when DukeException is caught
     */
    @Override
    public String getMessage() {
        return "OOPS! " + super.getMessage();
    }
}
