package duke.task;

import duke.core.DukeException;

/**
 * A class that represents a task in the task list.
 */
public abstract class Task {
    protected String text = "";
    protected String details = "";
    protected boolean complete = false;

    public void setText(String text) {
        this.text = text;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public DukeException invalidParameterError() {
        return new DukeException("Invalid parameters!");
    }

    @Override
    public String toString() {
        return "[" + (complete ? "X" : " ") + "] " + text;
    }
}