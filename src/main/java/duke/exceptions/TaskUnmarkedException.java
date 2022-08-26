package duke.exceptions;

public class TaskUnmarkedException extends DukeException {
    public TaskUnmarkedException(int message) {
        super("TASK " + message + " IS ALREADY UNMARKED!");
    }
}
