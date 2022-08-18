package duke;

import duke.task.Task;

public class StorageWriter {
    static String writeSingleTask(Task task) {
        int done = task.getDoneStatus();
        switch (task.type) {
            case TODO:
                return String.format("%s | %d | %s", "T", done, task.getDescription());
            case DEADLINE:
                return String.format("%s | %d | %s| %s", "D", done, task.getDescription(), task.getBy());
            case EVENT:
                return String.format("%s | %d | %s| %s", "E", done, task.getDescription(), task.getBy());
            default:
                return "";
        }
    }
}
