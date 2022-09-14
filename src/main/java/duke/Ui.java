package duke;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Returns welcome message.
     *
     * @return String of welcome message.
     */
    public String printWelcomeMessage() {
        String str = "";
        str += "Welcome to Margi bot!\n";
        str += "What tasks do you have to do today?\n";
        return str;
    }

    /**
     * Returns instructions.
     *
     * @return String of instruction message.
     */
    public String printInstructions() {
        String str = "";
        str += "To input a deadline or event, type the date and time in the format: 'yyyy-mm-ddThh:mm'\n";
        return str;
    }

    /**
     * Returns goodbye message.
     *
     * @return String of goodbye message.
     */
    public String printGoodbyeMessage() {
        return "Bye! See you soon!";
    }

    /**
     * Returns list of tasks.
     *
     * @param tasks Tasklist containing tasks.
     * @return String of list.
     */
    public String printList(TaskList tasks) {
        String str = "Here are the tasks that you have:\n";
        for (int i = 0; i < tasks.getSize(); i++) {
            int num = i + 1;
            str += num + ". " + tasks.getTask(i).toString() + "\n";
        }
        return str;
    }

    /**
     * Returns task that is marked as done.
     *
     * @param task Task to be printed as done.
     * @return String of marked task.
     */
    public String printDone(Task task) {
        String str = "Nice! I've marked this task as done:\n";
        str += "[" + task.getStatusIcon() + "] " + task.description;
        return str;
    }

    /**
     * Returns task that is marked as undone.
     *
     * @param task Task to be printed as undone.
     * @return String of unmarked task.
     */
    public String printUndone(Task task) {
        String str = "Hmm...I've marked this task as undone:\n";
        str += "[" + task.getStatusIcon() + "] " + task.description;
        return str;
    }

    /**
     * Returns todo task.
     *
     * @param task Todo task to be added.
     * @return String of todo task added.
     */
    public String printTodo(Task task) {
        String str = "Okay! I've added this task:\n";
        str += task.toString();
        return str;
    }

    /**
     * Returns task to be deleted.
     *
     * @param task Task to be deleted.
     * @return String of task deleted.
     */
    public String printDelete(Task task) {
        String str = "Nice! I've deleted this task:\n";
        str += task.toString() + "\n";
        return str;
    }

    /**
     * Returns number of tasks left in list.
     *
     * @param num Number of tasks left in list.
     * @return String of number of tasks left in the list.
     */
    public String printTasksLeft(int num) {
        if (num > 1) {
            String str = "You have " + num + " tasks in your list.";
            return str;
        } else {
            String str = "You have " + num + " task in your list.";
            return str;
        }
    }

    /**
     * Returns tasks that matches keyword.
     *
     * @param matchedTasks Arraylist of tasks that matches.
     * @return String of tasks matching keyword.
     */
    public String printFind(ArrayList<Task> matchedTasks) {
        String str = "Here are the matching tasks in your list:\n";
        for (int j = 0; j < matchedTasks.size(); j++) {
            int num = j + 1;
            str += num + ". " + matchedTasks.get(j).toString() + "\n";
        }
        return str;
    }

    /**
     * Returns number of todos in the list.
     *
     * @param taskType Type of tasks to be counted.
     * @param taskNum Number of todos in list
     * @return String of number of todos in list.
     */
    public String printTaskCount(String taskType, int taskNum) {
        if (taskType.equals("todo")) {
            String str = "You have " + taskNum + " todos. XD";
            return str;
        } else if (taskType.equals("deadline")) {
            String str = "You have " + taskNum + " deadlines. :)";
            return str;
        } else {
            String str = "You have " + taskNum + " events. ;)";
            return str;
        }
    }

    /**
     * Prints error message.
     */
    public String printErrorMsg(String str) {
        return str;
    }

}
