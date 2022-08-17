package exceptions;

public class DukeMissingIndexException extends DukeException {

    public DukeMissingIndexException() {
        super("Sorry! I could not find any item in that index");
    }
}
