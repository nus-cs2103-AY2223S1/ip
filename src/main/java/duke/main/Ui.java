package duke.main;

import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;

/**
 * A class that handles the User Interface.
 */
public class Ui {
    protected String reply;
    private Scanner sc = new Scanner(System.in);

    /**
     * Greets the user.
     *
     * @return String of greetings.
     */
    public String showWelcome() {
        return "Dude: \n" + "Hey Dude here\n" + "What can I do for you?";
    }

    /**
     * Shows loading error.
     *
     * @return String of loading error messages.
     */
    public String showLoadingError() {
        return "LOADING ERROR ... I died..XOX";
    }

    /**
     * Shows error message.
     *
     * @param message The error message.
     * @return String of error message.
     */
    public String showError(String message) {
        return "ERROR... I just died XP\n" + message;
    }

    /**
     * Says farewell to the user.
     */
    public void sayBye() {
        reply = "Bye^2!! :p";
    }

    /**
     * Lists the tasks.
     *
     * @param arr The array list of tasks.
     */
    public void sayList(ArrayList<Task> arr) {
        if (arr.size() == 0) {
            reply = "You have nothing due soon! :)";
            return;
        }
        int i = 1;
        String s = "Here are the tasks in your list: \n";
        for (Task task: arr) {
            s += (i + "." + arr.get(i - 1).toString() + "\n");
            i++;
        }
        reply = s;
    }

    /**
     * Alerts the user that task is marked.
     *
     * @param num the position of the task to be marked.
     * @param arr the array list of tasks.
     */
    public void sayMarked(int num, ArrayList<Task> arr) {
        reply = "Nice! I've marked this task as done:\n"
                + arr.get(num - 1).toString();
    }

    /**
     * Alerts the user that task is marked.
     *
     * @param num the position of the task to be marked.
     * @param t the task to be unmarked.
     */
    public void sayUnmarked(int num, Task t) {
        reply = "OK, I've marked this task as not done yet:\n"
                + t.toString();
    }

    /**
     * Alerts the user that the task is added.
     *
     * @param arr the array list of tasks.
     */
    public void sayAdded(ArrayList<Task> arr) {
        reply = "Got it. I've added this task:\n"
                + arr.get(arr.size() - 1).toString()
                + "\nNow you have " + arr.size() + " tasks in the list.";
    }

    /**
     * Informs the user that the specified task has been deleted.
     *
     * @param deletedTask the task to be deleted.
     * @param size the current size of the array.
     */
    public void sayDeleted(Task deletedTask, int size) {
        reply = "Noted. I've removed this task:\n" + deletedTask.toString()
                + "\nNow you have " + size + " tasks in the list.";
    }

    /**
     * Lists the tasks that matches the date that user specifies.
     *
     * @param arr the array list of tasks.
     */
    public void sayMatching(ArrayList<Task> arr) {
        if (arr.size() == 0) {
            reply = "There are no matches unfortunately :( \n";
            return;
        }
        String s = "Here are the matching tasks in your list:";
        int i = 1;
        for (Task task: arr) {
            s += (i + "." + arr.get(i - 1).toString());
            i++;
        }
        reply = s;
    }

    /**
     * Allows user to know he successfully undo.
     */
    public void sayUndo() {
        reply = "Undo successful!";
    }
}
