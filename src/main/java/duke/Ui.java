package duke;

import java.util.Scanner;

/**
 * Ui deals with interactions with the user.
 */
public class Ui {

    private Scanner sc = new Scanner(System.in);

    /**
     * Shows the message when a task is added.
     *
     * @param size The size of the TaskList.
     * @param desc The description of the task.
     */
    public String showAddTask(int size, String desc) {
        String plural = size > 1 ? "s" : "";
        String output = "Got it. I've added this task:" + System.lineSeparator();
        output += "  " + desc + System.lineSeparator();
        output += "Now you have " + size + " task" + plural + " in the list.";
        return output;
    }

    /**
     * Shows an error message with an indentation.
     *
     * @param errMessage The error message to be printed.
     */
    public String showError(String errMessage) {
        return errMessage;
    }

    /**
     * Shows the closing message when user finishes using the program.
     */
    public String showBye() {
        sc.close();
        return "Bye. Hope to see you again soon!";
    }

}
