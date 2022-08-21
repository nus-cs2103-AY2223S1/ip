package duke;

import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;

public class Ui {
    /**
     * Line break before Duke receives a command
     */
    private static final String lineBreakBefore =
            "-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_"
            + "-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-";
    /**
     * Line break after Duke receives a command
     */
    private static final String lineBreakAfter =
            "______________________________________________________"
            + "______________________________________________________";
    /**
     * Duke's logo
     */
    private static final String logo =
            " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|";
    /**
     * First line of Duke's greeting
     */
    private static final String greeting1 = "Hello! I'm duke.Duke.";
    /**
     * Second line of Duke's greeting
     */
    private static final String greeting2 = "What can I do for you?";

    /**
     * Scanner object that Ui will use to read user inputs
     */
    private Scanner commandInput;

    /**
     * Constructor for a Ui object
     * @since 0.1
     */
    public Ui() {
        this.commandInput = new Scanner(System.in);
    }

    /**
     * Greets the user
     * @since 0.1
     */
    public void greet() {
        System.out.println(LOGO + "\n" + GREETING_ONE);
        System.out.println(GREETING_TWO);
    }

    /**
     * Lists all the tasks in taskList
     * @since 0.1
     */
    public void list() {
        System.out.println("Here are the current tasks in your list:");
    }

    /**
     * Informs the user that the task has been marked
     * @param task the marked task
     * @since 0.1
     */
    public void mark(Task task) {
        System.out.println("Good Job! I will mark this duke.task as done:" + "\n" + task);
    }

    /**
     * Informs the user that the task has been unmarked
     * @param task the unmarked task
     * @since 0.1
     */
    public void unmark(Task task) {
        System.out.println("Alright, I will mark this duke.task as undone:" + "\n" + task);
    }

    /**
     * Apologizes to the user because Duke does not understand the command
     * @param exception the apology
     */
    public void showDukeException(String exception) {
        System.out.println(exception);
    }

    /**
     * Duke's final goodbye to the user.
     * @since 0.1
     */
    public void finalGoodbye() {
        commandInput.close();
        System.out.println("Goodbye. Call for me again when you need me!");
    }

    /**
     * Informs the user that a task has been added
     * @param task Task to add
     * @param size size of taskList that Duke is using
     * @since 0.1
     */
    public void addTask(Task task, int size) {
        System.out.println("Adding to Tasks:" + "\n"
                + task
                + "\nYou have " + size + " tasks in the list.");
    }

    /**
     * Informs the user that a task has been deleted
     * @param task Task to delete
     * @param size size of taskList that Duke is using
     * @since 0.1
     */
    public void delete(Task task, int size) {
        System.out.println("Understood. I will purge this duke.task from your list:\n" + task
                + "\nCurrently, you have " + size + " tasks in your list.");
    }

    /**
     * Method to read the user's input in System.in
     * @return user input as a String
     */
    public String readCommand() {
        System.out.println(LINE_BREAK_BEFORE);
        String command = commandInput.nextLine();
        System.out.println(LINE_BREAK_AFTER);
        return command;
    }

    public void readList(ArrayList<Task> tasks) {
        list();
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }
}
