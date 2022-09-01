package seedu.duke.ui;

import seedu.duke.task.Task;
import seedu.duke.task.TaskList;

import java.util.Scanner;

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
    public String showError(String errorMessage) {
        String text = Style.INDENTATION + errorMessage + "\n";
        System.out.println(text);
        return text;
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
    public String showGoodbye() {
        String text = Style.INDENTATION + "Bye. Hope to see you again soon!\n";
        System.out.println(text);

        return text;
    }

    /**
     * Displays all the tasks in the list.
     * @param tasks The tasklist
     */
    public String showList(TaskList tasks) {
        String text = Style.INDENTATION + "Here are the tasks in your list:\n"
                + tasks.listTasks();

        System.out.println(text);

        return text;
    }

    /**
     * Displays task after it has been marked.
     * @param task The task to be marked.
     */
    public String showMarkStatus(Task task) {
        String text = Style.INDENTATION + "Nice! I've marked this task as done:\n"
                + Style.INDENTATION + Style.HALF_INDENTATION + task + "\n";

        System.out.println(text);
        return text;
    }


    /**
     * Displays task after it has been unmarked.
     * @param task The task to be unmarked.
     */
    public String showUnmarkStatus(Task task) {
        String text = Style.INDENTATION + "OK, I've marked this task as not done yet:\n"
                + Style.INDENTATION + Style.HALF_INDENTATION + task + "\n";
        System.out.println(text);
        return text;
    }

    /**
     * Displays teh task that has been added to ArrayList.
     * @param task The task to be added.
     */
    public String addTask(Task task) {
        String text = Style.INDENTATION + "Got it. I've added this task:\n"
                + Style.INDENTATION + Style.HALF_INDENTATION + task + "\n";
        System.out.println(text);
        return text;
    }

    /**
     * Displays the task after it has been deleted.
     * @param task The task to be deleted.
     */
    public String deleteTask(Task task) {
        String text = Style.INDENTATION + "Noted. I've removed this task:"
                + Style.INDENTATION + Style.HALF_INDENTATION + task + "\n";
        System.out.println(text);
        return text + "\n";
    }

    /**
     * Displays the number of tasks in the list in an appropriate format.
     * @param numberOfTasks The number of tasks in the list.
     */
    public String displayNumberOfTasks(int numberOfTasks) {
        String taskOrTasks = numberOfTasks == 1 ? "task" : "tasks";
        String text = Style.INDENTATION + "Now you have " + numberOfTasks + " "
                + taskOrTasks + " in the list.\n";
        System.out.println(text);
        return text;
    }

    /**
     * Displays the header comment before the matching tasks.
     */
    public String displayMatchingTasks() {
        String text = Style.INDENTATION + "Here are the matching tasks in your list:\n";
        System.out.println(text);

        return text;
    }
}
