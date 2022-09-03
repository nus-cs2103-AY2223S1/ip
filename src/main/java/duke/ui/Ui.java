package duke.ui;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

public class Ui {

    public static String getGreetingMessage() {
        return Message.GREETING.toString();
    }

    public static String getTaskStatusString(Message prefix, Task task) {
        return String.format("%s\n    %s", prefix, task);
    }

    public static String getTaskListString(TaskList taskList) throws DukeException {
        return taskList.getListString();
    }

    public static String getTerminationString() {
        return Message.BYE.toString();
    }

    public static String getErrorMessageString(DukeException exc) {
        return String.format("%s\n%s", Message.ERROR, exc);
    }
}
