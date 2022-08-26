package duke.taskmanager.exceptions;

public class InvalidDeadlineException extends Exception {
    public InvalidDeadlineException(String dateFormat) {
        super("You have an invalid deadline!\nDeadlines should be in the format: " + dateFormat + "\n");
    }
}