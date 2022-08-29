package duke.ui;

import java.io.PrintStream;
import java.util.Scanner;

import duke.tasklist.TaskList;



/**
 * Ui class responsible for handling Ui and error messages
 */
public class Ui {

    private Scanner sc;
    private PrintStream out;

    /**
     * Constructor of Ui
     */
    public Ui() {
        sc = new Scanner(System.in);
        out = System.out;
    }

    /**
     * Shows Greetings Ui
     */
    public void showGreetings() {
        String greetings = "_________________________________________________\nHello! I'm Duke"
                + "\nWhat can I do for you?\n_________________________________________________";
        this.out.println(greetings);
    }

    /**
     * Shows Bye Ui
     */
    public static void showBye() {
        System.out.println("_________________________________________________\nBye. Hope to see you again soon!\n"
                + "_________________________________________________\n");
    }

    /**
     * Shows message when directory is created
     */
    public static void showDirectoryCreation() {
        System.out.println("Creating new /data/ directory folder.");
    }

    /**
     * Shows message when file is created
     */
    public static void showFileCreation() {
        System.out.println("Creating new duke.txt file under /data/ directory folder.");
    }

    /**
     * Shows successful load
     */
    public static void showLoadSuccessful() {
        System.out.println("Loaded successfully!");
    }

    /**
     * Shows successful save
     */
    public static void showSaveSuccessful() {
        System.out.println("Saved successfully!");
    }

    /**
     * Shows the tasks in taskList
     * @param taskList the tasks to be shown
     */
    public static void showTasksInList(TaskList taskList) {
        System.out.println("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.taskListSize(); i++) {
            System.out.println(i + 1 + ". " + taskList.getTask(i).toString());
        }
    }

    /**
     * Reads the command entered by user
     * @return the command entered by user
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Shows the error message
     * @param message the error message
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Shows when task is added
     * @param taskList the taskList that the task is added to
     */
    public void showTaskAdded(TaskList taskList) {
        System.out.println("Got it. I've added this task:");
        System.out.println(taskList.latestTask() + "\nNow you have " + taskList.taskListSize()
                + " tasks in the list.");
    }

    /**
     * Shows when task is deleted
     * @param taskList the taskList that the task is deleted from
     * @param integer the index of the task which is deleted
     */
    public void showTaskDeleted(TaskList taskList, int integer) {
        System.out.println("Noted. I've removed this task:\n"
                + taskList.getTask(integer));
        System.out.println("Now you have " + (taskList.taskListSize() - 1) + " tasks in the list.");
    }

    /**
     * Shows when task is marked as done
     * @param taskList the taskList that the task is marked from
     * @param integer the index of the task which is marked
     */
    public void showMarkAsDone(TaskList taskList, int integer) {
        System.out.println("Nice! I've marked this task as done:\n"
                + taskList.getTask(integer - 1).toString());
    }

    /**
     * Shows when task is marked as not done
     * @param taskList the taskList that the task is marked from
     * @param integer the index of the task which is marked as not done
     */
    public void showMarkAsNotDone(TaskList taskList, int integer) {
        System.out.println("OK, I've marked this task as not done yet:\n"
                + taskList.getTask(integer - 1).toString());
    }

    /**
     * Displays Ui when task is found
     * @param taskList the tasklist to be displayed
     */
    public void showTaskFound(TaskList taskList) {
        System.out.println("Here are the matching tasks in your list:\n");
        for (int i = 0; i < taskList.taskListSize(); i++) {
            System.out.println(i + 1 + ". " + taskList.getTask(i).toString());
        }
    }
}
