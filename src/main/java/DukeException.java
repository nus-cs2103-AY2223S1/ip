/**
 * Class that inherits from Exception to represent exceptions specific to Duke.
 */
class DukeException extends Exception {
    /**
     * Constructor for DukeException class.
     *
     * @param message a String that represents the exception message
     */
    public DukeException(String message) {
        super(message);
    }
}