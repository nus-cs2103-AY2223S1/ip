package dukechatbot.dukeexception;
import java.io.IOException;

/**
 * The DukeException class implements the exceptions thrown by the Duke application when commands input
 * are unknown.
 *
 * @author A0233290M
 * @version Week3
 *
 */
public class DukeException extends IOException {
    /**
     * Constructs an instance of the DukeException class.
     *
     * @param message message to printed when a Duke Exception is thrown.
     */
    public DukeException(String message) {
        super(message);
    }
}
