package duke.exceptions;

public class EmptyTaskDateException extends Exception {

    public EmptyTaskDateException() {
        super ("You need to specify a date.");
    }

}
