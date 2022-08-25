package duke;

import java.util.Scanner;

/**
 * Ui deals with interactions with the user.
 */
public class Ui {

    Scanner sc = new Scanner(System.in);
    private final String BORDER = "    ============================================================";
    private final String INDENT = "     ";

    /**
     * Shows the borders/outlines for Duke's messages.
     */
    public void showLine() {
        System.out.println(BORDER);
    }

    /**
     * Shows Duke's welcome message.
     */
    public void showWelcome() {
        System.out.println(BORDER);
        String content;
        String logo = "      ____        _        \n"
                + "     |  _ \\ _   _| | _____ \n"
                + "     | | | | | | | |/ / _ \\\n"
                + "     | |_| | |_| |   <  __/\n"
                + "     |____/ \\__,_|_|\\_\\___|\n";
        content = logo + "\n" + INDENT + "Hello! I'm Duke\n" + INDENT + "What can I do for you?\n";
        System.out.print(content);
        System.out.println(BORDER);
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
     * Shows the error when no past task data is found.
     */
    public void showLoadingError() {
        showError("Task data not found.");
    }

    /**
     * Shows the message when a task is added.
     *
     * @param size The size of the TaskList.
     * @param desc The description of the task.
     */
    public void showAddTask(int size, String desc) {
        String plural = size > 1 ? "s" : "";
        showMessage("Got it. I've added this task:");
        showMessage("  " + desc);
        showMessage("Now you have " + size + " task" + plural  + " in the list.");
    }

    /**
     * Shows an error message with an indentation.
     *
     * @param errMessage The error message to be printed.
     */
    public void showError(String errMessage) {
        System.out.println(INDENT + errMessage);
    }

    /**
     * Shows the closing message when user finishes using the program.
     */
    public void showBye() {
        System.out.println(INDENT + "Bye. Hope to see you again soon!");
        sc.close();
    }

    /**
     * Shows a message with an indentation.
     *
     * @param message The message to be printed.
     */
    public void showMessage(String message) {
        System.out.println(INDENT + message);
    }

}
