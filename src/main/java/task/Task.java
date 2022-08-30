package task;

/**
 * Represents a task
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String icon;

    /**
     * Instantiates a new task
     */
    public Task(String description, String icon) {
        this.description = description;
        this.isDone = false;
        this.icon = icon;
    }

    /**
     * Returns the status icon for the task
     *
     * @return String format for the status icon for the task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }


    /**
     * Returns the description for the task
     *
     * @return description for the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets task to done
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Sets task to not done
     */
    public void unmark() {
        this.isDone = false;
    }


    @Override
    public boolean equals(Object o) {
        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        if (!(o instanceof Task)) {

            return false;
        }

        Task c = (Task) o;

        // Compare the data members and return accordingly

        return this.description.equals(c.description);
    }

    @Override
    public String toString() {
        return this.description;
    }

    /**
     * Returns icon of the task
     *
     * @return String format of the icon of the task
     */
    public String getIcon() {
        return this.icon;
    }

    /**
     * Returns the string format for saving into a file
     *
     * @return String format for saving into a file
     */
    public String toSave() {
        return this.description;
    }
}