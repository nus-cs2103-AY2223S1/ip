package duke.TaskList;

import Ui.Constants;

public class Task {
    protected String detail;
    protected boolean isDone;

    /**
     * Constructor for task.
     * @param detail String
     */
    public Task(String detail) {
        this.detail = detail;
        this.isDone = false;
    }

    /**
     * Constructor for task.
     * @param detail String
     */
    public Task(String detail, boolean isDone) {
        this.detail = detail;
        this.isDone = isDone;
    }
    /**
     * Get icon for status done or not
     * @return String
     */
    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] ");
    }

    /**
     * Mark task as Done and Print acknowledge message.
     */
    public String markAsDone() {
        this.isDone = true;
        return Constants.MARK_AS_DONE_MESSAGE + this;
    }

    /**
     * Mark task as not Done and Print acknowledge message.
     */
    public String unmark() {
        this.isDone = false;
        return Constants.UNMARK_MESSAGE + this;
    }

    /**
     * Returns String form of the task
     * @return String
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.detail;
    }

    protected String statusIcon() {
        return (this.isDone ? "1" : "0");
    }

    /**
     * Returns String to be stored in the hardware list.
     * @return String
     */
    public String storedData() {
        return statusIcon() + "|" + detail;
    }

    public boolean contain(String s) {
        return detail.contains(s);
    }
}
