package duke;

import duke.task.Task;

import java.util.Scanner;

/**
 * The User Interface (UI) that manages what is shown or printed to the user
 */
public class Ui {

    private static final String LINE = "\n========================================================";
    private static final String WELCOME_MESSAGE = "Hello, my name is Duke!\nHow can I help you today?";
    private static final String EXIT_MESSAGE = LINE + "\nGoodbye! Looking forward to see you again soon!" + LINE;
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private final Scanner input;

    /**
     * A constructor for the Ui class
     */
    public Ui() {
        this.input = new Scanner(System.in);
    }

    /**
     * Reads the input from the user
     * @return The input given
     */
    public String readInput() {
        return input.nextLine();
    }

    /**
     * Prints out a welcome message upon running the program
     */
    public void printWelcomeMessage() {
        System.out.println(LOGO + LINE + "\n" + WELCOME_MESSAGE + LINE);
    }

    /**
     * Prints out an exit message upon ending the program
     */
    public void printExitMessage() {
        System.out.println(EXIT_MESSAGE);
    }

    /**
     * Prints out an error message upon failing to read/load the tasks from a file
     *
     * @param fileName The path to the file
     */
    public void printLoadingError(String fileName) {
        System.out.println("Failed to find the task file: " + fileName + "\nCreating a new task file...");
    }

    public void printCorruptedError() {
        System.out.println("The data file is corrupted! (Eg. Illegal/unknown text)"
            + "\nResetting the task file...");
    }

    /**
     * Prints out a success message upon reading/loading the tasks from a file
     */
    public void printLoadingSuccessMessage() {
        System.out.println("Previously saved tasks have been loaded!");
    }

    /**
     * Prints out a divider line
     */
    public void printLine() {
        System.out.println(LINE);
    }

    /**
     * Prints out a message upon inputting an unknown command
     */
    public void printUnknownCommandMessage() {
        printLine();
        System.out.print("I'm sorry, but I don't know what that means");
        printLine();
    }

    /**
     * Prints out an error message
     * @param message The error message
     */
    public void printError(String message) {
        printLine();
        System.out.print(message);
        printLine();
    }

    /**
     * Prints out a message upon printing the task list that is empty
     */
    public void printEmptyListMessage() {
        System.out.println(Ui.LINE + "\nYour list is empty! Why not add a task to it first?" + Ui.LINE);
    }

    /**
     * Prints out a success message upon marking a task as done
     *
     * @param t The task to be marked
     */
    public void printTaskMarked(Task t) {
        printLine();
        System.out.println("\nOkay, I have marked this task as done: \n" + t.toString());
        printLine();
    }

    /**
     * Prints out a success message upon marking a task as not done
     * @param t The task to be marked
     */
    public void printTaskUnmarked(Task t) {
        printLine();
        System.out.println("\nOkay, I have marked this task as not done: \n" + t.toString());
        printLine();
    }

    /**
     * Prints out a success message upon creating a Todo task
     * @param t The task created
     * @param s The total number of tasks in the list of tasks
     */
    public void printToDoCreated(Task t, int s) {
        printLine();
        System.out.println("\nGot it! I have added this task to your list:\n  " + t.toString()
                + "\nNow you have " + s + " tasks in the list.");
        printLine();
    }

    /**
     * Prints out a success message upon creating a Deadline task
     * @param t The task created
     * @param s The total number of tasks in the list of tasks
     */
    public void printDeadlineCreated(Task t, int s) {
        printLine();
        System.out.println("\nGot it! I have added this task to your list:\n  " + t.toString()
                + "\nNow you have " + s + " tasks in the list.");
        printLine();
    }

    /**
     * Prints out a success message upon creating an Event task
     * @param t The task created
     * @param s The total number of tasks in the list of tasks
     */
    public void printEventCreated(Task t, int s) {
        printLine();
        System.out.println("\nGot it! I have added this task to your list:\n  " + t.toString()
                + "\nNow you have " + s + " tasks in the list.");
        printLine();
    }

    /**
     * Prints out a success message upon deleting a task from the list
     * @param t The specific task to be deleted
     * @param s The total number of tasks left in the list of tasks
     */
    public void printTaskDeleted(Task t, int s) {
        printLine();
        System.out.println("\nOkay, I have removed this task from the list:\n  " + t.toString()
                + "\nNow you have " + s + " tasks in the list.");
        printLine();
    }
}