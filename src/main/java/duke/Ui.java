package duke;

import java.util.Scanner;

public class Ui {

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
    public static String printInitialMessage() {
        return "Hello! I'm Duke\nWhat can I do for you?\n";
    }

    /**
     * Prints goodbye message.
     */
    public static String printGoodbyeMessage() {
        return "Bye. Hope to see you again soon!\n";
    }

    /**
     * Prints the task added message.
     *
     * @param newTask The task added by the user.
     * @param size Total number of tasks.
     */
    public static String printAddedMessage(Task newTask, int size) {
        String message = String.format("\tGot it. I've added this task:\n\t  %s\n", newTask)
                + String.format("\tNow you have %s tasks in the list\n", size);
        return message;
    }

    /**
     * Prints the error message.
     *
     * @param e The exception returned by Duke.
     */
    public static String printErrorMessage(DukeException e) {
        String errorMessage = e.getMessage() + "\n";
        return errorMessage;
    }

    /**
     * Prints the deleted task message.
     *
     * @param deletedTask The deleted task.
     * @param size Total number of tasks.
     */
    public static String printDeletedMessage(Task deletedTask, int size) {
        String message = "Noted. I've removed this task:\n"
                + deletedTask
                + String.format("\nNow you have %s tasks in the list.\n", size);
        return message;
    }

    /**
     * List out all tasks.
     *
     * @param tasks The tasklist.
     */
    public static String printTasks(TaskList tasks) {
        String tasksString = "";
        for (String task : tasks.convertToStringList()) {
            tasksString += task + "\n";
        }
        return tasksString;
    }

    public static String printMarkMessage(String task) {
        return "Mark " + task + " as done";
    }

    public static String printUnmarkMessage(String task) {
        return "Already unmark " + task;
    }
}
