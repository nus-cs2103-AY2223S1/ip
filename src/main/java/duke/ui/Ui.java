package duke.ui;

import duke.model.Task;
import duke.model.TaskList;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    /**
     * Greets the user by printing some lines upon start-up.
     *
     * @return A response to the user with respect to the commmand.
     */
    public static String greetUser() {
        return "Charmposter! What move should I use?";
    }

    /**
     * Bids farewell to the user by printing some lines before exit.
     *
     * @return A response to the user with respect to the commmand.
     */
    public static String sayBye() {
        return "Nooooo, not the pokeball! AHHHHHH!\n*pop*";
    }

    /**
     * Displays the relevant information when a Task is added into the TaskList.
     *
     * @param task A Task that is added to the TaskList.
     * @return A response to the user with respect to the commmand.
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
     * @param taskList A TaskList to be displayed.
     * @return A response to the user with respect to the commmand.
     */
    public static String list(TaskList taskList) {
        return taskList.toString();
    }

    /**
     * Displays the relevant information when a Task is deleted from a TaskList.
     *
     * @param task A Task that is deleted from a TaskList.
     * @return A response to the user with respect to the commmand.
     */
    public static String delete(Task task) {
        String str = "Charmposter! I've incinerated the following task!\n" + task;
        return str;
    }

    /**
     * Displays the relevant information when a Task is mark as done.
     *
     * @param task A Task to be marked as done.
     * @return A response to the user with respect to the commmand.
     */
    public static String mark(Task task) {
        String str = "Charmposter! I've marked this task as done!\n\t" + task;
        return str;
    }

    /**
     * Displays the relevant information when a Task is mark as not done.
     *
     * @param task a Task to be marked as not done.
     * @return A response to the user with respect to the commmand.
     */
    public static String unmark(Task task) {
        String str = "Charmposter! I've unmarked this task for you!\n\t" + task;
        return str;
    }

    /**
     * Searches and displays a list of tasks which match the input description.
     *
     * @param description Keywords for searching the TaskList.
     * @param taskList The TaskList to search in.
     * @return A response to the user with respect to the commmand.
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

    /**
     * Displays the relevant information when undoing a command.
     *
     * @param isReverted A boolean representing if undoing of command is successful.
     * @param lastUserInput The user input given for the previous command to be undone.
     * @return A response to the user with respect to the commmand.
     */
    public static String undoCommandMessage(Boolean isReverted, String lastUserInput) {
        if (isReverted) {
            return "Alright! I've reverted your last change:\n\t" + lastUserInput;
        } else {
            return "Sorry, I'm unable to undo any past changes!";
        }
    }
}
