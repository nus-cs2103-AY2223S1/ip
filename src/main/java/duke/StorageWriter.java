package duke;

public class StorageWriter {
    static String writeSingleTask(Task task) {
        int done = task.isDone ? 1 : 0;
        switch (task.type) {
            case TODO:
                return String.format("%s | %d | %s", "T", done, task.description);
            case DEADLINE:
                return String.format("%s | %d | %s| %s", "D", done, task.description, task.getBy());
            case EVENT:
                return String.format("%s | %d | %s| %s", "E", done, task.description, task.getBy());
            default:
                return "";
        }
    }
}
