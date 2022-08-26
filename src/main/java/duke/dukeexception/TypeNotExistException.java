package duke.dukeexception;

/**
 * The Exception shown when the task type retrieved from the cache file is invalid.
 */
public class TypeNotExistException extends DukeException {
    /**
     * The error shown when the task type retrieved from the cache file is invalid.
     * @param msg error message.
     */
    public TypeNotExistException(String msg) {
        super(msg + "\n     :( OOPS!!! The task type is invalid, it must be one of T/D/E. PLease check!");
    }
}
