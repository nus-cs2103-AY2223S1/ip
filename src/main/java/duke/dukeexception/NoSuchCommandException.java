package duke.dukeexception;

/**
 * The exception shown when user entered sth random.
 */
public class NoSuchCommandException extends DukeException {
    /**
     * The exception shown when user entered sth random.
     * @param msg error message.
     */
    public NoSuchCommandException(String msg) {
        super(msg + "\nThis is a invalid command code. PLease check!");
    }
}
