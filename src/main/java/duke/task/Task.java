package duke.task;

import duke.util.DukeException;

import java.util.Objects;

/**
 * Task Class to store information of task.
 */
public class Task {
    protected String action;
    protected boolean isDone;
    protected String date;

    /**
     * Creates a new task object.
     * @param action user input action to be done.
     * @param isDone check is completed.
     * @param date date to be completed.
     * @throws DukeException if action is not specified.
     */
    public Task(String action, boolean isDone, String date) throws DukeException {
        if (Objects.equals(action, "")) {
            throw new DukeException();
        }
        this.action = action;
        this.isDone = isDone;
        this.date = date;
    }

    public String getAction() {
        return action;
    }

    public boolean getDone() {
        return isDone;
    }

    public String getDate() {
        return date;
    }

    public void markDone() {
        isDone = true;
    }

    public void markUnDone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return ("[" + (isDone ? "X" : " ") + "] " + action);
    }
}
