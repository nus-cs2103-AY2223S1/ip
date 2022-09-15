package duke;

import duke.tasks.Task;

/**
 * The Ui class encapsulates Duke responding to a user's input.
 */
public class Ui {
    private static final String noOfTasksString = "Now, you have %d task(s) in the list.";
    private static final String markString = "Nice! I've marked this task as done:\n";
    private static final String unMarkString = "I've unmarked this task as undone:\n";
    public Ui() {}

    /**
     * Prints a message.
     * @param msg Message to be printed.
     */
    public String print(String msg) {
        return msg;
    }

    /**
     * Prints a message together with the number of tasks in the tasklist.
     * @param msg Message to be printed.
     * @param taskList Tasklist which number of tasks in it is to be printed.
     */
    public String print(String msg, TaskList taskList) {
        msg += String.format(noOfTasksString, taskList.length());
        return msg;
    }

    /**
     * Prints a message for mark and unmark commands.
     * @param isMark Whether the message is for a mark or unmark command.
     * @param task Task which string is to be returned
     */
    public String print(boolean isMark, Task task) {
        if (isMark) {
            return markString + task.toString();
        } else {
            return unMarkString + task.toString();
        }
    }
    /**
     * Prints welcome message when a user logs on to Duke.
     */
    public String printWelcomeMsg() {
        String greeting = "Hello! I'm Duke\n";
        greeting += "What can I do for you?";
        return greeting;
    }
}
