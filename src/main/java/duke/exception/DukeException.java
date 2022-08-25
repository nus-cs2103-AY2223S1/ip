package duke.exception;

/**
 * The superclass of the exceptions related to the chatbot.
 */
public class DukeException extends RuntimeException{

    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
