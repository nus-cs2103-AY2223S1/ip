package duke.ui;

import duke.model.Task;
import duke.model.TaskList;

import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class Ui {


    private Scanner sc;

    /**
     * A constructor for a Ui.
     *
     * @param sc a scanner object to handle user inputs
     */
    public Ui(Scanner sc) {
        this.sc = sc;
    }

    /**
     * Greets the user by printing some lines upon start-up.
     */
    public void greetUser() {
        this.showDivider();
        System.out.println("\tHey there! I'm Duke!");
        System.out.println("\tHow may I help you? :)");
    }

    /**
     * Bids farewell to the user by printing some lines before exit.
     */
    public static void sayBye() {
        System.out.println("\tNice seeing you! Bye!");
    }

    /**
     * Reads the command given by the user.
     *
     * @return a string representing the user's input
     */
    public String readCommand() {
        return this.sc.nextLine();
    }

    /**
     * Closes the scanner object.
     */
    public void close() {
        this.sc.close();
    }

    /**
     * Displays the relevant information when a Task is added into the TaskList.
     *
     * @param task a Task that is added to the TaskList
     */
    public static void add(Task task) {
        System.out.println("\tGot it! I've added this task!");
        System.out.println("\t\t" + task);
        System.out.println("\tYou now have " + Task.getNumOfTasks() + " tasks in the list!");
    }

    /**
     * Displays a list of tasks from a TaskList.
     *
     * @param taskList a TaskList to be displayed
     */
    public static void list(TaskList taskList) {
        System.out.println(taskList);
    }

    /**
     * Displays the relevant information when a Task is deleted from a TaskList.
     *
     * @param task a Task that is deleted from a TaskList
     */
    public static void delete(Task task) {
        System.out.println("\tAlright! The following task has been deleted!");
        System.out.println("\t\t" + task);
    }

    /**
     * Displays the relevant information when a Task is mark as done.
     *
     * @param task a Task to be marked as done
     */
    public void mark(Task task) {
        System.out.println("\tAlright! Marked this task as done!");
        System.out.println("\t\t" + task);

    }

    /**
     * Displays the relevant information when a Task is mark as not done.
     *
     * @param task a Task to be marked as not done
     */
    public void unmark(Task task) {
        System.out.println("\tOkay! Unmarked this task!");
        System.out.println("\t\t" + task);
    }

    /**
     * Displays a line divider for Ui cleanliness.
     */
    public void showDivider() {
        System.out.println("\t-----------------------------------------------------");
    }
}
