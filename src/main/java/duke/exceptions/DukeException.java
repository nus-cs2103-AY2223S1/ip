package duke.exceptions;

public class DukeException extends Exception {

    public DukeException(String message) {
        super("Shiba says: \"OOPS! " + message + "\" ☹");
    }

}
