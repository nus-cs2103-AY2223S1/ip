package duke.task;

public abstract class Task {
    protected final String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String toStringWithIndex(int index) {
        return index + "." + this.toString();
    }

    public abstract String toFileFormat();

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task) {
            Task tmp = (Task) obj;
            return tmp.description.equals(this.description) &&
                    tmp.isDone == this.isDone;
        }
        return false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}