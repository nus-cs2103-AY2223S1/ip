package duke.task;

/**
 * ToDo is an extension of Task.
 */
public class ToDo extends Task {
    private String taskType;

    /**
     * Constructor for Task.
     * @param description task description.
     */
    public ToDo(String description) {
        super(description);
        this.taskType = "T";
    }

    /**
     * Constructor for Task.
     * @param description task description.
     * @param priority priority of task.
     */
    public ToDo(String description, String priority) {
        super(description, priority);
        this.taskType = "T";
    }

    /**
     * Returns task description.
     * @return task description.
     */
    public String getDescription() {
        return super.getDescription();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getTaskType() {
        return taskType;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
