package duke;

import duke.task.Task;
import duke.task.TaskList;

public class Ui {
    private static final char lineBreak = '\n';
    private static final String indent = "      ";

    private static final String greetingMessage = "Hi, I'm Lurch." + lineBreak + "You rang?";
    private static final String noTasksMessage = "You have no tasks in the list right now";
    private static final String byeMessage = "Have a lovely day with dark and cloudy skies.";
    private static final String oopsMessage = "Oh bother!";

    public static String getGreetingMessage() {
        return greetingMessage;
    }

    public static String getTaskStatusString(String prefix, Task task) {
        return prefix + lineBreak + indent + task;
    }

    public static String getTaskListString(TaskList taskList) throws DukeException {
        String msg = "";
        if (taskList.getSize() == 0) {
            throw new DukeException(noTasksMessage);
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
        return byeMessage;
    }

    public static String getErrorMessageString(DukeException exc) {
        return oopsMessage + lineBreak + exc;
    }
}
