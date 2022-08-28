package duke;

/**
 * Task class that creates a task inputted by user.
 */
public abstract class Task {
    protected String item;
    protected boolean done;

    /**
     * The constructor of the Task class.
     * @param item The item that the task contains.
     */
    public Task(String item) {
        this.item = item;
        this.done = false;
    }
    @Override
    public String toString() {
        if (!done) {
            return "[ ] " + item;
        } else {
            return "[X] " + item;
        }
    }

    /**
     * Returns the task in a String that can be recognised by the text file.
     * @return A string that can can be recognised by the text file.
     */
    public abstract String toLine();

    /**
     * Sets the task as done.
     */
    public void setDone() {
        this.done = true;
    }

    /**
     * Sets the task as undone.
     */
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
