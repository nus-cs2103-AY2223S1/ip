package maria.task;

public abstract class Task {

    private final String name;
    private boolean isDone;

    public Task(String name, boolean isDone) throws TaskNoNameException {

        if (name.isEmpty()) {
            throw new TaskNoNameException("The name for a task cannot be empty.");
        }

        this.name = name;
        this.isDone = isDone;
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    @Override
    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "] " + this.name;
    }

    public String toStorageString() {
        return this.name + "|||" + this.isDone;
    }

}
