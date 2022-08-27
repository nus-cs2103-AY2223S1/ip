package duke.util;

import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;

/**
 * The program that manages Duke's communications with the user
 * @author Nephelite
 * @version 0.2
 */
public class Ui {
    /**
     * Line break before Duke receives a command
     */
    private static final String LINE_BREAK_BEFORE =
            "-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_"
                    + "-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-";
    /**
     * Line break after Duke receives a command
     */
    private static final String LINE_BREAK_AFTER =
            "______________________________________________________"
                    + "______________________________________________________";
    /**
     * Duke logo
     */
    private static final String LOGO =
            " ____        _\n"
                    + "|  _ \\ _   _| | _____\n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|";
    /**
     * First line of Duke's greeting
     */
    private static final String GREETING_NAME = "Hello! I'm Duke.";
    /**
     * Second line of Duke's greeting
     */
    private static final String GREETING_QUESTION = "What can I do for you?";

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
     * @return Duke's greetings
     * @since 0.2
     */
    public String greet() {
        return LOGO + "\n" + GREETING_NAME + "\n" + GREETING_QUESTION;
    }

    /**
     * Lists all the tasks in taskList
     * @return Duke's response to reading a list
     * @since 0.2
     */
    public String list() {
        return "Here are the current tasks in your list:";
    }

    /**
     * Informs the user that the task has been marked
     * @param task the marked task
     * @return Duke's response to marking the task
     * @since 0.2
     */
    public String mark(Task task) {
        return "Good Job! I will mark this duke.task as done:" + "\n" + task;
    }

    /**
     * Informs the user that the task has been unmarked
     * @param task the unmarked task
     * @return Duke's response to unmarking the task
     * @since 0.2
     */
    public String unmark(Task task) {
        return "Alright, I will mark this duke.task as undone:" + "\n" + task;
    }

    /**
     * Apologizes to the user because Duke does not understand the command
     * @param exception the apology
     * @return DukeException in String form
     * @since 0.2
     */
    public String showDukeException(String exception) {
        return exception;
    }

    /**
     * Duke's final goodbye to the user.
     * @return Duke's goodbyes
     * @since 0.2
     */
    public String finalGoodbye() {
        commandInput.close();
        return "Goodbye. Call for me again when you need me!";
    }

    /**
     * Informs the user that a task has been added
     * @param task Task to add
     * @param size size of taskList that Duke is using
     * @return Duke's response to adding a task
     * @since 0.2
     */
    public String addTask(Task task, int size) {
        return "Adding to Tasks:" + "\n"
                + task
                + "\nYou have " + size + " tasks in the list.";
    }

    /**
     * Informs the user that a task has been deleted
     * @param task Task to delete
     * @param size size of taskList that Duke is using
     * @return Duke's response to deleting a the task
     * @since 0.2
     */
    public String delete(Task task, int size) {
        return "Understood. I will purge this duke.task from your list:\n" + task
                + "\nCurrently, you have " + size + " tasks in your list.";
    }

    /**
     * Reads a given list
     * @param tasks ArrayList contained within TaskList
     * @return Duke's response to reading the list of tasks, and the list of tasks for Duke to recite
     * @since 0.2
     */
    public String readList(ArrayList<Task> tasks) {
        String list = list();
        for (int i = 0; i < tasks.size(); i++) {
            list += "\n" + (i + 1) + ". " + tasks.get(i);
        }
        return list;
    }
}
