package duke.ui;

import duke.exception.DukeException;

import duke.task.Task;
import duke.task.TaskList;

/**
 * User interface for Duke application.
 */
public class Ui {
    private final String DASH = "----------------------------------------";

    private void printTemplate(String message) {
        System.out.println(String.format("%s\n%s\n%s", DASH, message, DASH));
    }

    /**
     * Prints welcome message.
     */
    public void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String welcomeMessage = "Hello from\n" + logo + "\nWait, I'm not Duke\n"
                + "I'm Yem, your personal assistant\nWhat can I do for you master?";
        printTemplate(welcomeMessage);
    }

    /**
     * Prints farewell message.
     */
    public void printFarewellMessage() {
        printTemplate("Bye. See you later master!");
    }

    /**
     * Prints error message of a DukeException.
     *
     * @param error the DukeException which message is going to be shown.
     */
    public void printError(DukeException error) {
        printTemplate(error.getMessage());
    }

    /**
     * Prints task creation success message.
     *
     * @param newTask the newly created Task.
     * @param currentNumberOfTasks the current number of tasks.
     */
    public void printTaskCreationSuccessMessage(Task newTask, int currentNumberOfTasks) {
        String successMessage = "This task is successfully added:\n " + newTask.toString()
                + "\n Now you have " + currentNumberOfTasks + " task(s) in the list";
        printTemplate(successMessage);
    }

    /**
     * Prints tasks deletion success message.
     *
     * @param deletedTask the newly deleted Task.
     * @param currentNumberOfTasks the current number of tasks.
     */
    public void printTaskDeletionSuccessMessage(Task deletedTask, int currentNumberOfTasks) {
        String successMessage = "Noted. I've removed this task:\n " + deletedTask.toString()
                + "\n Now you have " + currentNumberOfTasks + " task(s) in the list";
        printTemplate(successMessage);
    }

    /**
     * Prints task marking success message.
     *
     * @param markedTask the newly marked Task.
     */
    public void printTaskMarkSuccessMessage(Task markedTask) {
        String successMessage = String.format("This task has been marked as done:\n %s",
                markedTask);
        printTemplate(successMessage);
    }

    /**
     * Prints task unmarking success message.
     *
     * @param unmarkedTask the newly unmarked Task.
     */
    public void printTaskUnmarkSuccessMessage(Task unmarkedTask) {
        String successMessage = String.format("This task has been marked as not done yet:\n %s",
                unmarkedTask);
        printTemplate(successMessage);
    }

    /**
     * Prints all Tasks in a TaskList.
     *
     * @param taskList the TaskList to be shown.
     */
    public void printTaskList(TaskList taskList) {
        printTemplate(taskList.toString());
    }
}
