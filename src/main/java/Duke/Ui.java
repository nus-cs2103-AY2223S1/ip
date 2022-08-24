package Duke;

import Duke.enums.Command;
import Duke.enums.SecondaryCommand;
import Duke.task.Task;
import Duke.task.TaskList;

/**
 * The {@code Ui} class contains outputs used throughout the application.
 */
public class Ui {

    /**
     * Prints messages to be seen when the application is started.
     * It contains an introduction before showing a
     * {@link #listValidInstructions() list of valid instructions} and a
     * {@link #lineDivider() line divider }.
     */
    public void startPrompt() {
        System.out.println("Hi from Yi Xian");
        listValidInstructions();
        lineDivider();
    }

    /**
     * Prints a list of valid instructions that can be executed by the application.
     */
    public void listValidInstructions() {
        System.out.println("What can I do for you?");
        System.out.printf("- %s (task name)%n", Command.TODO.getValue());
        System.out.printf("- %s (task name) %s (date) \n",
                Command.DEADLINE.getValue(), SecondaryCommand.BY.getValue());
        System.out.printf("- %s (task name) %s (date) \n",
                Command.EVENT.getValue(), SecondaryCommand.AT.getValue());
        System.out.printf("- %s\n", Command.LIST.getValue());
        System.out.printf("- %s (index)\n", Command.CHECK.getValue());
        System.out.printf("- %s (index)\n", Command.UNCHECK.getValue());
        System.out.printf("- %s (index)\n", Command.DELETE.getValue());
        System.out.printf("- %s\n", Command.BYE.getValue());
    }

    /**
     * Prints a list of valid dateFormats that can be parsed by the application.
     */
    public void listValidDateFormats() {
        System.out.println("These are the following accepted formats:");
        System.out.println("1) yyyy-mm-dd");
    }

    /**
     * Prints a message after marking a task as done.
     */
    public void markDone(String taskName) {
        System.out.println("Nice! I have marked (" + taskName + ") as done!");
    }

    /**
     * Prints a message after marking a task as undone.
     */
    public void markUndone(String taskName) {
        System.out.println("Nice! I have marked (" + taskName + ") as undone!");
    }

    /**
     * A function that list all the tasks in the list.
     *
     * @param taskList task list that contains tasks to be displayed.
     */
    public void listTasks(TaskList taskList) {
        StringBuilder output = new StringBuilder();
        output.append("\nCurrent Tasking\n");
        for (int i = 0; i < taskList.getNumberOfTask(); i++) {
            output.append(String.format("%d) %s\n", i + 1, taskList.getTask(i)));
        }
        output.append("Number of tasking: ").append(taskList.getNumberOfTask());
        System.out.println(output);
    }

    /**
     * Prints a message after marking adding a task.
     *
     * @param task Task that was added.
     */
    public void addTask(Task task) {
        System.out.printf("Successfully added: %s", task.getTaskName());
    }

    /**
     * Prints a message after marking deleting a task.
     *
     * @param task Task that was deleted.
     */
    public void deleteTask(Task task) {
        System.out.printf("Successfully deleted: %s", task.getTaskName());
    }

    /**
     * Prints a message that should be displayed before closing the application.
     */
    public void endPrompt() {
        System.out.println("Goodbye");
    }

    /**
     * Prints a line to divide outputs from the application.
     */
    public void lineDivider() {
        System.out.println("-".repeat(60));
    }

    /**
     * Prints a success message when a file is successfully loaded.
     */
    public void fileSuccessfullyLoaded() {
        System.out.println("File successfully loaded!");
    }

    /**
     * Prints an error message with its message.
     *
     * @param message Message to be displayed
     */
    public void showError(String message) {
        System.out.println(message);
    }
}
