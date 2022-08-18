/**
 * DukeException is to deal with errors such as incorrect inputs entered by the user.
 */

public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
