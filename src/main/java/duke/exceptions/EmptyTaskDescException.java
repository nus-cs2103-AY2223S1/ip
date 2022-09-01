package duke.exceptions;

public class EmptyTaskDescException extends Exception {

    public EmptyTaskDescException() {
        super ("OOPS!!! The description of a todo cannot be empty.");
    }

}
