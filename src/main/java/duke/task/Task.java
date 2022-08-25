package duke.task;

import duke.Command;

public abstract class Task {
    // original access modifier was protected
    protected final String description;
    private boolean isDone;
    protected final Command taskCommand;

    public Task(String description, Command taskCommand) {
        this.description = description;
        isDone = false;
        this.taskCommand = taskCommand;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void setTaskAsDone() {
        isDone = true;
    }

    public void setTaskAsNotDone() {
        isDone = false;
    }

    protected String getTaskDoneString(int index) {
        return isDone ? "mark " + index + "\n" : "";
    }

    abstract public String getFileStorageString(int index);

    public boolean isMatchingKeywordInDescription(String keyword) {
        return description.contains(keyword);
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
