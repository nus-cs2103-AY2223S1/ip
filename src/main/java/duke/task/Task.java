package duke.task;

import duke.DukeException;

public abstract class Task {
    private static final String NO_DESC_MESSAGE = "The description of a Task must not be empty.";

    protected String desc;
    protected boolean isDone;

    public Task(String desc) throws DukeException {
        if (desc == null || desc.equals("")) {
            throw new DukeException(Task.NO_DESC_MESSAGE);
        }
        this.desc = desc;
        this.isDone = false;
    }

    public Task(String desc, boolean isDone) throws DukeException {
        if (desc == null || desc.equals("")) {
            throw new DukeException(Task.NO_DESC_MESSAGE);
        }
        this.desc = desc;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (this.isDone ? "[✔]" : "[ ]");
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.desc;
    }

    public String toStorageString() {
        return String.format("%s / %s", this.desc, this.isDone);
    }

    /**
     * Returns true if and only if the provided keyword exists as a substring in the Task description.
     *
     * @param keyword the substring to search for
     * @return true if the Task description contains keyword, false otherwise
     */
    public boolean hasSubstring(String keyword) {
        return this.desc.contains(keyword);
    }

}
