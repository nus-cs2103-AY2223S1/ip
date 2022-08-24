package duke.task;

public enum TaskType {
    TODO("T"),
    EVENT("E"),
    DEADLINE("D");

    /** Name of the task */
    private final String name;

    private TaskType(String name) {
        this.name = name;
    }

    public static TaskType parseToTaskType(String string) {
        for (TaskType taskType: TaskType.values()) {
            if (taskType.name.equals(string)) {
                return taskType;
            }
        }
        throw new RuntimeException(String.format("Invalid task type string %s.", string));
    }





}
