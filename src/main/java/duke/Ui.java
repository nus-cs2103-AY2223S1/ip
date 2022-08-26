package duke;

import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskList;

/**
 * The Ui class deals with interactions with the user.
 */
public class Ui {
    private static final String INDENTATION = "    ";
    private static final String LOGO = INDENTATION + " ____        _        \n"
            + INDENTATION + "|  _ \\ _   _| | _____ \n"
            + INDENTATION + "| | | | | | | |/ / _ \\\n"
            + INDENTATION + "| |_| | |_| |   <  __/\n"
            + INDENTATION + "|____/ \\__,_|_|\\_\\___|";
    private static final String LINE = INDENTATION
            + "____________________________________________________________";

    /** The scanner that reads inputs from the user. */
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads a command from the user.
     *
     * @return The string representing the command from the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Shows a message that a specified task has been added to a task list.
     *
     * @param task The specified task that was added.
     * @param tasks The task list that the specified task was added to.
     */
    public void showAdded(Task task, TaskList tasks) {
        showLine();
        System.out.println(INDENTATION + "Got it. I've added this task:\n"
                + INDENTATION + "  " + task);
        System.out.println(INDENTATION + tasks.getCountStatement());
        showLine();
    }

    /**
     * Shows a message that a specified task has been deleted from a task list.
     *
     * @param task The specified task that was deleted.
     * @param tasks The task list that the specified task was deleted from.
     */
    public void showDeleted(Task task, TaskList tasks) {
        showLine();
        System.out.println(INDENTATION + "Noted. I've removed this task:\n"
                + INDENTATION + "  " + task);
        System.out.println(INDENTATION + tasks.getCountStatement());
        showLine();
    }

    /**
     * Shows an error message from Duke.
     *
     * @param message The message that describes the error with Duke.
     */
    public void showError(String message) {
        showLine();
        System.out.println(INDENTATION + "☹ OOPS!!! " + message);
        showLine();
    }

    /**
     * Shows the exit message from Duke.
     */
    public void showExit() {
        showLine();
        System.out.println(INDENTATION + "Bye. Hope to see you again soon!");
    }

    /**
     * Shows every task found with a keyword.
     */
    public void showFound(TaskList tasks) {
        showLine();
        System.out.println(INDENTATION + "Here are the matching tasks in your list:");
        String[] strings = tasks.allToString();
        if (strings.length == 0) {
            System.out.println(INDENTATION + "You have no matching tasks!");
        } else {
            for (int i = 0; i < strings.length; i++) {
                System.out.println(INDENTATION + (i + 1) + "." + strings[i]);
            }
        }
        showLine();
    }

    /**
     * Shows an indented horizontal line.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Shows a list of all the tasks in a specified TaskList.
     *
     * @param tasks The specified TaskList to show the list of tasks from.
     */
    public void showList(TaskList tasks) {
        showLine();
        System.out.println(INDENTATION + "Here are the tasks in your list:");
        String[] strings = tasks.allToString();
        for (int i = 0; i < strings.length; i++) {
            System.out.println(INDENTATION + (i + 1) + "." + strings[i]);
        }
        showLine();
    }

    /**
     * Shows a message that a specified task has been marked as done.
     *
     * @param task The specified task that was marked as done.
     */
    public void showMarked(Task task) {
        showLine();
        System.out.println(INDENTATION + "Nice! I've marked this task as done:\n"
                + INDENTATION + "  " + task);
        showLine();
    }

    /**
     * Shows every task occurring by/at a specific date.
     *
     * @param tasks A TaskList containing the tasks occurring by/at a specific date.
     */
    public void showOnDate(TaskList tasks) {
        showLine();
        System.out.println(INDENTATION + "Here are the tasks in your list on this date:");
        String[] strings = tasks.allToString();
        if (strings.length == 0) {
            System.out.println(INDENTATION + "You have no tasks on this date!");
        } else {
            for (int i = 0; i < strings.length; i++) {
                System.out.println(INDENTATION + (i + 1) + "." + strings[i]);
            }
        }
        showLine();
    }

    /**
     * Shows a message that a specified task has been marked as done.
     *
     * @param task The specified task that was marked as done.
     */
    public void showUnmarked(Task task) {
        showLine();
        System.out.println(Ui.INDENTATION + "OK, I've marked this task as not done yet:\n"
                + Ui.INDENTATION + "  " + task);
        showLine();
    }

    /**
     * Greets the user when Duke starts running.
     */
    public void showWelcome() {
        System.out.println(INDENTATION + "Hello from\n" + LOGO);
        showLine();
        System.out.println(INDENTATION + "Hello! I'm Duke\n"
                + INDENTATION + "What can I do for you?");
        showLine();
    }
}
