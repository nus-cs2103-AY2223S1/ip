package duke;

public abstract class Task {
    private boolean isDone;
    private String description;
    private String taskIcon;

    /**
     * Constructor for Task
     *
     * @param description Description of the task
     * @param taskIcon    Icon of the task (e.g "E" for "event")
     * @param isDone      boolean indicating if the task is done
     */
    public Task(String description, String taskIcon, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.taskIcon = taskIcon;
    }

    /**
     * Returns String representation of the Task object
     *
     * @return String representation of the Task object
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.taskIcon, this.getStatusIcon(), this.description);
    }

    /**
     * Getter for statusIcon
     *
     * @return Status icon of Task as String
     */
    private String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    /**
     * Marks Task as done
     *
     * @return String to be displayed to user after marking Task as done
     */
    public String markAsDone() {
        this.isDone = true;
        return String.format("Nice! I've marked this task as done:\n  %s\n", this);
    }

    /**
     * Marks Task as not done
     *
     * @return String to be displayed to user after marking Task as not done
     */
    public String markAsNotDone() {
        this.isDone = false;
        return String.format("OK, I've marked this task as not done yet:\n  %s\n", this);
    }

    /**
     * Return String representation of Task to be saved in Txt file
     *
     * @return String representation of Task to be saved in Txt file
     */
    public String getTxtString() {
        return String.format("%b %s", isDone, description);
    }

    /**
     * Checks if keyword is in Task's description
     *
     * @param keyword String keyword to search for in Task
     * @return boolean indicating if keyword exists in Task's description
     */
    public boolean containsKeyword(String keyword) {
        return this.description.contains(keyword);
    }
}
