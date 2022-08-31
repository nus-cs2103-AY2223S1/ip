package seedu.duke.ui;

import java.util.Scanner;

import seedu.duke.task.Task;
import seedu.duke.task.TaskList;

/**
 * Ui Class responsible for handling all Ui displaying related matters.
 */
public class Ui {
    /* Boolean representing whether there is a loadingError */
    protected boolean loadingError;

    /**
     * Creates a Ui object.
     */
    public Ui() {
        this.loadingError = false;
    }

    /**
     * Prints out the welcome message.
     */
    public void showWelcome() {
        String logo = " ____  ____   __    ___   ___  _  _\n"
                + "(  __)(  _ \\ /  \\  / __) / __)( \\/ )\n"
                + " ) _)  )   /(  O )( (_ \\( (_ \\ )  /\n"
                + "(__)  (__\\_) \\__/  \\___/ \\___/(__/\n";




        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?\n");
    }

    /**
     * Shows a loading error screen if there is an error.
     */
    public void showLoadingError() {
        System.out.println("----- Loading Error!! -----\n");
    }

    /**
     * Prints out the error message if there is an error.
     *
     * @param errorMessage The error message.
     */
    public void showError(String errorMessage) {
        System.out.println(Style.INDENTATION + errorMessage + "\n");
    }

    /**
     * Returns the command after reading it through Scanner.
     *
     * @return The command in a string representation.
     */
    public String readCommand() {
        Scanner myObj = new Scanner(System.in);
        String command = myObj.nextLine();

        return command;
    }

    /**
     * Prints out a line as part of the UI.
     */
    public void showLine() {
        System.out.println("__________________________________________"
                + "__________________________________________" + "\n");
    }

    /**
     * Prints out good bye message.
     */
    public void showGoodbye() {
        System.out.println(Style.INDENTATION + "Bye. Hope to see you again soon!\n");
    }

    /**
     * Displays all the tasks in the list.
     * @param tasks
     */
    public void showList(TaskList tasks) {
        System.out.println(Style.INDENTATION + "Here are the tasks in your list:");
        tasks.listTasks();
        System.out.println("");
    }

    /**
     * Displays task after it has been marked.
     * @param task The task to be marked.
     */
    public void showMarkStatus(Task task) {
        System.out.println(Style.INDENTATION + "Nice! I've marked this task as done:");
        System.out.println(Style.INDENTATION + Style.HALF_INDENTATION + task + "\n");
    }


    /**
     * Displays task after it has been unmarked.
     * @param task The task to be unmarked.
     */
    public void showUnmarkStatus(Task task) {
        System.out.println(Style.INDENTATION + "OK, I've marked this task as not done yet:");
        System.out.println(Style.INDENTATION + Style.HALF_INDENTATION + task + "\n");
    }

    /**
     * Displays teh task that has been added to ArrayList.
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        System.out.println(Style.INDENTATION + "Got it. I've added this task:");
        System.out.println(Style.INDENTATION + Style.HALF_INDENTATION + task);
    }

    /**
     * Displays the task after it has been deleted.
     * @param task The task to be deleted.
     */
    public void deleteTask(Task task) {
        System.out.println(Style.INDENTATION + "Noted. I've removed this task:");
        System.out.println(Style.INDENTATION + Style.HALF_INDENTATION + task);
    }

    /**
     * Displays the number of tasks in the list in an appropriate format.
     * @param numberOfTasks The number of tasks in the list.
     */
    public void displayNumberOfTasks(int numberOfTasks) {
        String taskOrTasks = numberOfTasks == 1 ? "task" : "tasks";
        System.out.println(Style.INDENTATION + "Now you have " + numberOfTasks + " "
                + taskOrTasks + " in the list.\n");
    }

    /**
     * Displays the header comment before the matching tasks.
     */
    public void displayMatchingTasks() {
        System.out.println(Style.INDENTATION + "Here are the matching tasks in your list:");
    }
}
