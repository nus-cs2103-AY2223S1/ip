package duke.exception;

import java.nio.file.Path;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(int idTask) {
        super("Task No.: " + String.valueOf(idTask) + " is not found in the task list. \n" +
                "Try command [list] to show the tasks you may choose from.");
    }
}
