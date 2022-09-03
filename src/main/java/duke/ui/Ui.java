package duke.ui;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

public class Ui {
    private static final char lineBreak = '\n';
    private static final String indent = "      ";

    public static String getGreetingMessage() {
        return Message.GREETING.toString();
    }

    public static String getTaskStatusString(Message prefix, Task task) {
        return String.format("%s %s %s %s", prefix, lineBreak, indent, task);
    }

    public static String getTaskListString(TaskList taskList) throws DukeException {
        String msg = "";
        if (taskList.getSize() == 0) {
            throw new DukeException(Message.EMPTY);
        }
        for (int i = 0; i < taskList.getSize(); i++) {
            msg += i + 1 + ". " + taskList.get(i);
            if (i < taskList.getSize() - 1)  {
                msg += lineBreak;
            }
        }
        return msg;
    }

    public static String getTerminationString() {
        return Message.BYE.toString();
    }

    public static String getErrorMessageString(DukeException exc) {
        return String.format("%s %s %s", Message.ERROR, lineBreak, exc);
    }
}
