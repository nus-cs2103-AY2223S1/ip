package duke;

import java.util.ArrayList;

/**
 * Encapsulates all the interactions between the Duke interface and the user.
 */
public class Ui {
    public static final String BORDER = "______________________________________________________________";

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
        String welcomeMsg = "Hello! I'm duke.\n"
                + "What can I do for you?\n"
                + "If you need help, type 'help'.";
        return getMessage(welcomeMsg);
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

    public String getHelpMessage() {
        String whitespace = "\n\n";
        String helpMsg = "Here are the commands you can use (shortcuts in brackets):" + whitespace
                + "bye(b) - Quit App" + whitespace
                + "list(l) - Get the list of tasks" + whitespace
                + "help(h) - Get to this help screen" + whitespace
                + "find(f) [text] - Search for tasks with the input text" + whitespace
                + "mark(m) [number] - Mark a task as done" + whitespace
                + "unmark(um) [number] - Mark a task as undone" + whitespace
                + "delete(del) [number] - Delete a task" + whitespace
                + "todo(t) [task] - Add a todo task" + whitespace
                + "deadline(d) [task] /by [YYYY-MM-DD] [HH:MM]"
                + " - Add a deadline task with an end time" + whitespace
                + "event(e) [task] /at [YYYY-MM-DD] [HH:MM] [YYYY-MM-DD] [HH:MM]"
                + " - Add an event task with a start and end time.";
        return getMessage(helpMsg);
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
        String acknowledgeTextMarked = "Nice! I've marked this task as done:\n";
        return getMessage(acknowledgeTextMarked + task);
    }

    /**
     * Returns a string with an acknowledgment message that a task has been unmarked.
     *
     * @param task The task that is unmarked.
     * @return A string acknowledging the unmarked task with a border.
     */
    public String getUnmarkedTask(Task task) {
        String acknowledgeTextUnmarked = "OK, I've marked this task as not done yet:\n";
        return getMessage(acknowledgeTextUnmarked + task);
    }

    private String getTaskCountReply(int count) {
        String reply = "Now you have " + count + " task(s) in the list.";
        return reply;
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
        String response = "Woah there!!! Your input contains a \"" + s
                + "\" character which is not allowed!!";
        return getMessage(response);
    }

    /**
     * Returns a String with an error message caused by invalid input.
     *
     * @return A String containing an error message with a border.
     */
    public String getInvalidInputResponse() {
        String response = "OOPS!!! I'm sorry, but I don't know what that means :-(";
        return getMessage(response);
    }

    /**
     * Returns a string containing an error message caused by an input command missing some key information.
     *
     * @param s The input command.
     * @return A String containing an error message with a border.
     */
    public String getMissingInputResponse(String s) {
        String response = "OOPS!!! The description after a \""
                + s + "\" is missing or incomplete!!";
        return getMessage(response);
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
        String response = "OOPS!!! You tried to " + cmd + " task " + inputNum
                + " but it doesn't exist in the list!";
        return getMessage(response);
    }

    public String getBadNumberFormatResponse() {
        String response = "OOPS!!! I don't think you've typed in a proper number!!!!";
        return getMessage(response);
    }

    /**
     * Prints an error message caused by having date input that is unable to be parsed by the program.
     *
     * @return A String containing an error message with a border.
     */
    public String getDateTimeErrorResponse() {
        String response = "OOPS!!! I can't recognise the date you just inputted :-(\n"
                + "Dates should be inputted in a 'YYYY-MM-DD HH:MM' format, and events should have 2 dates.";
        return getMessage(response);
    }
}
