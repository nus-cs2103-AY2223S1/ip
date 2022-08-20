/**
 * This class represents tasks added by the user.
 * @author Carrie Zheng Jiarui
 * @version CS2103T AY22/23 Semester 1, iP
 */
public abstract class Task {
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
     * Marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks a task as not done.
     */
    public void markAsUndone() {
        this.isDone = false;
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

    /**
     * Displays the type of task.
     * @return A letter to represent the type of task.
     */
    public abstract String taskType();

    /**
     * Displays the task with its type, status (done or undone) and description.
     * @return Task type, status and description.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.taskType(), this.getStatusIcon(), this.getDescription());
    }

}
