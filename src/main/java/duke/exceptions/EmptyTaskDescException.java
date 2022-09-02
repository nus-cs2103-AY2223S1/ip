package duke.exceptions;

public class EmptyTaskDescException extends Exception {

    public EmptyTaskDescException() {
        super ("The description of a todo cannot be empty.");
    }

}
