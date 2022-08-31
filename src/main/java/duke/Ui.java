package duke;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    private Scanner scanner;
    private final String line = "--------------------------------------------------------------------------------\n";

    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prints welcome message.
     */
    public String printWelcomeMessage() {
        String str = "";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        str += "Welcome to Duke bot!\n";
        str += "What tasks do you have to do today?\n";
        str += "To input a deadline or event, type the date and time in the format 'yyyy-mm-ddThh:mm'";
        return str;
    }

    /**
     * Prints goodbye message.
     */
    public String printGoodbyeMessage() {
        return "Bye! See you soon!";
    }

    /**
     * Prints list of tasks.
     */
    //public String printList(ArrayList<Task> tasks) {
    public String printList(TaskList tasks) {
        String str = "Here are the tasks that you have:\n";
        for (int i = 0; i < tasks.getSize(); i++) {
            int num = i + 1;
            str += num + ". " + tasks.getTask(i).toString() + "\n";
        }
        return str;
    }

    /**
     * Prints task as done.
     *
     * @param task Task to be printed as done.
     */
    public String printDone(Task task) {
        String str = "Nice! I've marked this task as done:\n";
        str += "[" + task.getStatusIcon() + "]" + task.description;
        return str;
    }

    /**
     * Prints task as undone.
     *
     * @param task Task to be printed as undone.
     */
    public String printUndone(Task task) {
        String str = "Hmm...I've marked this task as undone:\n";
        str += "[" + task.getStatusIcon() + "] " + task.description;
        return str;
    }

    /**
     * Prints todo task.
     *
     * @param task Todo task to be added.
     */
    public String printTodo(Task task) {
        String str = "Okay! I've added this task:\n";
        str += task.toString();
        return str;
    }

    /**
     * Prints task to be deleted.
     *
     * @param task Task to be deleted.
     */
    public String printDelete(Task task) {
        String str = "Nice! I've deleted this task:\n";
        str += task.toString() + "\n";
        return str;
    }

    /**
     * Prints number of tasks left in list.
     *
     * @param num Number of tasks left in list.
     */
    public String printTasksLeft(int num) {
        String str = "You have " + num + " tasks left in your list.";
        return str;
    }

    /**
     * Prints tasks that matches keyword
     *
     * @param matchedTasks Arraylist of tasks that matches
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
     * Prints error message.
     */
    public String printErrorMsg(String str) {
        //System.out.println(str);
        return str;
    }

    /**
     * Prints horizontal line.
     */
    public void drawLine() {
        System.out.println(line);
    }

}
