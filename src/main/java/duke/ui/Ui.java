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
        return "Charmposter! What move should I use?";
    }

    /**
     * Bids farewell to the user by printing some lines before exit.
     */
    public static String sayBye() {
         return "Nooooo, not the pokeball! AHHHHHH!\n*pop*";
    }

    /**
     * Displays the relevant information when a Task is added into the TaskList.
     *
     * @param task a Task that is added to the TaskList
     */
    public static String add(Task task) {
        String str = "Charmposter! I've added this task for you!\n\t"
                + task + "\n"
                + "You now have " + Task.getNumOfTasks() + " tasks in your pokedex!";
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
        String str = "Charmposter! I've incinerated the following task!\n" + task;
        return str;
    }

    /**
     * Displays the relevant information when a Task is mark as done.
     *
     * @param task a Task to be marked as done
     */
    public static String mark(Task task) {
        String str = "Charmposter! I've marked this task as done!\n\t" + task;
        return str;
    }

    /**
     * Displays the relevant information when a Task is mark as not done.
     *
     * @param task a Task to be marked as not done
     */
    public static String unmark(Task task) {
        String str = "Charmposter! I've unmarked this task for you!\n\t" + task;
        return str;
    }

    /**
     * Searches and displays a list of tasks which match the input description.
     *
     * @param description keywords for searching the TaskList
     * @param taskList the TaskList to search in
     */
    public static String find(String description, TaskList taskList) {
        String str = "";

        int num = 1;
        for (int i = 1; i < Task.getNumOfTasks() + 1; i++) {
            Task task = taskList.getTask(i);
            if (task.contains(description)) {
                str += num + ". " + task + "\n";
                num += 1;
            }
        }
        if (str.equals("")) {
            str = "Charmposter! I can't find any matching tasks!";
        } else {
            str = "Charmposter! Here are the matching tasks in your pokedex!\n" + str;
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
