package duke.task;

import duke.exception.EmptyDescException;

public class Task {
    protected String desc;
    protected boolean isDone;

    /**
     * Constructor.
     * @param desc Description of task.
     * @throws EmptyDescException For empty descriptions.
     */
    public Task(String desc) throws EmptyDescException {
        if (desc.equals("")) {
            throw new EmptyDescException("task");
        }
        this.desc = desc;
        this.isDone = false;
    }

    /**
     * @return string that describes whether task is marked as done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * @return Description of the task as string.
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as not done yet.
     */
    public void unmarkDone() {
        this.isDone = false;
    }

    public String toString() {
        return "[" + getStatusIcon() + "]" + desc;
    }

    public String toSave() { return "? | " + (isDone ? "1" : "0") + " | " + desc; }
}
