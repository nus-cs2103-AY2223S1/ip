package duke.exception;

import java.nio.file.Path;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(int idTask) {
        super("Task No.: " + String.valueOf(idTask) + " is not found in the task list. \n" +
                "Try command [list] to show the tasks you may choose from.");
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof TaskNotFoundException) {
            TaskNotFoundException e = (TaskNotFoundException) obj;
            if (e == null) {
                return false;
            }
            return this.getMessage().equals(e.getMessage());
        }
        return false;
    }
}
