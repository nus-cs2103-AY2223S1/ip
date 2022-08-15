package duke.tasks;

public class TaskFactory {

    private enum TaskType {
        TODO, DEADLINE, EVENT
    }

    public static Task createTask(String taskType, String inputDesc) {
        if (taskType.isEmpty()) {
            return null;
        }

        switch (TaskType.valueOf(taskType.toUpperCase())) {
        case TODO:
            return new ToDo(inputDesc);
        case DEADLINE:
            return new Deadline(inputDesc);
        case EVENT:
            return new Event(inputDesc);
        }

        return null;
    }
}
