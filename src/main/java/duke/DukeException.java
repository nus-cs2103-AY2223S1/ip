package duke;

/*
Exception that is unique to Duke
*/
public class DukeException extends Exception {
    public DukeException(String message) {
        super("oops!! " + message );
    }
}
