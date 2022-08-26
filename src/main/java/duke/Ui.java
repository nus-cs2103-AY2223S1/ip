package duke;

import duke.task.Task;

import java.util.Scanner;

/**
 * Represents the user interface
 */
public class Ui {

    private static final String GREETINGS = "Hello! I'm Duke\n" + "What can I do for you?";
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String LINE = "______________________________________________________________________________";
    private static final String LIST_MESSAGE = "Here are the tasks in your list:";
    private static final String ADD_MESSAGE = "Got it. I've added this task:";
    private static final String DELETE_MESSAGE = "Noted. I've removed this task:";
    private static final String DONE_MESSAGE = "Nice! I've marked this task as done:";
    private static final String UNDONE_MESSAGE = "OK, I've marked this task as not done yet:";
    private static final String FIND_MESSAGE = "Here are the matching tasks in your list:";

    Scanner sc = new Scanner(System.in);

    /**
     * Prints greeting message
     */
    protected void greet() {
        System.out.println(LINE);
        System.out.println(GREETINGS);
        System.out.println(LINE);
    }

    /**
     * Prints exit message
     */
    public void exit() {
        System.out.println(EXIT_MESSAGE);
    }

    /**
     * Prints line break
     */
    protected void showLine() {
        System.out.println(LINE);
    }

    /**
     * Prints error message
     *
     * @param message Error message
     */
    protected void showError(String message) {
        System.err.println(message);
    }

    /**
     * Prints add task message
     *
     * @param task Target task
     * @param tasks Task list
     */
    public void addTaskMessage(Task task, TaskList tasks) {
        System.out.println(ADD_MESSAGE);
        System.out.println(task.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Displays all task in task list
     *
     * @param tasks Task list
     */
    public void displayTasks(TaskList tasks) throws DukeException {
        System.out.println(LIST_MESSAGE);
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(i + ". " + tasks.get(i - 1).toString());
        }
    }

    /**
     * Prints delete task message
     *
     * @param task Task to be deleted
     * @param tasks Task list
     */
    public void deleteTaskMessage(Task task, TaskList tasks) {
        System.out.println(DELETE_MESSAGE);
        System.out.println(task.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Prints mark done message
     *
     * @param task Target task
     */
    public void markDone(Task task) {
        System.out.println(DONE_MESSAGE);
        System.out.println(task.toString());
    }

    /**
     * Prints mark undone message
     *
     * @param task Target task
     */
    public void markUndone(Task task) {
        System.out.println(UNDONE_MESSAGE);
        System.out.println(task.toString());
    }

    /**
     * Prints find task message
     *
     * @param tasks Task list
     */
    public void findTask(TaskList tasks) throws DukeException {
        System.out.println(FIND_MESSAGE);
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(i + ". " + tasks.get(i - 1).toString());
        }
    }

    /**
     * Reads user's input
     *
     * @return user's input
     */
    protected String readCommand() {
        return sc.nextLine();
    }
}
