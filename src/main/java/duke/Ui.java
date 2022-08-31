package duke;

import duke.task.Task;
import duke.task.TaskList;

public class Ui {
    private static final String logo =
            "$$\\                                     $$\\\n" +
                    "$$ |                                    $$ |\n" +
                    "$$ |      $$\\   $$\\  $$$$$$\\   $$$$$$$\\ $$$$$$$\\\n" +
                    "$$ |      $$ |  $$ |$$  __$$\\ $$  _____|$$  __$$\\\n" +
                    "$$ |      $$ |  $$ |$$ |  \\__|$$ /      $$ |  $$ |\n" +
                    "$$ |      $$ |  $$ |$$ |      $$ |      $$ |  $$ |\n" +
                    "$$$$$$$$\\ \\$$$$$$  |$$ |      \\$$$$$$$\\ $$ |  $$ |\n" +
                    "\\________| \\______/ \\__|       \\_______|\\__|  \\__|\n\n";
    private static final char lineBreak = '\n';
    private static final String line = "---------------------------------------------------";
    private static final String indent = "      ";

    private static final String greetingMessage = "Hi, I'm Lurch." + lineBreak + "You rang?";
    private static final String noTasksMessage = "You have no tasks in the list right now";
    private static final String byeMessage = "Have a lovely day with dark and cloudy skies.";
    private static final String oopsMessage = "Oh bother!";

    /**
     * Logs the formatted message in the console.
     *
     * @param msg Raw message string to format and print.
     */
    private static void message(String msg) {
        final String indentedLine = indent + line;
        final String indentedMessage = indent
                + msg.replace(Character.toString(lineBreak), lineBreak + indent);
        System.out.println(indentedLine + lineBreak + indentedMessage + lineBreak + indentedLine);
    }

    /**
     * Prints logo and greeting line
     */
    public static void showGreeting() {
        System.out.println(logo);
        message(greetingMessage);
    }

    /**
     * Prints information about a task with a prefix.
     *
     * @param prefix The message to display before the task.
     * @param task The task object whose status will be shown.
     */
    public static void showTaskStatus(String prefix, Task task) {
        message(prefix + lineBreak + indent + task);
    }

    /**
     * Prints information about a task with a prefix and a suffix.
     *
     * @param prefix The message to display before the task.
     * @param task The task object whose status will be shown.
     * @param suffix The message to display after the task.
     */
    public static void showTaskStatus(String prefix, Task task, String suffix) {
        message(prefix + lineBreak + indent + task + lineBreak + suffix);
    }

    /**
     * Prints the task list sequentially.
     *
     * @param taskList TaskList object containing tasks
     * @throws DukeException If taskList has no tasks
     */
    public static void showTasksList(TaskList taskList) throws DukeException {
        String msg = "";
        if (taskList.getSize() == 0) {
            throw new DukeException(noTasksMessage);
        }
        for (int i = 0; i < taskList.getSize(); i++) {
            msg += i + 1 + ". " + taskList.get(i);
            if (i < taskList.getSize() - 1)  {
                msg += lineBreak;
            }
        }
        message(msg);
    }

    /**
     * Prints goodbye message
     */
    public static void showTermination() {
        message(byeMessage);
    }

    /**
     * Prints formatted error message.
     *
     * @param exc Exception object
     */
    public static void showErrorMessage(DukeException exc) {
        message(oopsMessage + lineBreak + exc);
    }

    public static String getGreetingMessage() {
        return greetingMessage;
    }

    public static String getTaskStatusString(String prefix, Task task) {
        return prefix + lineBreak + indent + task;
    }

    public static String getTaskListString(TaskList taskList) throws DukeException {
        String msg = "";
        if (taskList.getSize() == 0) {
            throw new DukeException(noTasksMessage);
        }
        for (int i = 0; i < taskList.getSize(); i++) {
            msg += i + 1 + ". " + taskList.get(i);
            if (i < taskList.getSize() - 1)  {
                msg += lineBreak;
            }
        }
        return msg;
    }

    public static String getTerminationString() {
        return byeMessage;
    }

    public static String getErrorMessageString(DukeException exc) {
        return oopsMessage + lineBreak + exc;
    }
}
