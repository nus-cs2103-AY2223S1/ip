package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Deals with interactions with the user.
 *
 * @author ish1506
 */
public class Ui {
    public String getWelcomeMessage() {
        return "Hello! I'm Duke :)\nHow can I help you?";
    }

    public String getGoodbyeMessage() {
        return "Bye! See you again :)";
    }

    public String getMarkMessage(Task task) {
        return "Great! This task is completed:\n" + task;
    }

    public String getUnmarkMessage(Task task) {
        return "Okay, this task is now unchecked:\n" + task;
    }

    public String getAddTaskMessage(Task task) {
        return "Got it. I've added this task:\n" + task;
    }

    public String getDeleteTaskMessage(Task task) {
        return "Noted. I've removed this task:\n" + task;
    }

    public String getFoundTasksListString(TaskList list) {
        if (list.isEmpty()) {
            return "OOPS!! No matching tasks found :( ";
        }
        StringBuilder stringBuilder = new StringBuilder("Here are the matching tasks in your list:\n");
        int i = 1;
        for (Task task : list) {
            stringBuilder.append(i + ". " + task + "\n");
            i++;
        }
        return stringBuilder.toString();
    }
}
