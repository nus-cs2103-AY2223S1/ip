package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Represents the Duke bot.
 */
public class Duke {
    private static boolean isLoading = true;
    private static boolean isRunning = true;
    private static Scanner sc = new Scanner(System.in);
    private String filePath = "C:/Data/Duke.txt";

    private Parser parser;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for the Duke bot.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage(this.filePath);
        this.tasks = new TaskList();
        this.parser = new Parser();
    }

    /**
     * Stops the Duke bot.
     */
    public void stop() {
        Duke.isRunning = false;
    }

    /**
     * Get the storage associated with the Duke bot.
     *
     * @return Storage of the Duke bot.
     */
    public Storage getStorage() {
        return this.storage;
    }

    /**
     * Returns whether the Duke bot has finished loading.
     *
     * @return Boolean indicating whether the Duke bot has finished loading.
     */
    public boolean hasFinishedLoading() {
        return !this.isLoading;
    }

    /**
     * Prints a list of tasks on hand.
     */
    public void getList() {
        this.tasks.getList();
    }

    /**
     * Mark the task as completed.
     *
     * @param i Index of the task to be marked as completed.
     */
    public void markTask(int i) {
        this.tasks.markTask(i);
    }

    /**
     * Mark the task as not completed.
     *
     * @param i Index of the task to be marked as not completed.
     */
    public void unmarkTask(int i) {
        this.tasks.unmarkTask(i);
    }

    /**
     * Adds a task without deadline.
     *
     * @param s Task description.
     */
    public void addTodo(String s) {
        this.tasks.addTodo(s);
    }

    /**
     * Adds a task with deadline.
     *
     * @param s Task description.
     * @param d Deadline in LocalDate format.
     */
    public void addDeadline(String s, LocalDate d) {
        this.tasks.addDeadline(s, d);
    }

    /**
     * Adds a task with deadline.
     *
     * @param s Task description.
     * @param d Deadline in String format.
     */
    public void addDeadline(String s, String d) {
        this.tasks.addDeadline(s, d);
    }

    /**
     * Adds an event.
     *
     * @param s Event description.
     * @param d Event time in LocalDate format.
     */
    public void addEvent(String s, LocalDate d) {
        this.tasks.addEvent(s, d);
    }

    /**
     * Adds an event.
     *
     * @param s Event description.
     * @param d Event time in String format.
     */
    public void addEvent(String s, String d) {
        this.tasks.addEvent(s, d);
    }

    /**
     * Delete the task from the list.
     *
     * @param i Index of the task to be deleted.
     */
    public void deleteTask(int i) {
        this.tasks.deleteTask(i);
    }

    /**
     * Parse a message written to the Duke bot.
     *
     * @param s Message to be parsed.
     */
    public void parse(String s) {
        this.parser.parse(this, s, this.hasFinishedLoading());
    }

    /**
     * Main method to start the Duke bot running.
     *
     * @param args Inputs to interact with the Duke bot.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        try {
            duke.storage.loadData(duke, duke.filePath);
        } catch (IOException e) {
            System.err.println("File not found.");
        }
        duke.isLoading = false;
        System.out.println(duke.ui.logo());
        while (duke.isRunning) {
            duke.parse(sc.nextLine());
        }
    }
}