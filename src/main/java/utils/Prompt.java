package utils;

import enums.Command;
import enums.SecondaryCommand;
import task.Task;

/**
 * The {@code Prompt} class contains outputs used throughout the application.
 */
public class Prompt {

    /**
     * Prints messages to be seen when the application is started.
     * It contains an introduction before showing a
     * {@link #listValidInstructions() list of valid instructions} and a
     * {@link #lineDivider() line divider }.
     */
    public static void startPrompt() {
        System.out.println("Hi from Yi Xian");
        listValidInstructions();
        lineDivider();
    }

    /**
     * Prints a list of valid instructions that can be executed by the application.
     */
    public static void listValidInstructions() {
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
     * Prints a message after marking a task as done.
     */
    public static void markDone(String taskName) {
        System.out.println("Nice! I have marked (" + taskName + ") as done!");
    }

    /**
     * Prints a message after marking a task as undone.
     */
    public static void markUndone(String taskName) {
        System.out.println("Nice! I have marked (" + taskName + ") as undone!");
    }

    /**
     * Prints a message after marking adding a task.
     */
    public static void addTask(Task task) {
        System.out.printf("Successfully added: %s", task.getTaskName());
    }

    /**
     * Prints a message after marking deleting a task.
     */
    public static void deleteTask(Task task) {
        System.out.printf("Successfully deleted: %s", task.getTaskName());
    }

    /**
     * Prints a message that should be displayed before closing the application.
     */
    public static void endPrompt() {
        System.out.println("Goodbye");
    }

    /**
     * Prints a line to divide outputs from the application.
     */
    public static void lineDivider() {
        System.out.println("-------------------------------------------------------------");
    }
}
