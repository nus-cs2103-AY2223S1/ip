package duke;

/**
 * Represents a Task to be completed by a user of Duke. A task has String body, a task type and completion status.
 */
public class Task {
    private final String desc;
    private final char completed;
    private final char taskType;
    private final static char STATUS_COMPLETE = 'X';
    private final static char STATUS_INCOMPLETE = ' ';

    public Task(String desc, char taskType) {
        this.desc = desc;
        completed = STATUS_INCOMPLETE;
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
     *
     * @return An identical Task object that is marked as complete.
     */
    protected Task completeTask() {
        return new Task(desc, STATUS_COMPLETE, taskType);
    }

    /**
     * Creates a Task instance that is identical to a given Task object, and then marked as incomplete.
     *
     * @return An identical Task object that is marked as incomplete.
     */
    protected Task resetTask() {
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
