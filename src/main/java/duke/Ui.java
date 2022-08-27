package duke;

import java.time.LocalDate;
import java.util.Scanner;

import duke.task.Task;

/**
 * Represents a Ui to handle user interactions. A Ui object contains a scanner to read in user inputs.
 */
public class Ui {

    /** Scanner to read in user inputs */
    private final Scanner sc = new Scanner(System.in);

    /**
     * Prints welcome message.
     */
    public void printWelcomeMessage() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * Prints bye message.
     */
    public void printByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Throws exception when /by is not included in a deadline command.
     *
     * @throws DukeException Exception thrown to indicate that /by was not included in the command.
     */
    public void printDeadLineErrorMessage() throws DukeException {
        throw new DukeException("Oops! Remember to include /by and the deadline after your task description");
    }

    /**
     * Throws exception when /at is not included in an event command.
     *
     * @throws DukeException Exception thrown to indicate that /at was not included in the command.
     */
    public void printEventErrorMessage() throws DukeException {
        throw new DukeException("Oops! Remember to include /at and the event time after your task description");
    }

    /**
     * Prints entry message before listing tasks.
     */
    public void printListTasksMessage() {
        System.out.println("Here are the tasks in your list:");
    }

    /**
     * Prints entry message before listing tasks due on the specified date.
     *
     * @param date date of tasks to be listed.
     */
    public void printListTasksDueOnDateMessage(LocalDate date) {
        System.out.printf("Here are the tasks due on %s\n", date);
    }

    /**
     * Prints message indicating that no tasks are due on a specified day.
     */
    public void printNoTasksMessage() {
        System.out.println("Looks like you have no tasks occurring on that day");
    }

    /**
     * Prints confirmation message after marking a task as done.
     *
     * @param task task that was marked as done.
     */
    public void printMarkTaskMessage(Task task) {
        System.out.printf("Nice! I've marked this task as done:\n%s\n", task);
    }

    /**
     * Prints confirmation message after un-marking a task as done.
     *
     * @param task task that was un-marked as done.
     */
    public void printUnMarkTaskMessage(Task task) {
        System.out.printf("OK, I've marked this task as not done yet:\n%s\n", task);
    }

    /**
     * Prints confirmation message after deleting a task.
     *
     * @param deletedTask task that was deleted.
     * @param totalTasks total number of tasks left.
     */
    public void printDeleteTaskMessage(Task deletedTask, int totalTasks) {
        System.out.printf("Noted. I've removed this task:\n%s\nNow you have %d tasks in the list\n",
                deletedTask, totalTasks);
    }

    /**
     * Prints confirmation message after adding a task.
     *
     * @param task task that was added.
     * @param totalTasks total number of tasks currently in the list.
     */
    public void printTaskAddedMessage(Task task, int totalTasks) {
        System.out.printf("Got it. I've added this task:\n%s\nNow you have %d tasks in the list\n",
                task, totalTasks);
    }

    /**
     * Reads in the next command by the user.
     *
     * @return user command.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Checks if the user has entered another command.
     *
     * @return boolean indicating if the user has entered another command.
     */
    public boolean hasNext() {
        return sc.hasNext();
    }

    /**
     * Prints an error message when an error occurs.
     *
     * @param message error message of the exception that occurred.
     */
    public void showError(String message) {
        System.out.printf("Seems like something went wrong\n%s", message);
    }

    /**
     * Prints entry message for finding tasks.
     */
    public void printFindMessage() {
        System.out.println("Here are the matching tasks in your list:");
    }
}
