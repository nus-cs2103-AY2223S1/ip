package duke;

import java.util.ArrayList;

/**
 * Encapsulates all the interactions between the Duke interface and the user.
 */
public class Ui {
    public static String BORDER = "_________________________________________________";
    /**
     * Returns a string with the inputted message on the Duke interface.
     *
     * @param msg A String to be printed.
     * @return The string with a border.
     */
    public static String getMessage(String msg) {
        return BORDER + "\n" + msg + "\n" + BORDER;
    }

    /**
     * Returns a string containing the Duke exit message.
     *
     * @return The Duke exit String with a border.
     */
    public String getExitMessage() {
        String exitMsg = "Bye. Hope to see you again soon!";
        return getMessage(exitMsg);
    }

    /**
     * Returns a string containing the list of tasks stored in Duke.
     *
     * @param tasks A list of tasks stored.
     * @return A String detailing the list of tasks stored with a border.
     */
    public String listTasks(ArrayList<Task> tasks) {
        String reply = "Here are the tasks in your list:";
        return getMessage(enumerateTasks(tasks, reply));
    }

    /**
     * Returns a string containing the list of tasks that matches a certain requirement in Duke.
     *
     * @param tasks A list of tasks stored that matches a certain requirement.
     * @return A String detailing the list of tasks that matches a certain requirement with a border.
     */
    public String listFoundTasks(ArrayList<Task> tasks) {
        String reply = "Here are the matching tasks in your list:";
        return getMessage(enumerateTasks(tasks, reply));
    }

    private String enumerateTasks(ArrayList<Task> tasks, String str) {
        int pointer = 1;
        for (Task task : tasks) {
            str += "\n" + pointer + "." + task;
            pointer++;
        }
        return str;
    }

    /**
     * Returns a string containing an acknowledgment message that a task has been marked.
     *
     * @param task The task that is marked.
     * @return A string acknowledging the marked task with a border.
     */
    public String getMarkedTask(Task task) {
        return getMessage("Nice! I've marked this task as done:\n" + task);
    }

    /**
     * Returns a string with an acknowledgment message that a task has been unmarked.
     *
     * @param task The task that is unmarked.
     * @return A string acknowledging the unmarked task with a border.
     */
    public String getUnmarkedTask(Task task) {
        return getMessage("OK, I've marked this task as not done yet:\n" + task);
    }

    private String getTaskCountReply(int count) {
        return "Now you have " + count + " task(s) in the list.";
    }

    /**
     * Returns a string with an acknowledgment message that a task has been removed and the number of tasks left.
     *
     * @param removedTask The task that is removed.
     * @param tasksCountLeft The number of tasks left after the task is removed.
     * @return A string acknowledging the removed task along with the number of tasks remaining with a border.
     */
    public String getRemovedTask(Task removedTask, int tasksCountLeft) {
        String msg = "Noted. I've removed this task:\n"
                + removedTask + "\n"
                + getTaskCountReply(tasksCountLeft);
        return getMessage(msg);
    }

    /**
     * Returns a string with an acknowledgment message that a task has been added and the number of tasks now.
     *
     * @param task The task that is added.
     * @param tasksCountLeft The number of tasks present after the task is added.
     * @return A String acknowledging the removed task along with the number of tasks left with a border.
     */
    public String getAddedTask(Task task, int tasksCountLeft) {
        String msg = "Got it. I've added this task:\n"
                + task + "\n"
                + getTaskCountReply(tasksCountLeft);
        return getMessage(msg);
    }

    /**
     * Returns a String with an error message caused by usage of a forbidden character/string.
     *
     * @param s The forbidden String.
     * @return A String containing an error message with a border.
     */
    public String getBannedCharacterInputResponse(String s) {
        return getMessage("Woah there!!! Your input contains a \"" + s
                + "\" character which is not allowed!!");
    }

    /**
     * Returns a String with an error message caused by invalid input.
     *
     * @return A String containing an error message with a border.
     */
    public String getInvalidInputResponse() {
        return getMessage("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Returns a string containing an error message caused by an input command missing some key information.
     *
     * @param s The input command.
     * @return A String containing an error message with a border.
     */
    public String getMissingInputResponse(String s) {
        return getMessage("OOPS!!! The description after a \""
                + s + "\" is missing or incomplete!!");
    }

    /**
     * Returns a string containing a error message caused by having a number input that exceeds the number of tasks
     * present in the program.
     *
     * @param cmd The attempted command.
     * @param inputNum The inputted number.
     * @return A String containing an error message with a border.
     */
    public String getInputIndexOutOfBoundsResponse(String cmd, String inputNum) {
        return getMessage("OOPS!!! You tried to " + cmd + " task " + inputNum
                + " but it doesn't exist in the list!");
    }

    /**
     * Prints an error message caused by having date input that is unable to be parsed by the program.
     *
     * @return A String containing an error message with a border.
     */
    public String getDateTimeErrorResponse() {
        return getMessage("OOPS!!! I can't recognise the date you just inputted :-(\n"
                + "Dates should be inputted in a 'YYYY-MM-DD HH:MM' format, and events should have 2 dates.");
    }
}
