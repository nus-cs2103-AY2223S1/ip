package duke.utilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskList;

/**
 * The User Interface (UI) class.
 */
public class Ui {
    /** Line used for formatting. */
    private static String line = "----------------------------------------";

    /** Scanner object to read from the system input. */
    private Scanner sc;

    /**
     * Constructor for Ui objects.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints the Duke Opening that includes the greeting and logo.
     */
    public void printDukeOpening() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "");
        System.out.println(line);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println(line);
    }

    /**
     * Prints the Duke Closing that includes a leaving salutation.
     */
    public void printDukeClosing() {
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }

    /**
     * Prints the message from the DukeException thrown, nicely formatted.
     *
     * @param e The DukeException whose message we want to format and print.
     */
    public void printDukeException(DukeException e) {
        System.out.println(line);
        System.out.println("OOPS!!! " + e.getMessage());
        System.out.println(line);
    }

    /**
     * Prints the message from the IOException thrown, nicely formatted.
     *
     * @param e The IOException whose message we want to format and print.
     */
    public void printIoException(IOException e) {
        System.out.println(line);
        System.out.println("OOPS!!! " + e.getMessage());
        System.out.println(line);
    }

    /**
     * Prints the message if we ever get a DateTimeParseException, signalling
     * to a user that they entered the date in the wrong format.
     */
    public void printDateTimeParseException() {
        System.out.println(line);
        System.out.println("All dates must be in the format (yyyy-MM-dd HH:mm)!");
        System.out.println(line);
    }

    /**
     * Prints all the tasks in a given task List object.
     *
     * @param taskList The TaskList whose tasks we want to print.
     */
    public void printTasks(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        System.out.println(line);
        System.out.println("Here are the tasks in your list:");

        for (int i = 0; i < tasks.size(); i++) {
            Task current = tasks.get(i);
            System.out.println((i + 1) + "." + current.toString());
        }
        System.out.println(line);
    }

    /**
     * Method to print the matching tasks after filtering for tasks that contains
     * the query term.
     *
     * @param tasks The input task list to print to the user.
     */
    public void printMatchingTasks(ArrayList<Task> tasks) {
        System.out.println(line);
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task current = tasks.get(i);
            System.out.println((i + 1) + "." + current.toString());
        }
        System.out.println(line);
    }

    /**
     * Prints the nicely formatted message when a user adds a task.
     *
     * @param task The task that was just added.
     * @param taskList The current task list after the task has been added.
     */
    public void printAddTask(Task task, TaskList taskList) {
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + taskList.getNumberOfTasks() + " tasks in the list.");
        System.out.println(line);
    }

    /**
     * Prints the nicely formatted message when a user deletes a task.
     *
     * @param task The task that was just deleted.
     * @param taskList The current task list after the task has been deleted.
     */
    public void printDeleteTask(Task task, TaskList taskList) {
        System.out.println(line);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + taskList.getNumberOfTasks() + " tasks in the list.");
        System.out.println(line);
    }

    /**
     * Prints the nicely formatted message when a user marks a task as done or not.
     *
     * @param task The task that was just marked as done or undone.
     * @param isDone The status indicating whether a task was marked as done or not.
     */
    public void printChangeTaskStatus(Task task, boolean isDone) {
        System.out.println(line);
        if (isDone) {
            System.out.println(("Nice! I've marked this task as done:"));
            System.out.println("  " + task.toString());
        } else {
            System.out.println(("OK, I've marked this task as not done yet:"));
            System.out.println("  " + task.toString());
        }
        System.out.println(line);
    }

    /**
     * Method to close the scanner object.
     */
    public void closeScanner() {
        this.sc.close();
    }

    /**
     * Method to read the next line from the standard input.
     *
     * @return Returns the next line as a String.
     */
    public String nextLine() {
        return this.sc.nextLine();
    }

    /**
     * Gets the string of the duke opening.
     *
     * @return The string of the duke opening.
     */
    public String getStringDukeOpening() {
        String dukeOpening = "Hello! I'm Duke! What can I do for you?";
        return dukeOpening;
    }

    /**
     * Gets the string of the duke closing.
     *
     * @return The string of the duke closing.
     */
    public String getStringDukeClosing() {
        String dukeClosing = "Bye. Hope to see you again soon!";
        return dukeClosing;
    }

    /**
     * Gets the string of the input duke exception.
     *
     * @param e The DukeException.
     * @return The message from the duke exception.
     */
    public String getStringDukeException(DukeException e) {
        return "OOPS! " + e.getMessage();
    }

    /**
     * Gets the string of the input io exception.
     *
     * @param e The IOException.
     * @return The message from the io exception.
     */
    public String getStringIoException(IOException e) {
        return "OOPS! " + e.getMessage();
    }

    /**
     * Gets the string when there is a date time parse exception.
     *
     * @return Returns the string for the exception.
     */
    public String getStringDateTimeParseException() {
        return "All dates must be in the format (yyyy-MM-dd HH:mm)!";
    }

    /**
     * Gets the string representation of the task list.
     *
     * @param taskList The task list that we want to convert into a string.
     * @return Returns the string representation of the current task list.
     */
    public String getStringTasks(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        StringBuilder response = new StringBuilder("Here are the tasks in your list:\n");

        for (int i = 0; i < tasks.size(); i++) {
            Task current = tasks.get(i);
            response.append((i + 1) + "." + current.toString() + "\n");
        }

        return response.toString();
    }

    /**
     * Gets the string representation of the filtered task list.
     *
     * @param tasks The filter task list.
     * @return The string representation of the filtered task list.
     */
    public String getStringMatchingTasks(ArrayList<Task> tasks) {
        StringBuilder response = new StringBuilder("Here are the matching tasks in your list:\n");

        for (int i = 0; i < tasks.size(); i++) {
            Task current = tasks.get(i);
            response.append((i + 1) + "." + current.toString() + "\n");
        }

        return response.toString();
    }

    /**
     * Gets the string representation of when we add a task.
     *
     * @param task The task that was added.
     * @param taskList The task list to which task was added.
     * @return The string representation and what to display.
     */
    public String getStringAddTask(Task task, TaskList taskList) {
        StringBuilder response = new StringBuilder("Got it. I've added this task:\n");
        response.append("  " + task.toString() + "\n");
        response.append("Now you have " + taskList.getNumberOfTasks() + " tasks in the list.\n");
        return response.toString();
    }

    /**
     * Gets the string representation of when we delete a task.
     *
     * @param task The task that was deleted.
     * @param taskList The task list from which the task was deleted.
     * @return The string representation and what to display.
     */
    public String getStringDeleteTask(Task task, TaskList taskList) {
        StringBuilder response = new StringBuilder("Noted. I've removed this task:\n");
        response.append("  " + task.toString() + "\n");
        response.append("Now you have " + taskList.getNumberOfTasks() + " tasks in the list.\n");
        return response.toString();
    }

    /**
     * Gets the string representation of what to display when there is a change in the
     * mark status of a task.
     *
     * @param task The task to mark.
     * @param isDone The boolean whether the task isDone or not.
     * @return The string representation and what to display.
     */
    public String getStringChangeTaskStatus(Task task, boolean isDone) {
        StringBuilder response = new StringBuilder();
        if (isDone) {
            response.append("Nice! I've marked this task as done:\n");
            response.append("  " + task.toString() + "\n");
        } else {
            response.append("OK, I've marked this task as not done yet:\n");
            response.append("  " + task.toString() + "\n");
        }
        return response.toString();
    }

}
