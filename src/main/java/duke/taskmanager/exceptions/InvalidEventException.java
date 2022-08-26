package duke.taskmanager.exceptions;

public class InvalidEventException extends Exception {
    public InvalidEventException(String dateFormat) {
        super("You have an invalid event time!\nEvent times should be in the format: " + dateFormat + "\n");
    }
}