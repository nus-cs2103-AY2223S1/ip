package duke.ui;

import duke.model.Task;
import duke.model.TaskList;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    /**
     * Greets the user by printing some lines upon start-up.
     */
    public static String greetUser() {
        String str = "\tHey there! I'm Duke!\n"
                + "\tHow may I help you? :)";
        return str;
    }

    /**
     * Bids farewell to the user by printing some lines before exit.
     */
    public static String sayBye() {
         return "\tNice seeing you! Bye!";
    }

    /**
     * Displays the relevant information when a Task is added into the TaskList.
     *
     * @param task a Task that is added to the TaskList
     */
    public static String add(Task task) {
        String str = "\tGot it! I've added this task!\n"
                + "\t\t" + task + "\n"
                + "\tYou now have " + Task.getNumOfTasks() + " tasks in the list!";
        return str;
    }

    /**
     * Displays a list of tasks from a TaskList.
     *
     * @param taskList a TaskList to be displayed
     */
    public static String list(TaskList taskList) {
        return taskList.toString();
    }

    /**
     * Displays the relevant information when a Task is deleted from a TaskList.
     *
     * @param task a Task that is deleted from a TaskList
     */
    public static String delete(Task task) {
        String str = "\tAlright! The following task has been deleted!\n"
                + "\t\t" + task;
        return str;
    }

    /**
     * Displays the relevant information when a Task is mark as done.
     *
     * @param task a Task to be marked as done
     */
    public static String mark(Task task) {
        String str = "\tAlright! Marked this task as done!\n"
                + "\t\t" + task;
        return str;
    }

    /**
     * Displays the relevant information when a Task is mark as not done.
     *
     * @param task a Task to be marked as not done
     */
    public static String unmark(Task task) {
        String str = "\tOkay! Unmarked this task!\n"
                + "\t\t" + task;
        return str;
    }

    /**
     * Searches and displays a list of tasks which match the input description.
     *
     * @param description keywords for searching the TaskList
     * @param taskList the TaskList to search in
     */
    public static String find(String description, TaskList taskList) {
        String str = "\tHere are the matching tasks in your list!\n";

        int num = 1;
        for (int i = 1; i < Task.getNumOfTasks() + 1; i++) {
            Task task = taskList.getTask(i);
            if (task.contains(description)) {
                str += "\t\t" + num + ". " + task + "\n";
                num += 1;
            }
        }
        return str;
    }

    public static String undoCommandMessage(Boolean isReverted, String lastUserInput) {
        if (isReverted) {
            return "Alright! I've reverted your last change:\n\t" + lastUserInput;
        } else {
            return "Sorry, I'm unable to undo any past changes!";
        }
    }
}
