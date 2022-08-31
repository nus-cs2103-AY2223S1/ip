package duke;

import java.util.Scanner;

/**
 * Ui deals with interactions with the user.
 */
public class Ui {

    private static final String BORDER = "    ============================================================";
    private static final String INDENT = "     ";
    private Scanner sc = new Scanner(System.in);

    /**
     * Shows the borders/outlines for Duke's messages.
     */
    public void showLine() {
        System.out.println(BORDER);
    }

    /**
     * Shows Duke's welcome message.
     */
    public String showWelcome() {
        String content;
        String logo = "      ______  ______  _    _  ______\n"
                + "     |  ____||  ____|| |  | ||  __  |\n"
                + "     | |____ | |     | |__| || |  | |\n"
                + "     |  ____|| |     |  __  || |  | |\n"
                + "     | |____ | |____ | |  | || |__| |\n"
                + "     |______||______||_|  |_||______|\n";
        content = logo + System.lineSeparator() + "Hello! I'm Echo" + System.lineSeparator()
                + "What can I do for you?";
        return content;
    }

    /**
     * Reads user's full input.
     *
     * @return The command inputted by the user.
     */
    public String readCommand() {
        return sc.nextLine();
    }

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
