package duke.exceptions;

public class NoDateException extends DukeException {

    public NoDateException() {
        super("YOU DID NOT PROVIDE A DATE!");
    }
}
