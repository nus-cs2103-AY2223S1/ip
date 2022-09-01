package duke;
import duke.task.Task;

import java.util.Scanner;

/**
 * Deals with interactions with the user
 */
public class Ui {
    private final Scanner scanner = new Scanner(System.in);
    private static final String line = "---------------------------------------------------";

    /**
     * Reads the command typed by the user
     *
     * @return The command that the user typed
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Returns the word with the correct plural or singular form
     *
     * @param taskList The list of tasks
     * @return The word with the correct plural or singular form
     */
    private static String showTaskTense(TaskList taskList) {
        return taskList.size() == 1 ? " task" : " tasks";
    }

    /**
     * Displays a line
     */
    public void showLine() {
        System.out.println(line);
    }

    /**
     * Displays greeting
     */
    public void showGreeting() {
        System.out.println(line);
        System.out.println("Hi there! I'm Duke\n" +
                "     What's up?");
        System.out.println(line);
    }

    /**
     * Displays exit message
     */
    public void showExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays error message
     *
     * @param message The error message
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Displays added task message for the specified task
     *
     * @param task The specified task
     * @param taskList The list of tasks
     */
    public void showAddTask(Task task, TaskList taskList) {
        System.out.println("Got it. I've added this task:\n" + "  " + task.toString());
        System.out.println("Now you have " + taskList.size() + showTaskTense(taskList) + " in the list.");
    }

    /**
     * Displays the deleted task message for the specified task
     *
     * @param task The specified task
     * @param taskList The list of tasks
     */
    public void showDeleteTask(Task task, TaskList taskList) {
        System.out.println("Done! " + task.toString() + " has been deleted :(");
        System.out.println("Now you have " + taskList.size() + showTaskTense(taskList) + " left.");
    }

    /**
     * Displays the marked task message for the specified task
     *
     * @param task The specified task
     */
    public void showMarkedTask(Task task) {
        System.out.println("Good job! I've marked this task as done:");
        System.out.println(task.toString());
    }

    /**
     * Displays the unmarked task message for the specified task
     *
     * @param task The specified task
     */
    public void showUnmarkedTask(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task.toString());
    }

    /**
     * Displays the specified task list
     *
     * @param taskList The specified task list
     */
    public void showTaskList(TaskList taskList) {
        if (taskList.size() == 0) {
            System.out.println("Nothing to do right now...");
        } else {
            System.out.println("Tasks: ");
            System.out.println(taskList);
        }
        System.out.println("You have " + taskList.size() + showTaskTense(taskList) + "!");
    }

    /**
     * Displays the list of tasks on a specified date
     *
     * @param taskList The list of tasks on a specified date
     * @param dateStr The specified date
     */
    public void showTasksOnDate(TaskList taskList, String dateStr) {
        if (taskList.size() != 0) {
            System.out.println("These are the tasks on " + dateStr + ":");
            System.out.println(taskList);
            System.out.println("You have " + taskList.size() + showTaskTense(taskList)
                    + " on " + dateStr + ".");
        } else {
            System.out.println("There are no tasks on this date!");
        }
    }
}
