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
     * Initialises a Scanner.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints a out a line to user.
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
     */
    public void printExit() {
        print("Bye. Hope to see you again soon!");
    }

    /**
     * Prints out all the Tasks at hand.
     * @param tasks A list of all the Tasks at hand.
     */
    public void printTasks(TaskList tasks) {
        print("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            print(String.format("%d. %s", i + 1, tasks.getTask(i)));
        }
    }

    /**
     * Prints out message that a Task has been added.
     * @param task The Task to be added.
     * @param tasks The list of all Tasks.
     */
    public void printAddTasks(Task task, TaskList tasks) {
        print("Got it. I've added this duke.task:");
        print("  " + task.toString());
        print("Now you have " + tasks.getSize() + " tasks in the list.");
    }

    /**
     * Prints out message that a Task was marked done.
     * @param task The Task that has been marked.
     */
    public void printMark(Task task) {
        print("Nice! I've marked this duke.task as done:");
        print("  " + task.toString());
    }

    /**
     * Prints out message that a Task was marked not done.
     * @param task The Task that has been marked.
     */
    public void printUnMark(Task task) {
        print("OK, I've marked this duke.task as not done yet:");
        print("  " + task.toString());
    }

    /**
     * Prints out message that Task has been deleted.
     * @param task The Task that has been deleted.
     * @param tasks The list of all Tasks.
     */
    public void printDeleteTask(Task task, TaskList tasks) {
        print("Got it. I've added this duke.task:");
        print("  " + task.toString());
        print("Now you have " + tasks.getSize() + " tasks in the list.");
    }

    /**
     * Prints out the Exception message.
     * @param e The exception caught.
     */
    public void printException(Exception e) {
        printLine();
        print(e.getMessage());
    }

    /**
     * Prints out all the Tasks that matches the filter.
     * @param tasks The list of Tasks that match filter.
     */
    public void printFilteredTasks(ArrayList<Task> tasks) {
        print("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            print(String.format("%d. %s", i + 1, tasks.get(i)));
        }
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
