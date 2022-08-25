/**
 * Creates an exception that deals with the error in this specific program
 */
package Duke;

public class DukeException extends Exception {

    public DukeException(String message) {
        super("☹ OOPS!!! " + message);
    }
}
