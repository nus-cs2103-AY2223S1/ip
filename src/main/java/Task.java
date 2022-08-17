/**
 * This class represents tasks added by the user.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for creating a task.
     * @param description Task description from user input.
     */
    //@@author carriezhengjr-reused
    // Reused code under subsection "Extension: A-Classes" of the section "Duke Level-3: Mark as Done"
    // from https://nus-cs2103-ay2223s1.github.io/website/schedule/week2/project.html#ip
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Checks task description.
     * @return Task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Checks task status.
     * @return True if the task is done; false otherwise.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Updates task status.
     * @param isDone Whether the task should be marked as done or undone.
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * The icon to display task status.
     * @return X when task is done; empty otherwise.
     */
    //@@author carriezhengjr-reused
    // Reused code under subsection "Extension: A-Classes" of the section "Duke Level-3: Mark as Done"
    // from https://nus-cs2103-ay2223s1.github.io/website/schedule/week2/project.html#ip
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

}
