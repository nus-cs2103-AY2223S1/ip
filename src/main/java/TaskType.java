public enum TaskType {

    DEADLINE("D"),
    EVENT("E"),
    TODO("T");

    private final String task;

    TaskType(String task) {
        this.task = task;
    }

    public static TaskType parse(String task) throws DukeException {
        for (TaskType taskType : TaskType.values()) {
            if (taskType.task.equals(task)) {
                return taskType;
            }
        }
        throw new DukeException("Sorry, there is no such task type!");
    }
}
