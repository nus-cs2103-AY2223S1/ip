package tuna;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import tuna.task.Task;

/**
 * Represents a Ui to handle user interactions. A Ui object contains a scanner to read in user inputs.
 */
public class Ui {

    /** Scanner to read in user inputs */
    private final Scanner sc = new Scanner(System.in);

    /**
     * Returns welcome message.
     *
     * @return welcome message.
     */
    public String welcomeMessage() {
        return "Hello! I'm Tuna\nWhat can I do for you?";
    }

    /**
     * Returns bye message.
     *
     * @return bye message.
     */
    public String byeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Throws exception when /by is not included in a deadline command.
     *
     * @return Return value omitted as Exception is thrown.
     * @throws TunaException Exception thrown to indicate that /by was not included in the command.
     */
    public String deadLineErrorMessage() throws TunaException {
        throw new TunaException("Oops! Remember to include /by and the deadline after your task description");
    }

    /**
     * Throws exception when /at is not included in an event command.
     *
     * @return Return value is omitted as Exception is thrown.
     * @throws TunaException Exception thrown to indicate that /at was not included in the command.
     */
    public String eventErrorMessage() throws TunaException {
        throw new TunaException("Oops! Remember to include /at and the event time after your task description");
    }

    /**
     * Returns entry message and lists tasks.
     *
     * @param tasks list of tasks to be returned.
     * @return all tasks in the list.
     */
    public String listTasksMessage(ArrayList<Task> tasks) {
        StringBuilder message = new StringBuilder("Here are the tasks in your list:\n");
        int index = 1;
        for (Task task : tasks) {
            message.append(String.format("%d. %s\n", index, task.toString()));
            index += 1;
        }
        return message.toString();

    }

    /**
     * Returns entry message and lists tasks due on the specified date.
     *
     * @param date date of tasks to be listed.
     * @param tasks lists of tasks to be returned.
     * @return tasks that are due on the specified date.
     */
    public String listTasksDueOnDateMessage(LocalDate date, ArrayList<Task> tasks) {
        StringBuilder message = new StringBuilder(String.format("Here are the tasks due on %s\n", date));
        for (Task task : tasks) {
            message.append(String.format("%s\n", task.toString()));
        }
        return message.toString();
    }

    /**
     * Returns confirmation message after marking a task as done.
     *
     * @param task task that was marked as done.
     * @return confirmation message after marking a task as done.
     */
    public String markTaskMessage(Task task) {
        return String.format("Nice! I've marked this task as done:\n%s\n", task);
    }

    /**
     * Returns confirmation message after un-marking a task as done.
     *
     * @param task task that was un-marked as done.
     * @return confirmation message after un-marking a task as done.
     */
    public String unMarkTaskMessage(Task task) {
        return String.format("OK, I've marked this task as not done yet:\n%s\n", task);
    }

    /**
     * Returns confirmation message after deleting a task.
     *
     * @param deletedTask task that was deleted.
     * @param totalTasks total number of tasks left.
     * @return confirmation message after deleting a task.
     */
    public String deletedTaskMessage(Task deletedTask, int totalTasks) {
        return String.format("Noted. I've removed this task:\n%s\nNow you have %d tasks in the list\n",
                deletedTask, totalTasks);
    }

    /**
     * Returns confirmation message after adding a task.
     *
     * @param task task that was added.
     * @param totalTasks total number of tasks currently in the list.
     * @return confirmation message after adding a task.
     */
    public String taskAddedMessage(Task task, int totalTasks) {
        return String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list\n",
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
     * Returns error message of exception.
     *
     * @param message error message of the exception that occurred.
     * @return error message of exception.
     */
    public String showError(String message) {
        return message;
    }

    /**
     * Returns entry message and lists tasks that contain the specified keyword.
     *
     * @param tasksFound tasks that contain the specified keyword.
     * @return list of tasks containing the specified keyword.
     */
    public String findMessage(ArrayList<Task> tasksFound) {
        StringBuilder message = new StringBuilder("Here are the matching tasks in your list:\n");
        for (Task task : tasksFound) {
            message.append(String.format("%s\n", task.toString()));
        }
        return message.toString();
    }

    /**
     * Returns confirmation message after tasks have been saved to the data file.
     *
     * @return confirmation message to indicate tasks have been saved.
     */
    public String fileSavedMessage() {
        return "Your data has been saved\n";
    }

    /**
     * Returns confirmation message after tasks have been loaded from the data file.
     *
     * @return confirmation message to indicate tasks have been loaded.
     */
    public String fileLoadedMessage() {
        return "Your data has been loaded\n";
    }
}
