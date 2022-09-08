package duke;

/**
 * Task class that creates a task inputted by user.
 */
public abstract class Task {
    protected String item;
    protected boolean isDone;

    /**
     * The constructor of the Task class.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.item = description;
        this.isDone = false;
    }
    @Override
    public String toString() {
        if (!isDone) {
            return "[ ] " + item;
        } else {
            return "[X] " + item;
        }
    }

    /**
     * Returns the task in a String that can be recognised by the text file.
     *
     * @return A string that can can be recognised by the text file.
     */
    public abstract String toLine();

    /**
     * Sets the task as done or undone.
     *
     * @param isDone A boolean that changes the isDone state of the class.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Checks if the task contains the keyword.
     *
     * @param keyWord The keyword that is being checked.
     * @return True if the task contains the keyword, false otherwise.
     */
    public boolean contains(String keyWord) {
        return item.contains(keyWord);
    }
}
