package duke;

public abstract class Task {
    protected String item;
    protected boolean done;

    public Task(String item) {
        this.item = item;
        this.done = false;
    }

    /**
     * The toString method of the duke.Task class.
     *
     * @return The string representation of the duke.Task object.
     */
    @Override
    public String toString() {
        if (!done) {
            return "[ ] " + item;
        } else {
            return "[X] " + item;
        }
    }

    public abstract String toLine();

    public void setDone() {
        this.done = true;
    }

    public void setUnDone() {
        this.done = false;
    }

    /**
     * Checks if the task contains the keyword.
     * @param keyWord The keyword that is being checked.
     * @return True if the task contains the keyword, false otherwise.
     */
    public boolean contains(String keyWord) {
        return item.contains(keyWord);
    }
}
