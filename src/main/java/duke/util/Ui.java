package duke.util;

import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskList;

public class Ui {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String LINE_DIVIDER = "    ____________________________________________________________";
    private static final String INDENTATION = "     ";
    private static Scanner in;

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

    public void showWelcome() {
        System.out.println(LOGO);
        System.out.println("Hello! I'm Duke, What can I do for you?");
    }

    public void showError(Exception e) {
        System.out.println(LINE_DIVIDER);
        String[] lines = e.getMessage().split(System.lineSeparator());
        for (String line : lines) {
            System.out.println(INDENTATION + line);
        }
        System.out.println(LINE_DIVIDER);
    }

    public void printMessages(String[] messages) {
        System.out.println(LINE_DIVIDER);
        for (String message : messages) {
            System.out.println(INDENTATION + message);
        }
        System.out.println(LINE_DIVIDER);
    }

    public void printTaskListChange(String message, Task task, TaskList taskList) {
        printMessages(new String[] {
            message,
            task.toString(),
            String.format("Now you have %d tasks in the list.", taskList.size())
        });
    }
}
