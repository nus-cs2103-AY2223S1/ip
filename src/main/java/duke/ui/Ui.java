package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Deals with interactions with the user.
 *
 * @author ish1506
 */
public class Ui {

    public void printWelcome() {
        System.out.println("Hello! I'm Duke" + "\nHow can I help you?");
    }

    public void printGoodbye() {
        System.out.println("Bye! See you again :)");
    }

    public void printMark(Task task) {
        System.out.println("Great! This task is completed:\n" + task);
    }

    public void printUnmark(Task task) {
        System.out.println("Okay, this task is now unchecked:\n" + task);
    }

    public void printAdd(Task task) {
        System.out.println("Got it. I've added this task:\n" + task);
    }

    public void printDelete(Task task) {
        System.out.println("Noted. I've removed this task:\n" + task);
    }

    public void printFoundTasksList(TaskList list) {
        if (list.isEmpty()) {
            System.out.println("OOPS!! No matching tasks found :( ");
            return;
        }
        System.out.println("Here are the matching tasks in your list: ");
        int i = 1;
        for (Task task : list) {
            System.out.println(i + ". " + task);
            i++;
        }
    }
}
