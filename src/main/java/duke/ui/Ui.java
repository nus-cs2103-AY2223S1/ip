package duke.ui;

import duke.task.TaskList;

/**
 * UI of the application Inspired by AddressBook
 */
public class Ui {

    /**
     * Initialises Ui.
     */
    public Ui() {
    }

    /**
     * Returns a greeting to the user.
     *
     * @return helloMessage
     */
    public static String helloMessage() {
        return "Hello. This is Primer.";
    }

    /**
     * Returns an exit message to the user.
     *
     * @return exitMessage
     */
    public static String exitMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints a message.
     *
     * @param strs vaars of String.
     * @return message
     */
    public static String printMessage(String... strs) {
        StringBuilder sb = new StringBuilder();
        sb.append("_______________________________________________________");
        for (String str : strs) {
            sb.append("\n\t");
            sb.append(str);
            sb.append("\n");
        }
        sb.append("_______________________________________________________");
        return sb.toString();
    }

    /**
     * Wraps a message for printing after a new task is added.
     *
     * @param str
     * @param taskDescription
     * @param taskList
     * @return message
     */
    public static String wrapMessage(String str, String taskDescription, TaskList taskList) {
        return String.format(
                str + "\n\t\t" + taskDescription + "\n\tNow you have " + taskList.size() + " tasks in the list.");
    }
}
