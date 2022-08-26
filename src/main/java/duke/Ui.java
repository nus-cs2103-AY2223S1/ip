package duke;

import java.util.Scanner;

public class Ui {

    private static String DIVIDER = "__________________________________________________________\n";
    private Scanner in;

    public Ui() {
        in = new Scanner(System.in);
    }

    /**
     * Reads command from the user.
     *
     * @return the command parsed in by the user.
     */
    public String readCommand() {
        return in.nextLine();
    }

    /**
     * Prints initial message.
     */
    public static void printInitialMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.print(logo + "Hello! I'm Yale\nWhat can I do for you?\n");
    }

    /**
     * Prints goodbye message.
     */
    public static void printGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    /**
     * Prints the task added message.
     *
     * @param newTask The task added by the user.
     * @param size Total number of tasks.
     */
    public static void printAddedMessage(Task newTask, int size) {
        String message = DIVIDER
                + String.format("\tGot it. I've added this task:\n\t  %s\n", newTask)
                + String.format("\tNow you have %s tasks in the list\n", size)
                + DIVIDER;
        System.out.print(message);
    }

    /**
     * Prints the error message.
     *
     * @param e The exception returned by Duke.
     */
    public static void printErrorMessage(DukeException e) {
        String errorMessage = DIVIDER + e.getMessage() + "\n" + DIVIDER;
        System.out.println(errorMessage);
    }

    /**
     * Prints the deleted task message.
     *
     * @param deletedTask The deleted task.
     * @param size Total number of tasks.
     */
    public static void printDeletedMessage(Task deletedTask, int size) {
        String message = DIVIDER + "Noted. I've removed this task:\n"
                + deletedTask
                + String.format("\nNow you have %s tasks in the list.\n", size) + DIVIDER;
        System.out.println(message);
    }

    /**
     * List out all tasks.
     *
     * @param tasks The tasklist.
     */
    public static void printTasks(TaskList tasks) {
        for (String task : tasks.convertToStringList()) {
            System.out.println(task);
        }
    }

    public static void printMarkMessage() {

    }
}
