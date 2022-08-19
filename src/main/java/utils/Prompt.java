package utils;

import enums.Command;
import enums.SecondaryCommand;
import task.Task;

public class Prompt {

    public static void startPrompt() {
        System.out.println("Hi from Yi Xian");
        listValidInstructions();
        lineDivider();
    }

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

    public static void markDone(String taskName) {
        System.out.println("Nice! I have marked (" + taskName + ") as done!");
    }

    public static void markUndone(String taskName) {
        System.out.println("Nice! I have marked (" + taskName + ") as undone!");
    }

    public static void addTask(Task task) {
        System.out.printf("Successfully added: %s", task.getTaskName());
    }

    public static void deleteTask(Task task) {
        System.out.printf("Successfully deleted: %s", task.getTaskName());
    }

    public static void endPrompt() {
        System.out.println("Goodbye");
    }

    public static void lineDivider() {
        System.out.println("-------------------------------------------------------------");
    }
}
