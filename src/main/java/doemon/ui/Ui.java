package doemon.ui;

import java.util.ArrayList;
import java.util.Scanner;

import doemon.exception.DoemonException;
import doemon.task.Task;
import doemon.task.TaskList;

/**
 * Handles Doemon chat bot message displays.
 */
public class Ui {
    /** Text art of Doemon. */
    private static final String logo =
            "                       _______________\n"
            + "                      /  --. --.      \\ \n"
            + "                     /  | '| ' |   \\   \\ \n"
            + "                    / /  `-O--'     \\   \\ \n"
            + "                   |.  --  |  --     |   |\n"
            + "                   |  --   |  --     |   |\n"
            + "                    \\  (___|_______) /  /\n"
            + "                     \\              /  /\n"
            + "                       |== (t) ===|____";
    /** Introduction string that is printed when Doemon is started. */
    private static final String introStr = "Hello I'm\n" + logo + "\t\t\tDoemon!";
    /** String that is printed when Doemon is exited. */
    private static final String exitStr = "I'm going to sleep now...See you again soon!";

    /** Scanner used to take in user input. */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Reads next line of user input.
     *
     * @return User-inputted string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays welcome message.
     */
    public void showWelcome() {
        System.out.println(output(this.introStr));
    }

    /**
     * Displays specified task.
     *
     * @param task Task to be displayed.
     */
    public void showTask(Task task) {
        System.out.println(output(task.toString()));
    }

    /**
     * Displays the specified list of tasks.
     *
     * @param tasks List of tasks to be displayed.
     */
    public void showTaskList(TaskList tasks) {
        StringBuilder listStringBuilder =
                new StringBuilder("Here is what's on my bread:\n\t");
        if (tasks.getSize() == 0) {
            System.out.println(output("You have no tasks!"));
        } else {
            for (int i = 1; i <= tasks.getSize(); i++) {
                listStringBuilder.append(i)
                        .append(".")
                        .append(tasks.getTask(i - 1))
                        .append("\n\t");
            }
            System.out.println(output(listStringBuilder.toString().trim()));
        }
    }

    /**
     * Displays add task message.
     *
     * @param task Task to be added.
     * @param numTasks New total number of tasks.
     */
    public void showAddTask(Task task, int numTasks) {
        System.out.println(
                output(String.format(
                        "Alright! I have recorded this task on my bread:\n\t"
                        + "  %s\n\tYou now have %d task(s) recorded on my bread.",
                        task.toString(),
                        numTasks)));
    }

    /**
     * Displays mark task message.
     *
     * @param task Task to be marked.
     */
    public void showMarkTask(Task task) {
        System.out.println(output(
                String.format("Yay! This task is now marked as done:\n\t  %s", task)));
    }

    /**
     * Displays unmark task message.
     *
     * @param task Task to be unmarked.
     */
    public void showUnmarkTask(Task task) {
        System.out.println(output(
                String.format("I guess you weren't done with that one:\n\t  %s", task)));
    }

    /**
     * Displays delete task message.
     *
     * @param task Task to be deleted.
     * @param numTasks New total number of tasks.
     */
    public void showDeleteTask(Task task, int numTasks) {
        System.out.println(output(
                String.format("I used a knife to slice off this task from my bread:\n\t  %s"
                + "\n\tThere are %d items left on my bread.", task, numTasks)));
    }

    /**
     * Displays all tasks found using the find keyword.
     *
     * @param tasks List of tasks found.
     */
    public void showFoundTasks(ArrayList<Task> tasks) {
        int taskNum = 1;
        if (tasks.size() == 0) {
            System.out.println(output("I couldn't find any matches on my bread..."));
        } else {
            StringBuilder sb = new StringBuilder("Here are the matches I found on my bread: ");
            for (Task task : tasks) {
                sb.append("\n\t").append(taskNum++).append(".").append(task.toString());
            }
            System.out.println(output(sb.toString()));
        }
    }

    /**
     * Displays error message.
     *
     * @param e Error to be displayed.
     */
    public void showError(DoemonException e) {
        System.out.println(output(e.toString()));
    }

    /**
     * Displays exit message.
     */
    public void showExit() {
        System.out.println(output(this.exitStr));
    }

    /**
     * Returns a formatted string to display the given text.
     *
     * @param text Text to be formatted.
     * @return Formatted string.
     */
    private static String output(String text) {
        String line = "____________________________________________________________";
        return String.format("\t%s\n\t%s\n\t%s", line, text, line);
    }
}
