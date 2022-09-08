package duke;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Deals with interactions with the user
 *
 * @author eugeneleong
 * @version 1.0
 */

public class Ui {

    private static final String line = "-----------------------------";
    private Scanner sc = new Scanner(System.in);

    public Ui() {
    }

    /**
     * Our lovely Duke gives a sweet intro
     */
    public void sayHi() {
        System.out.println(line + "\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you\n"
                + line + "\n");
    }

    /**
     * Our lovely Duke gives its parting words
     */
    public void sayBye() {
        System.out.println(line + "\n"
                + "Bye. Hope to see you again soon!\n"
                + line + "\n");
    }

    public String showLine() {
        return line;
    }

    /**
     * Our lovely Duke takes in the commands of the user
     * @return the command, as it is
     */
    public String readCommand() {
        return sc.nextLine();
    }

    public String printTasks(TaskList tasks) {
        return "Here are the tasks in your list:\n" + tasks;
    }

    public String printMarked(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    public String printUnmarked(Task task) {
        return "OK, I've marked this task as not done yet:\n" + task;
    }

    public String printAddedTag(Task task) {
        return "Nice! I've tagged the task as per your request:\n" + task;
    }

    /**
     * Informs user of the added task description and size of current TaskList
     * @param task description of added task
     * @param size size of current TaskList
     * @return task description and size of current TaskList
     */
    public String printAddedTask(Task task, int size) {
        return "Got it. I've added this task:\n"
                + task + "\n"
                + "Now you have " + size + " tasks in the list.\n";
    }

    /**
     * Informs user of the removed task description and size of current TaskList
     * @param task description of removed task
     * @param size size of current TaskList
     * @return task description and size of current TaskList
     */
    public String printDeletedTask(Task task, int size) {
        return "Noted. I've removed this task:\n"
                + task + "\n"
                + "Now you have " + size + " tasks in the list.\n";
    }

    /**
     * Just in case someone keys in the wrong filename...
     * @exception FileNotFoundException if the file cannot be found
     */
    public void showLoadingError() throws FileNotFoundException {
        throw new FileNotFoundException("File cannot be found!");
    }

}
