package duke.ui;

import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskList;

/**
 * A class that deals with user interaction with the program.
 */
public class Ui {

    private final Scanner sc;

    /**
     * Constructor for Ui.
     * Initialises the Scanner.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints out a line to user.
     */
    public void printLine() {
        System.out.println("\t____________________________________________________________");
    }

    /**
     * Prints out a message to user.
     * @param s The message to be printed.
     */
    private void print(String s) {
        System.out.println("\t " + s);
    }

    /**
     * Prints out the Introduction to Duke program.
     */
    public void printIntro() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printLine();
        print("Hello! I'm duke.Duke");
        print("What can I do for you?");
        printLine();
    }

    /**
     * Prints out the Exit message of Duke program.
     * @return Exit message.
     */
    public String printExit() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints out all the Tasks at hand.
     * @param tasks A list of all the Tasks at hand.
     * @return String showcasing all Tasks.
     */
    public String printTasks(TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.getSize(); i++) {
            sb.append(String.format(" %d. %s\n", i + 1, tasks.getTask(i)));
        }
        return sb.toString();
    }

    /**
     * Prints out message that a Task has been added.
     * @param task The Task to be added.
     * @param tasks The list of all Tasks.
     * @return String message indicating task has been added.
     */
    public String printAddTasks(Task task, TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Got it. I've added this duke.task:\n");
        sb.append(String.format("  %s\n", task.toString()));
        sb.append(String.format("Now you have %d tasks in the list.", tasks.getSize()));
        return sb.toString();
    }

    /**
     * Prints out message that a Task was marked done.
     * @param task The Task that has been marked.
     * @return String message indicating the Task has been marked.
     */
    public String printMark(Task task) {
        return String.format("Nice! I've marked this duke.task as done:\n %s", task.toString());
    }

    /**
     * Prints out message that a Task was marked not done.
     * @param task The Task that has been marked.
     * @return String message indicating that Task has been unmarked.
     */
    public String printUnMark(Task task) {
        return String.format("OK, I've marked this duke.task as not done yet:\n %s", task.toString());
    }

    /**
     * Prints out message that Task has been deleted.
     * @param task The Task that has been deleted.
     * @param tasks The list of all Tasks.
     * @return String message indicating that Task has been deleted.
     */
    public String printDeleteTask(Task task, TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Noted. I've removed this task:\n");
        sb.append(String.format("  %s\n", task.toString()));
        sb.append(String.format("Now you have %d tasks in the list.", tasks.getSize()));
        return sb.toString();
    }

    /**
     * Prints out the Exception message.
     * @param e The exception caught.
     * @return String message of exception.
     */
    public String printException(Exception e) {
        return e.getMessage();
    }

    /**
     * Prints out all the Tasks that matches the filter.
     * @param tasks The list of Tasks that match filter.
     * @return String message showcasing all the Tasks that has been filtered.
     */
    public String printFilteredTasks(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(String.format(" %d. %s\n", i + 1, tasks.get(i)));
        }
        return sb.toString();
    }

    /**
     * Returns the next user input.
     * @return The next line of Scanner.
     */
    public String nextLine() {
        return this.sc.nextLine();
    }

    /**
     * Closes the Scanner.
     */
    public void close() {
        this.sc.close();
    }
}
