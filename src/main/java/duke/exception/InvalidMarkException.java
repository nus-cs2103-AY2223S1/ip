package duke.exception;

public class InvalidMarkException extends DukeException{
    private static final String markUndone = "This task is already marked as undone:\n";
    private static final String markDone = "This task is already marked as done:\n";


    public InvalidMarkException(boolean mark, String taskString) {
        super(mark ? markDone + taskString : markUndone + taskString);
    }
}
