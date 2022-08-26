package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Ui deals with interactions with the user.
 */
public class Ui {
    private static final String LINE = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

    private Scanner sc;

    /**
     * Constructor for Ui.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Reads the next line from the user input.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints the String LINE.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Prints the welcome statement when the user first opens Duke.
     */
    public void showWelcome() {
        System.out.println("Hello! I am a ToDos, Events, Deadlines and Talk Bot, otherwise known as TEDTalk\n"
                + "What can I do for you today?");
    }

    /**
     * Prints the exit statement when the user inputs the exit command.
     */
    public void showBye() {
        System.out.println("Goodbye! I hope to see you again soon.");
    }

    /**
     * Prints the task that is to be marked as done.
     *
     * @param task Task to be marked as done.
     */
    public void showMark(Task task) {
        System.out.println("Task successfully completed!\n" + task);
    }

    /**
     * Prints the task that is to be marked as undone.
     *
     * @param task Task to be marked as undone
     */
    public void showUnmark(Task task) {
        System.out.println("Task incomplete.\n" + task);
    }

    /**
     * Prints the task that was newly added by the user.
     *
     * @param task Task to be added into the list.
     * @param size New number of tasks in the list.
     */
    public void showAdd(Task task, int size) {
        System.out.println("Successfully added new task:\n" + task
                + "\nYou have " + size + " task(s) in the list.");
    }

    /**
     * Prints the current list of tasks.
     *
     * @param taskList List of tasks.
     */
    public void showList(TaskList taskList) {
        System.out.println("Here are your tasks:");
        for (int i = 0; i < taskList.getSize(); i++) {
            System.out.println(i + 1 + ". " + taskList.getTask(i));
        }
    }

    /**
     * Prints the task that was removed from the list.
     *
     * @param task Task that was removed.
     * @param size New number of tasks in the list.
     */
    public void showDelete(Task task, int size) {
        System.out.println("Understood. I have removed this task:\n" + task
                + "\nYou have " + size + " task(s) in the list.");
    }

    /**
     * Prints list of tasks that occur on a particular date.
     *
     * @param tasks List of tasks that occur on that date.
     */
    public void showDate(ArrayList<Task> tasks) {
        if (tasks.size() != 0) {
            System.out.println("Here are the tasks on that date:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i + 1 + ". " + tasks.get(i));
            }
        } else {
            System.out.println("You have no tasks on that date.");
        }
    }

    /**
     * Prints error if no saved data is found.
     */
    public void showLoadingError() {
        System.out.println("No saved data found.");
    }

    /**
     * Prints error message when an invalid input is given.
     *
     * @param msg Error message to be printed.
     */
    public void showError(String msg) {
        System.out.println(msg);
    }
}
