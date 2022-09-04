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
     * Returns a string with the Duke startup welcome message.
     *
     * @return The Duke welcome message string with a border.
     */
    public static String getWelcomeMessage() {
        final String WELCOME_MSG = "Hello! I'm duke.\n" + "What can I do for you?";
        return getMessage(WELCOME_MSG);
    }

    /**
     * Returns a string containing the Duke exit message.
     *
     * @return The Duke exit String with a border.
     */
    public String getExitMessage() {
        final String EXIT_MSG = "Bye. Hope to see you again soon!";
        return getMessage(EXIT_MSG);
    }

    /**
     * Returns a string containing the list of tasks stored in Duke.
     *
     * @param tasks A list of tasks stored.
     * @return A String detailing the list of tasks stored with a border.
     */
    public String listTasks(ArrayList<Task> tasks) {
        final String REPLY = "Here are the tasks in your list:";
        return getMessage(enumerateTasks(tasks, REPLY));
    }

    /**
     * Returns a string containing the list of tasks that matches a certain requirement in Duke.
     *
     * @param tasks A list of tasks stored that matches a certain requirement.
     * @return A String detailing the list of tasks that matches a certain requirement with a border.
     */
    public String listFoundTasks(ArrayList<Task> tasks) {
        final String REPLY = "Here are the matching tasks in your list:";
        return getMessage(enumerateTasks(tasks, REPLY));
    }

    private String enumerateTasks(ArrayList<Task> tasks, String str) {
        int pointer = 1;
        String returnedString = str;
        for (Task task : tasks) {
            returnedString += "\n" + pointer + "." + task;
            pointer++;
        }
        return returnedString;
    }

    /**
     * Returns a string containing an acknowledgment message that a task has been marked.
     *
     * @param task The task that is marked.
     * @return A string acknowledging the marked task with a border.
     */
    public String getMarkedTask(Task task) {
        final String ACKNOWLEDGE_TEXT_MARKED = "Nice! I've marked this task as done:\n";
        return getMessage(ACKNOWLEDGE_TEXT_MARKED + task);
    }

    /**
     * Returns a string with an acknowledgment message that a task has been unmarked.
     *
     * @param task The task that is unmarked.
     * @return A string acknowledging the unmarked task with a border.
     */
    public String getUnmarkedTask(Task task) {
        final String ACKNOWLEDGE_TEXT_UNMARKED = "OK, I've marked this task as not done yet:\n";
        return getMessage(ACKNOWLEDGE_TEXT_UNMARKED + task);
    }

    private String getTaskCountReply(int count) {
        final String REPLY = "Now you have " + count + " task(s) in the list.";
        return REPLY;
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
        final String MSG = "Got it. I've added this task:\n"
                + task + "\n"
                + getTaskCountReply(tasksCountLeft);
        return getMessage(MSG);
    }

    /**
     * Returns a String with an error message caused by usage of a forbidden character/string.
     *
     * @param s The forbidden String.
     * @return A String containing an error message with a border.
     */
    public String getBannedCharacterInputResponse(String s) {
        final String RESPONSE = "Woah there!!! Your input contains a \"" + s
                + "\" character which is not allowed!!";
        return getMessage(RESPONSE);
    }

    /**
     * Returns a String with an error message caused by invalid input.
     *
     * @return A String containing an error message with a border.
     */
    public String getInvalidInputResponse() {
        final String RESPONSE = "OOPS!!! I'm sorry, but I don't know what that means :-(";
        return getMessage(RESPONSE);
    }

    /**
     * Returns a string containing an error message caused by an input command missing some key information.
     *
     * @param s The input command.
     * @return A String containing an error message with a border.
     */
    public String getMissingInputResponse(String s) {
        final String RESPONSE = "OOPS!!! The description after a \""
                + s + "\" is missing or incomplete!!";
        return getMessage(RESPONSE);
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
        final String RESPONSE = "OOPS!!! You tried to " + cmd + " task " + inputNum
                + " but it doesn't exist in the list!";
        return getMessage(RESPONSE);
    }

    /**
     * Prints an error message caused by having date input that is unable to be parsed by the program.
     *
     * @return A String containing an error message with a border.
     */
    public String getDateTimeErrorResponse() {
        final String RESPONSE = "OOPS!!! I can't recognise the date you just inputted :-(\n"
                + "Dates should be inputted in a 'YYYY-MM-DD HH:MM' format, and events should have 2 dates.";
        return getMessage(RESPONSE);
    }
}
