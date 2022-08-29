package duke.tools;

import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.tasks.Task;

/**
 * This class takes care of the interaction with the user.
 * This class manages the input and output from the Duke system.
 */
public class Ui {

    private static Scanner sc = new Scanner(System.in);

    /** This variable controls the continuation of the Duke program. */
    private boolean isContinue = true;

    /**
     * Reads the next line of command from the user.
     *
     * @return A String storing the user's input.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Checks if the Duke program should continue to run.
     *
     * @return A boolean to whether Duke should continue running.
     */
    public boolean canContinue() {
        return this.isContinue;
    }

    /** Prints the greeting message from Duke. */
    public void sayGreet() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    /** Prints the goodbye message from Duke. */
    public void sayBye() {
        System.out.println("Bye! Hope to see you again soon");
    }

    /** Prints the message informing user of the tasks stored by Duke. */
    public void sayList() {
        System.out.println("Here are the tasks in your list:");
    }

    /**
     * Prints a task with its index.
     *
     * @param index The index of the task in the TaskList.
     * @param task The task to be printed.
     */
    public void sayTaskWithIndex(int index, Task task) {
        System.out.printf("%d. %s\n", index + 1, task);
    }

    /** Prints a message informing user that tasks have been printed. */
    public void sayFinishListing() {
        System.out.println("That's all!");
    }

    /**
     * Prints a message informing the user that the specified task has been marked.
     *
     * @param index The index of the task in the TaskList.
     * @param task The task to be printed.
     */
    public void sayMarkTask(int index, Task task) {
        System.out.println("Marked following task as done:");
        System.out.printf("%d. %s\n", index + 1, task);
    }

    /**
     * Prints a message informing the user that the specified task has been unmarked.
     *
     * @param index The index of the task in the TaskList.
     * @param task The task to be printed.
     */
    public void sayUnmarkTask(int index, Task task) {
        System.out.println("Marked following task as not done:");
        System.out.printf("%d. %s\n", index + 1, task);
    }

    /**
     * Prints a message informing the user that the specified task has been deleted.
     *
     * @param index The index of the task in the TaskList.
     * @param task The task to be printed.
     */
    public void sayDeleteTask(int index, Task task) {
        System.out.println("The following task is deleted:");
        System.out.printf("%d. %s\n", index + 1, task);
    }

    /**
     * Prints a message informing the user that the specified task has been stored.
     *
     * @param task The task that is stored.
     */
    public void sayAddTask(Task task) {
        System.out.println("Got it! I stored this task:\n" + task);
    }

    /**
     * Prints a message informing the user how many tasks has been stored.
     *
     * @param taskList The list of tasks stored in Duke.
     */
    public void sayTaskListSize(TaskList taskList) {
        System.out.printf("Now you have %d tasks in the list.\n", taskList.getSize());
    }

    /** Prints a message informing the user of the search results from find command. */
    public void sayFind() {
        System.out.println("Here are the matching tasks in your list:");
    }

    /**
     * Prints a message informing the user of the exception that has occurred.
     *
     * @param e The DukeException containing the error message.
     */
    public void sayExceptionMessage(DukeException e) {
        System.out.println(e);
    }

    /** Exits the Duke program. */
    public void exit() {
        this.isContinue = false;
    }
}
