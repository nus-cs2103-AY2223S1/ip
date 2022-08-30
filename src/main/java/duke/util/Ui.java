package duke.util;

import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Class to manage user interface input and output.
 */
public class Ui {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String LINE_DIVIDER = "    ____________________________________________________________";
    private static final String INDENTATION = "     ";
    private static Scanner in;

    /**
     * Constructor for {@code Ui}.
     */
    public Ui() {
        Ui.in = new Scanner(System.in);
    }

    /**
     * Returns the system input scanner.
     *
     * @return {@code Scanner} for system input.
     */
    public static Scanner getInputScanner() {
        return Ui.in;
    }

    /**
     * Prints greeting message.
     */
    public void showWelcome() {
        System.out.println(LOGO);
        System.out.println("Hello! I'm Duke, What can I do for you?");
    }

    /**
     * Prints error message from {@code Exception}.
     *
     * @param e An {@code Exception}.
     */
    public void showError(Exception e) {
        System.out.println(LINE_DIVIDER);
        String[] lines = e.getMessage().split(System.lineSeparator());
        for (String line : lines) {
            System.out.println(INDENTATION + line);
        }
        System.out.println(LINE_DIVIDER);
    }

    /**
     * Prints messages after formatting.
     *
     * @param messages Message strings.
     */
    public void printMessages(String... messages) {
        System.out.println(LINE_DIVIDER);
        for (String message : messages) {
            System.out.println(INDENTATION + message);
        }
        System.out.println(LINE_DIVIDER);
    }


    /**
     * Prints message informing user that something in {@code TaskList} has changed.
     *
     * @param message Message string.
     * @param task {@code Task} affected.
     * @param taskList {@code TaskList} affected.
     */
    public void printTaskListChange(String message, Task task, TaskList taskList) {
        printMessages(new String[] {
            message,
            task.toString(),
            String.format("Now you have %d tasks in the list.", taskList.size())
        });
    }
}
