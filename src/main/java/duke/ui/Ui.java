package duke.ui;

import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Ui deals with interactions with the user.
 */
public class Ui {
    private static final String DIVIDER = "____________________________________________________________\n";
    private final Scanner sc;

    /**
     * Constructor for <code>Ui</code>.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Displays the reply message.
     * @param reply
     */
    private void generateReply(String reply) {
        System.out.print(DIVIDER + reply + "\n" + DIVIDER + "\n");
    }

    /**
     * Displays an error message.
     * @param errorMessage
     */
    public void showError(String errorMessage) {
        System.err.println(DIVIDER + errorMessage + "\n" + DIVIDER);
    }

    /**
     * Displays the greeting message.
     */
    public void greet() {
        generateReply("Hello! I'm Sheep\n"
                + "What can I do for you?");
    }

    /**
     * Displays a loading error when cannot load the tasks store from memory.
     */
    public void showLoadingError() {
        showError("Cannot find the file from given file path.\n"
                + "Create a new to-do list.");
    }

    /**
     * Displays a message when add a task successfully.
     * @param task
     * @param size
     */
    public void showAddTask(Task task, int size) {
        generateReply("Added " + task.toString() + " to the list.\n"
                + "There are " + size + " tasks in the list.");
    }

    /**
     * Displays the tasks in the task list.
     * @param tasks
     */
    public void showTaskList(TaskList tasks) {
        generateReply(tasks.toString());
    }

    /**
     * Display a message when mark a task successfully.
     * @param task
     */
    public void showMarkDone(Task task) {
        generateReply("Nice! I have marked this task as done\n"
                + task.toString());
    }
    /**
     * Display a message when unmark a task successfully.
     * @param task
     */
    public void showUnmarkDone(Task task) {
        generateReply("Oh no! I have marked this task as not done\n"
                + task.toString());
    }
    /**
     * Display a message when delete a task successfully.
     * @param task
     */
    public void showRemoveTask(Task task) {
        generateReply("Done! I have removed this task from your todo list\n"
                + task.toString());
    }

    /**
     * Display a message when exit the chat bot.
     */
    public void showBye() {
        generateReply("Ok bye, see you later.");
        sc.close();
    }

    /**
     * Read the input from the user.
     * @return
     */
    public String readInput() {
        return sc.nextLine();
    }
}
