package duke.tasks;

public class TaskFactory {

    public static Task createTask(TaskType taskType, String inputDesc) {
        switch (taskType) {
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
