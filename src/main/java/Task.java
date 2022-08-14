public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) throws DukeException {
        if (description == null) {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s]%s", this.getStatusIcon(), this.description);
    }
}