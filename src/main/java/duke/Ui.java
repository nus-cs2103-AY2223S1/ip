package duke;

import java.util.Scanner;

public class Ui {

    private static String DIVIDER = "__________________________________________________________\n";
    private Scanner in;

    public Ui() {
        in = new Scanner(System.in);
    }

    public String readCommand() {
        return in.nextLine();
    }

    public static void printInitialMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.print(logo + "Hello! I'm Yale\nWhat can I do for you?\n");
    }

    public static void printGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    public static void printAddedMessage(Task newTask, int size) {
        String message = DIVIDER
                + String.format("\tGot it. I've added this task:\n\t  %s\n", newTask)
                + String.format("\tNow you have %s tasks in the list\n", size)
                + DIVIDER;
        System.out.print(message);
    }

    public static void printErrorMessage(DukeException e) {
        String errorMessage = DIVIDER + e.getMessage() + "\n" + DIVIDER;
        System.out.println(errorMessage);
    }

    public static void printDeletedMessage(Task deletedTask, int size) {
        String message = DIVIDER + "Noted. I've removed this task:\n"
                + deletedTask
                + String.format("\nNow you have %s tasks in the list.\n", size) + DIVIDER;
        System.out.println(message);
    }

    public static void printTasks(TaskList tasks) {
        for (String task : tasks.convertToStringList()) {
            System.out.println(task);
        }
    }

    public static void printMarkMessage() {

    }
}
