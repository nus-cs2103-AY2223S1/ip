package duke.task;

/**
 * TaskType represents the different types of Task.
 */
public enum TaskType {
    TODO("T"),
    EVENT("E"),
    DEADLINE("D");

    private final String task;

    /**
     * Creates a TaskType with the corresponding Task name.
     *
     * @param task The corresponding Task name.
     */
    TaskType(String task) {
        this.task = task;
    }


    /**
     * Returns the corresponding TaskType based on the input String.
     *
     * @return The corresponding TaskType.
     */
    public static TaskType parse(String string) {
        for (TaskType taskType : TaskType.values()) {
            if (taskType.task.equals(string)) {
                return taskType;
            }
        }
        throw new RuntimeException(String.format("Invalid task type %s.", string));
    }
}
