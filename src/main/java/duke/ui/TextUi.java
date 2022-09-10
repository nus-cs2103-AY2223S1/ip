package duke.ui;

import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskList;


/**
 * A TextUI class to deal with the interactions with the user.
 */
public class TextUi {
    private final Scanner scanner;

    public TextUi() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints out the given text with dividers to the console.
     *
     * @param text The specified text to be printed to the console.
     */
    public void printTextWithDivider(String text) {
        final String divider = "-".repeat(100) + "\n";
        System.out.println(divider + text + divider);
    }

    /**
     * Prints the error message when loading tasks from file.
     */
    public void showLoadingError() {
        String str = "Unable to load tasks from file!\n";
        printTextWithDivider(str);
    }

    /**
     * Prints the welcome message with the Duke logo.
     */
    public void showWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String greetingMsg = "Hello! I'm duke.Duke\n"
                + "What can I do for you?\n";

        String welcomeMsg = logo + greetingMsg;

        printTextWithDivider(welcomeMsg);
    }

    /**
     * Prints goodbye message when the application closes.
     */
    public void showGoodByeMessage() {
        String goodbyeMsg = "Bye. Hope to see you again soon!\n";
        printTextWithDivider(goodbyeMsg);
    }

    /**
     * Reads user input and return the string value of the input.
     *
     * @return The string representation of user input.
     */
    public String readUserInput() {
        return this.scanner.nextLine();
    }

    /**
     * Prints the success message after a task is added.
     *
     * @param task The task to be added.
     * @param taskList The list of tasks that the task is adding to.
     */
    public void showAddTaskMessage(Task task, TaskList taskList) {
        String message = "Got it. I've added this task:\n"
                + "  " + task + "\n"
                + "Now you have " + taskList.size() + " task(s) in the list.\n";

        printTextWithDivider(message);
    }

    /**
     * Prints the success message after marking a task as done.
     *
     * @param task The task to be marked as done.
     */
    public void showMarkTaskMessage(Task task) {
        String message = "Nice! I've marked this as done:\n"
                + task + "\n";
        printTextWithDivider(message);
    }

    /**
     * Prints the success message after marking a task as undone.
     *
     * @param task The task to be marked as undone.
     */
    public void showUnmarkTaskMessage(Task task) {
        String message = "Ok, I've marked this task as not done yet:\n"
                + task + "\n";
        printTextWithDivider(message);
    }

    /**
     * Prints the success message after a task is removed from the task list.
     *
     * @param task The task to be removed.
     * @param taskList The task list that the task is being removed from.
     */
    public void showRemoveTaskMessage(Task task, TaskList taskList) {
        String message = "Noted. I've removed this task:\n"
                + "  " + task + "\n"
                + "Now you have " + taskList.size() + " task(s) in the list.\n";
        printTextWithDivider(message);
    }

    /**
     * Prints out the matching tasks.
     *
     * @param taskList The list of matching tasks.
     */
    public void showFindTaskMessage(ArrayList<Task> taskList) {
        StringBuilder message = new StringBuilder();
        message.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            // Display task as 1-index
            message.append(i + 1).append(".").append(taskList.get(i)).append("\n");
        }
        printTextWithDivider(message.toString());
    }

    /**
     * Prints out all the tasks in the task list.
     *
     * @param taskList The list of tasks in Duke.
     */
    public void showListTaskMessage(TaskList taskList) {
        StringBuilder str = new StringBuilder();
        str.append("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            // Display task as 1-index
            str.append(i + 1).append(".").append(taskList.getTask(i)).append("\n");
        }

        printTextWithDivider(str.toString());
    }
}
