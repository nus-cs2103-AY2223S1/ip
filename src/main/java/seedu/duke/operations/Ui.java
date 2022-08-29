package seedu.duke.operations;

import seedu.duke.task.Task;

import java.util.Scanner;

/**
 * Ui is the class that contains the messages that Duke will output
 * to the command line in response to user inputs. It contains a
 * Scanner as well such that it can accept user inputs.
 */
public class Ui {
    private final Scanner cmdReader;

    /**
     * Ui initializes a Scanner to take user inputs.
     */
    public Ui() {
        this.cmdReader = new Scanner(System.in);
    }

    /**
     * Method that allows Ui to read user input.
     *
     * @return  User input from the command line
     */
    public String readCommand() {
        return cmdReader.nextLine();
    }

    /**
     * Prints welcome message when initializing Duke.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm duke.Duke\nWhat can I do for you?");
    }

    /**
     * Prints line breaks.
     */
    public void showLine() {
        System.out.println("_".repeat(100));
    }

    /**
     * Prints exit message when user closes Duke.
     */
    public void showExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Message printed when there are no task in task-list.
     */
    public void showNoTask() {
        System.out.println("It appears you have no tasks right now,\nwould you like to add some?");
    }

    /**
     * Message printed when adding a new task to task-list.
     */
    public void showNewTask() {
        System.out.println("Got it. I've added this task:");
    }

    /**
     * Message printed when marking an unmarked task.
     */
    public void showMarked() {
        System.out.println("Nice! I've marked this task as done:");
    }

    /**
     * Message printed when unmarking a marked task.
     */
    public void showUnmarked() {
        System.out.println("OK, I've marked this task as not done yet:");
    }

    /**
     * Message printed when marking a marked task.
     */
    public void showAlreadyMarked() {
        System.out.println("This task is already marked:");
    }

    /**
     * Message printed when unmarking an unmarked task.
     */
    public void showAlreadyUnmarked() {
        System.out.println("This task is already unmarked:");
    }

    /**
     * Message printed when removing a task.
     *
     * @param task  Task to be removed
     */
    public void showRemoveTask(Task task) {
        System.out.println("Noted. I've removed this task:" + task.toString());
    }

    /**
     * Prints the error message into the command line.
     *
     * @param errorMessage  Error message returned by Duke
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Returns an error message for general invalid inputs in the form of a String.
     *
     * @return  Invalid input error message
     */
    public String getInvalidInputMessage() {
        return "The input is invalid, please try again.";
    }

    /**
     * Returns an error message for task-list operation inputs without index in
     * the form of a String.
     *
     * @return Invalid input error message
     */
    public String getNoIndexProvidedErrorMessage() {
        return "Please provide the index of he relevant task after the\ncommand.";
    }

    /**
     * Returns an error message for task-list operation inputs with index out of
     * range of the task-list in the form of a String.
     *
     * @return Invalid input error message
     */
    public String getTaskListIndexErrorMessage() {
        return "It appears there is no such task,\nPlease try again";
    }

    /**
     * Returns an error message for task making operation inputs without description
     * in the form of a String.
     *
     * @return Invalid input error message
     */
    public String getNoDescriptionErrorMessage() {
        return "The description of the task cannot be empty.";
    }

    /**
     * Returns an error message for task making operation inputs without time
     * provided (In the case of time sensitive tasks) in the form of a String.
     *
     * @return Invalid input error message
     */
    public String getNoTimeErrorMessage() {
        return "Please provide the relevant time for this type of task,\n"
                + "by typing \"/\" followed by the time.";
    }

    /**
     * Returns an error message for task making operation inputs with invalid
     * datetime format in the form of a String.
     *
     * @return Invalid input error message
     */
    public String getInvalidTimeFormatErrorMessage() {
        return "Invalid date provided.\nPlease format the date in YYYY-MM-DD";
    }

    /**
     * Prints a loading error message to command line.
     */
    public void showLoadingError() {
        System.out.println("There appears to be an issue retrieving your previous records");
    }
}
