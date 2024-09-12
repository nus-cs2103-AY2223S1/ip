package duke.task;

/**
 * Represents the different types of Task.
 */
public enum TaskType {
    TODO("T"),
    EVENT("E"),
    DEADLINE("D");

    private final String name;

    /**
     * Creates a TaskType with the corresponding name.
     * @param name The name of the TaskType.
     */
    TaskType(String name) {
        this.name = name;
    }

    /**
     * Returns the corresponding TaskType to the storage string.
     * @param storageString The storage string representing the task.
     * @return The TaskType corresponding to the storage string, if any.
     */
    public static TaskType parseToTaskType(String storageString) {
        for (TaskType taskType: TaskType.values()) {
            if (taskType.name.equals(storageString)) {
                return taskType;
            }
        }
        throw new RuntimeException(String.format("Invalid task type string %s.", storageString));
    }
}
