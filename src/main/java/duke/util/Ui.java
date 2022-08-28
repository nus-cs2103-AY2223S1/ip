package duke.util;

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
    private static final String INDENTATION = "     ";

    /**
     * Prints greeting message.
     */
    public static String showWelcome() {
        return LOGO + "Hello! I'm Duke, What can I do for you?" + System.lineSeparator();
    }

    /**
     * Prints error message from {@code Exception}.
     *
     * @param e An {@code Exception}.
     */
    public static String showError(Exception e) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] lines = e.getMessage().split(System.lineSeparator());
        for (String line : lines) {
            stringBuilder.append(INDENTATION + line);
        }
        return stringBuilder.toString();
    }

    /**
     * Prints messages after formatting.
     *
     * @param messages Message strings.
     */
    public static String formatMessages(String[] messages) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String message : messages) {
            stringBuilder.append(INDENTATION + message);
        }
        return stringBuilder.toString();
    }

    /**
     * Prints message informing user that something in {@code TaskList} has changed.
     *
     * @param message Message string.
     * @param task {@code Task} affected.
     * @param taskList {@code TaskList} affected.
     */
    public static String formatTaskListChangeMessage(String message, Task task, TaskList taskList) {
        return formatMessages(new String[] {
            message,
            task.toString(),
            String.format("Now you have %d tasks in the list.", taskList.size())
        });
    }
}
