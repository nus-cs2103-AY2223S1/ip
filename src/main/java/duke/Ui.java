package duke;

import java.util.ArrayList;

/**
 * Represents the Ui to deal with interactions with the user.
 */
public class Ui {
    private TaskList tasks;
    private final static String HOR_LINE = "------------------------------------------------------";

    public Ui() {

    }

    /**
     * Displays to the user the confirmatory message after saved tasks are loaded.
     *
     * @param size number of tasks loaded.
     */
    public String tasksLoadedMsg(int size) {
        String msg;
        if (size == 0) {
            msg = ("\tNo saved tasks were found.");
        } else {
            msg = ("\t" + size + " tasks were loaded.");
        }
        return msg;
    }

    public String editPriorityMsg(String pr) {
        String msg = "Priority for the task has been set to " + pr;
        return msg;
    }

    /**
     * Displays to the user the welcome message on running Duke.
     *
     * @return the welcome message.
     */
    public String welcomeMessage() {
        String msg = ("\tHello! I'm Baymax\n" +
                "\tWhat can I do for you?");
        return msg;
    }

    /**
     * Displays to the user the exit message on closing duke.
     */
    public String exitMessage() {
        String msg = ("\tBye. Hope to see you again soon!");
        return msg;
    }

    /**
     * Displays to the user the confirmatory message when a task is deleted
     *
     * @param task deleted task.
     * @param size number of tasks in the list after given tasks is deleted.
     */
    public String deleteTaskMsg(Task task, int size) {
        String msg = ("\tNoted, I have removed this task: \n" +
                "\t\t" + task.toString() + "\n" + "\tNow you have " +
                size + " tasks in the list.");
        return msg;
    }

    /**
     * Displays to the user the confirmatory message after a task is marked as done.
     *
     * @param task task marked as done.
     * @return the confirmatory message
     */
    public String taskDoneMsg(Task task) {
        String msg = ("\tNice! I've marked this task as done:\n" +
                "\t\t" + task.toString());
        return msg;
    }

    /**
     * Displays to the user the confirmatory message after a task is marked as undone.
     *
     * @param task task marked as undone.
     */
    public String taskUndoneMsg(Task task) {
        String msg = ("\tOK, I've marked this task as not done yet:\n" +
                "\t\t" + task.toString());
        return msg;
    }

    public String viewListMsg(ArrayList<Task> tasks) {
        if (tasks.size() != 0) {
            String msg = ("\tHere are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                int num = i + 1;
                msg += ("\t" + num + "." + tasks.get(i).toString() + "\n");
            }
            return msg;
        } else {
            return tasksNotFoundMsg();
        }
    }

    public String tasksNotFoundMsg() {
        String msg = "No tasks in the list\nPlease add a task";
        return msg;
    }


    public String taskAddMsg(Task task, int size) {
        String msg = ("\tGot it. I've added this task:\n" + "\t" + task.toString() +
                "\n\tNow you have " + size + " tasks in the list.");
        return msg;
    }

    public String viewFoundTasks(String keyword, ArrayList<Task> tasks) {
        String msg = ("\tHere are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).taskName.contains(keyword)) {
                int num = i + 1;
                msg += ("\t" + num + "." + tasks.get(i).toString() + "\n");
            }
        }
        return msg;
    }


}
