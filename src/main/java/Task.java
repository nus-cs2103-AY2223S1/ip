/**
 * This is an abstract class representing the task that
 * the user wants to record. The task can be time sensitive
 * like a deadline or an event. It can also be non time sensitive
 * such as todo.
 */
public abstract class Task {
   /**
    * Marks the completion status of the task, 
    * True indicates that the user marks the task as
    * completed. 
    */
    private boolean isMarked = false;

    /** Detailed description of the task */
    private String description = "";

    /** Constructor for a general task */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Marks the task as complete.
     * @return Prompt on the result of the marking
     */
    public String markTask() {
        this.isMarked = true;
        return "Nice! I've marked this task as done:\n " + this.toString();
    }

    /**
     * Unmarks the task as complete.
     * @return Prompt on the result of the unmarking
     */
    public String unmarkTask() {
        this.isMarked = false;
        return "OK, I've marked this task as not done yet:\n " + this.toString();
    }

    /**
     * A string representation of the task.
     * @return String representation of the task
     */
    @Override
    public String toString() {
        return String.format("[%c] %s", isMarked ? 'X' : ' ', description);
    }
}
