package duke.models;

import duke.exceptions.DukeException;

public class Task {
    protected boolean isDone;
    protected String description;
    protected int taskNumber;

    public Task(String desc) {
        this.description = desc;
        this.isDone = false;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public String toSave() {
        return (this.isDone ? " | 1 | " : " | 0 | ");
    }
    public String getStatusIcon() {
        return (isDone? "X": " ");
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setUndone() {
        this.isDone = false;
    }

    public String getTaskName() {
        return this.description;
    }

    public int getTaskNumber() {
        return this.taskNumber;
    }

    public void snooze(String newDate) throws DukeException {
        return ;
    }
}
