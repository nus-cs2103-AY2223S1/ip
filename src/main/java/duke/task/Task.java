package duke.task;
import java.util.ArrayList;

import duke.storage.DukeEncoder;
import duke.ui.Constants;


/**
 * Represents a general task.
 */
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
    public String markAsDone(ArrayList<Task> workList) {
        this.isDone = true;
        // Update data
        DukeEncoder.rewriteList(workList);
        return Constants.MARK_AS_DONE_MESSAGE + this;
    }

    /**
     * Mark task as not Done and Print acknowledge message.
     */
    public String unmark(ArrayList<Task> workList) {
        this.isDone = false;
        // Update data
        DukeEncoder.rewriteList(workList);
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
