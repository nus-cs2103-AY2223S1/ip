package duke;

/**
 * Represents a Task to be completed by a user of Duke. A task has String body, a task type and completion status.
 */
public class Task {
    private final String desc;
    private final char completed;
    private final char taskType;

    public Task(String desc, char taskType) {
        this.desc = desc;
        completed = ' ';
        this.taskType = taskType;
    }

    public Task(String desc, char completed, char taskType) {
        this.desc = desc;
        this.completed = completed;
        this.taskType = taskType;
    }

    public Task(String taskString) {
        int firstBracketIndex = taskString.indexOf('[');
        taskString = taskString.substring(firstBracketIndex);
        taskType = taskString.charAt(1);
        completed = taskString.charAt(4);
        desc = taskString.substring(7);
    }

    /**
     * Creates a Task instance that is identical to a given Task object, and then marked as complete.
     * @return An identical Task object that is marked as complete.
     */
    protected Task performTask() {
        return new Task(desc, 'X', taskType);
    }

    /**
     * Creates a Task instance that is identical to a given Task object, and then marked as incomplete.
     * @return An identical Task object that is marked as incomplete.
     */
    protected Task undoTask() {
        return new Task(desc, taskType);
    }

    protected String getDesc() {
        return desc;
    }

    protected char getTaskType() {
        return taskType;
    }

    public String toString() {
        return String.format("[%c][%c] %s", taskType, completed, desc);
    }
}
