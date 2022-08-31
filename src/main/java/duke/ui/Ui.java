package duke.ui;

import java.util.Scanner;

import duke.command.Command;
import duke.task.Task;

/**
 * Represents a User interface class
 *
 * @author Khor Jun Wei
 * @version CS2103T AY22/23 Sem 1
 */
public class Ui {

    /**
     * Represents an indentation for replies.
     */
    private static final String INDENTATION = "     ";

    /**
     * Represents an extra indentation for replies.
     */
    private static final String EXTRA_INDENTATION = "  ";

    /**
     * Represents a scanner.
     */
    private final Scanner scanner;

    /**
     * Constructs a UI class through a constructor
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Formats given string message.
     * @param s String message
     * @return formatted string message
     */
    private String formatMessage(String s) {
        return INDENTATION + s;
    }

    /**
     * Greets the user.
     */
    public void showWelcome() {
        showLine();
        System.out.println(formatMessage("Hello! I'm Duke\n"
                + "     What can I do for you?"));
        showLine();
    }

    /**
     * Says bye to the user.
     */
    public String sayBye() {
        String msg = formatMessage("Bye. Hope to see you again soon!");
        System.out.println(msg);
        return msg;
    }

    /**
     * Reads the command given by the user.
     * @return String representing the command
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Shows a line as part of text formatting.
     */
    public void showLine() {
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Shows error in a formatted way.
     * @param s error message
     */
    public void showError(String s) {
        System.out.println(formatMessage(s));
    }

    /**
     * Sends a message to user.
     * @param keyword Type of command
     * @param task a task for the message, if required
     * @param numOfTasks number of tasks in task list, if required
     * @param message additional details for the message, if required
     */
    public String sendAndReturnMessage(Command.ActionKeywords keyword, Task task, int numOfTasks, String message) {
        if (keyword == Command.ActionKeywords.DEADLINE || keyword == Command.ActionKeywords.TODO
                || keyword == Command.ActionKeywords.EVENT) {
            String msg = formatMessage("Got it. I've added this task:\n"
                    + INDENTATION + EXTRA_INDENTATION + task + "\n"
                    + INDENTATION + "Now you have " + numOfTasks
                    + (numOfTasks < 2 ? " task" : " tasks") + " in the list.");
            System.out.println(msg);
            return msg;
        } else if (keyword == Command.ActionKeywords.DELETE) {
            String msg = formatMessage("Noted. I've removed the task:\n"
                    + INDENTATION + EXTRA_INDENTATION + task + "\n"
                    + INDENTATION + "Now you have " + numOfTasks
                    + (numOfTasks < 2 ? " task" : " tasks") + " in the list.");
            System.out.println(msg);
            return msg;
        } else if (keyword == Command.ActionKeywords.LIST) {
            if (message.equals("")) {
                String msg = formatMessage("There are currently no tasks in your list");
                System.out.println(msg);
                return msg;
            } else {
                String msg = formatMessage("Here are the task(s) in your list:\n"
                        + INDENTATION + message);
                System.out.println(msg);
                return msg;
            }
        } else if (keyword == Command.ActionKeywords.MARK) {
            String msg = formatMessage("Nice! I've marked this task as done:\n"
                    + INDENTATION + EXTRA_INDENTATION + task);
            System.out.println(msg);
            return msg;
        } else if (keyword == Command.ActionKeywords.UNMARK) {
            String msg = formatMessage("OK, I've marked this task as not done yet:\n"
                    + INDENTATION + EXTRA_INDENTATION + task);
            System.out.println(msg);
            return msg;
        } else if (keyword == Command.ActionKeywords.FIND) {
            if (message.equals("")) {
                String msg = formatMessage("Sorry, there are matching tasks in your list");
                System.out.println(msg);
                return msg;
            } else {
                String msg = formatMessage("Here are the matching tasks in your list:\n"
                        + INDENTATION + message);
                System.out.println(msg);
                return msg;
            }
        } else {
            return null;
        }
    }
}
