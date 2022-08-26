public abstract class Task {
    private final String description;
    private boolean isDone;

    public Task(String description) throws DukeException {
        this(description, false);
    }

    public Task(String description, boolean isDone) throws DukeException {
        if (description == null || description.length() == 0) {
            throw new DukeException("Description cannot be empty.");
        }
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String getAddMessage(int numberTasks) {
        return "Got it. I've added this task:\n" + this + "\nNow you have " + numberTasks + " tasks in the list";
    }

    public String encode() {
        return (this.isDone ? 1 : 0) + " | " + this.description;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
