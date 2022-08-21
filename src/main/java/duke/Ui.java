package duke;

import java.util.ArrayList;

/**
 * Encapsulates all the interactions between the Duke interface and the user.
 */
public class Ui {
    /**
     * Prints an inputted message on the Duke interface with a border.
     *
     * @param msg A String to be printed.
     */
    public void printMessage(String msg) {
        String border = "____________________________________________________________\n";
        System.out.println(border + msg + "\n" + border);
    }

    /**
     * Prints the Duke startup message.
     */
    public void printStartupMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String startupMsg = "Hello! I'm duke.Duke\n" + "What can I do for you?";
        printMessage(startupMsg);
    }

    /**
     * Prints the Duke exit message.
     */
    public void printExitMessage() {
        String exitMsg = "Bye. Hope to see you again soon!";
        printMessage(exitMsg);
    }

    /**
     * Prints the list of tasks stored in Duke.
     *
     * @param tasks A list of tasks stored.
     */
    public void listTasks(ArrayList<Task> tasks) {
        int pointer = 1;
        String reply = "Here are the tasks in your list:";
        for (Task task : tasks) {
            reply += "\n" + pointer + "." + task;
            pointer++;
        }
        printMessage(reply);
    }

    /**
     * Prints an acknowledgment message that a task has been marked.
     *
     * @param task The task that is marked.
     */
    public void printMarkedTask(Task task) {
        printMessage("Nice! I've marked this task as done:\n" + task);
    }

    /**
     * Prints an acknowledgment message that a task has been unmarked.
     *
     * @param task The task that is unmarked.
     */
    public void printUnmarkedTask(Task task) {
        printMessage("OK, I've marked this task as not done yet:\n" + task);
    }

    private String getTaskCountReply(int count) {
        return "Now you have " + count + " task(s) in the list.";
    }

    /**
     * Prints an acknowledgment message that a task has been removed.
     *
     * @param removedTask The task that is removed.
     * @param tasksCountLeft The number of tasks left after the task is removed.
     */
    public void printRemovedTask(Task removedTask, int tasksCountLeft) {
        String msg = "Noted. I've removed this task:\n"
                + removedTask + "\n"
                + getTaskCountReply(tasksCountLeft);
        printMessage(msg);
    }

    /**
     * Prints an acknowledgment message that a task has been added.
     *
     * @param task The task that is added.
     * @param tasksCountLeft The number of tasks present after the task is added.
     */
    public void printAddedTask(Task task, int tasksCountLeft) {
        String msg = "Got it. I've added this task:\n"
                + task + "\n"
                + getTaskCountReply(tasksCountLeft);
        printMessage(msg);
    }

    /**
     * Prints an error message caused by usage of a forbidden character/string.
     *
     * @param s The forbidden String.
     */
    public void printBannedCharacterInputResponse(String s) {
        printMessage("☹ Woah there!!! Your input contains a \"" + s
                + "\" character which is not allowed!!");
    }

    /**
     * Prints an error message caused by invalid input.
     */
    public void printInvalidInputResponse() {
        printMessage("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Prints an error message caused by an input command missing some key information.
     *
     * @param s The input command.
     */
    public void printMissingInputResponse(String s) {
        printMessage("☹ OOPS!!! The description after a \""
                + s + "\" is missing or incomplete!!");
    }

    /**
     * Prints an error message caused by having a number input that exceeds the number of tasks present in the program.
     *
     * @param cmd The attempted command.
     * @param inputNum The inputted number.
     */
    public void printInputIndexOutOfBoundsResponse(String cmd, String inputNum) {
        printMessage("☹ OOPS!!! You tried to " + cmd + " task " + inputNum
                + " but it doesn't exist in the list!");
    }

    /**
     * Prints an error message caused by having date input that is unable to be parsed by the program.
     */
    public void printDateTimeErrorResponse() {
        printMessage("☹ OOPS!!! I can't recognise the date you just inputted :-(\n"
                + "Dates should be inputted in a 'YYYY-MM-DD HH:MM' format, and events should have 2 dates.");
    }
}
