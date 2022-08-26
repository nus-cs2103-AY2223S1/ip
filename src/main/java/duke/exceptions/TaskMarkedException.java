package duke.exceptions;

public class TaskMarkedException extends DukeException {
    public TaskMarkedException(int message) {
        super("TASK " + message + " ALREADY MARKED!");

    }
}
