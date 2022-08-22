package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Sets the isDone property to true.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Sets the isDone property to false.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns a string that is safe to use with the save file.
     *
     * @return String that is of the save file format.
     */
    public String saveText() {
        return String.format("Task|%d|%s", this.isDone ? 1 : 0, this.description);
    }

    @Override
    public String toString() {
        char statusIcon = this.isDone ? 'X' : ' ';
        return "[" + statusIcon + "] " + this.description;
    }
}