package duke.task;

public enum TaskType {
    TODO("T"),
    EVENT("E"),
    DEADLINE("D");

    private final String task;

    TaskType(String task) {
        this.task = task;
    }

    public static TaskType parse(String string) {
        for (TaskType taskType : TaskType.values()) {
            if (taskType.task.equals(string)) {
                return taskType;
            }
        }
        throw new RuntimeException(String.format("Invalid task type %s.", string));
    }
}