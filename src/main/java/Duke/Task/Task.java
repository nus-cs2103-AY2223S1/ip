package Duke.Task;
/**
 * This is an abstract class representing the task that
 * the user wants to record. The task can be time sensitive
 * like a deadline or an event. It can also be non time sensitive
 * such as todo.
 */
public abstract class Task {

    enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    private TaskType type;

   /**
    * Marks the completion status of the task, 
    * True indicates that the user marks the task as
    * completed. 
    */
    private boolean isMarked = false;

    /** Detailed description of the task */
    private String description = "";

    /** Constructor for a general task */
    public Task(String description, TaskType type) {
        this.description = description;
        this.type = type;
    }

    public Task(String description, TaskType type, boolean isMarked) {
        this.description = description;
        this.type = type;
        this.isMarked = isMarked;
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
        return String.format("[%s][%s] %s", this.taskTypeString(),isMarked ? "X" : " ", description);
    }

    /**
     * Encodes the task for storage.
     * Format of the event is TASK_TYPE|IS_MARKED|DESCRIPTION.
     * @return String encoding of the task.
     */
    public String encodeForStorage() {
        return String.format("%s|%s|%s", this.taskTypeString(), this.isMarked ? "Y" : "N", this.description);
    }

    private String taskTypeString() {
        switch (this.type) {
        case TODO:
            return "T";
        case EVENT:
            return "E";
        case DEADLINE:
            return "D";
        default:
            return "";
        }
    }

    /**
     * Returns if the task contains the specified keyword in its
     * description.
     * 
     * @param keyword The keyword to be matched with the description.
     * @return True if description contains the keyword, false otherwise.
     */
    public boolean containsKeyword(String keyword) {
        return this.description.contains(keyword);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task) {
            return encodeForStorage().equals(((Task) obj).encodeForStorage());
        }
        return false;
    }

}
