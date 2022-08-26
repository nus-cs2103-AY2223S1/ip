package duke.task;

import duke.DukeException;

public class Task {

    private final String taskDescription;
    private boolean isDone;
    private static final String ALREADY = "This task is already marked as ";

    Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    Task(String taskDescription, boolean isDone) {
        this.taskDescription = taskDescription;
        this.isDone = isDone;
    }

    public void doing() throws DukeException {
        if (!this.isDone) {
            this.isDone = true;
        } else {
            throw new DukeException(ALREADY + "done");
        }
    }

    public void undo() throws DukeException {
        if (this.isDone) {
            this.isDone = false;
        } else {
            throw new DukeException(ALREADY + "not done");
        }
    }

    public String getDescription() {
        return this.taskDescription;
    }
    public boolean isDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        String marker;
        if (this.isDone) {
            marker = "X";
        } else {
            marker = " ";
        }
        return "[" + marker + "] " + this.taskDescription;
    }
}
