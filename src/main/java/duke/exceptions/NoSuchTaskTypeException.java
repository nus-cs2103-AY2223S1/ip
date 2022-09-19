package duke.exceptions;

/**
 * This exception indicates an unknown task type.
 */
public class NoSuchTaskTypeException extends Exception {

    public NoSuchTaskTypeException(String type) {
        super("I'm sorry, there is no task of type " + type);
    }

}
