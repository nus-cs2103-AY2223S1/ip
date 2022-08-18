public class DukeException extends Exception{
    /**
     * A constructor to intialize the DukeException object
     *
     * @param message The message sent when a DukeException is called
     */
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "OOPS!!! " + getMessage();
    }
}