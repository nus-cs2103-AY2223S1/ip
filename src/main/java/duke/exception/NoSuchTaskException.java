package duke.exception;

/**
 * NoSuchTaskException is thrown when the target task doesn't exist
 */
public class NoSuchTaskException extends DukeExceptions {

    public NoSuchTaskException(String msg) {
        super(msg);
    }
}
