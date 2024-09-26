package duke.exception;

/**
 * A class representing trouble while loading
 *     saved tasks.
 */
public class LoadException extends DukeException {
    public LoadException() {
        super("There is a problem loading your safe file");
    }
}
