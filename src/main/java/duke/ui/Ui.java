package duke.ui;

import java.util.Scanner;

import duke.command.Command;
import duke.task.Task;

/**
 * Represents a User interface class.
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
     * Represents a scanner object in Ui.
     */
    private final Scanner scanner;

    /**
     * Constructs a UI class through a constructor.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Formats given string message.
     * @param s String message.
     * @return Formatted string message.
     */
    private String formatMessage(String s) {
        return INDENTATION + s;
    }

    /**
     * Gets interface's greeting message.
     */
    public String getWelcomeMessage() {
        String msg = formatMessage("Hello! I'm Duke\n"
                + "     What can I do for you?");
        return msg;
    }

    /**
     * Gets interface's bye message.
     */
    public String getByeMessage() {
        String msg = formatMessage("Bye. Hope to see you again soon!");
        return msg;
    }

    /**
     * Reads the command given by the user.
     * @return String representing the command.
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
     * @param s Error message.
     */
    public void showError(String s) {
        System.out.println(formatMessage(s));
    }

    /**
     * Sends a message to user.
     * @param keyword Type of command.
     * @param task Task for the message, if required.
     */
    public String sendAndReturnMessage(Command.ActionKeywords keyword, Task task, String... values) {
        int numOfTasks = 0;
        String message = "";
        if (values.length >= 1) {
            numOfTasks = Integer.parseInt(values[0]);
        }
        if (values.length >= 2) {
            message = values[1];
        }
        switch (keyword) {
        case DEADLINE:
        case TODO:
        case EVENT: {
            String msg = formatMessage("Got it. I've added this task:\n"
                    + INDENTATION + EXTRA_INDENTATION + task + "\n"
                    + INDENTATION + "Now you have " + numOfTasks
                    + (numOfTasks < 2 ? " task" : " tasks") + " in the list.");
            return msg;
        }
        case DELETE: {
            String msg = formatMessage("Noted. I've removed the task:\n"
                    + INDENTATION + EXTRA_INDENTATION + task + "\n"
                    + INDENTATION + "Now you have " + numOfTasks
                    + (numOfTasks < 2 ? " task" : " tasks") + " in the list.");
            return msg;
        }
        case LIST: {
            String msg;
            if (message.equals("")) {
                msg = formatMessage("There are currently no tasks in your list");
            } else {
                msg = formatMessage("Here are the task(s) in your list:\n"
                        + INDENTATION + message);
            }
            return msg;
        }
        case MARK: {
            String msg = formatMessage("Nice! I've marked this task as done:\n"
                    + INDENTATION + EXTRA_INDENTATION + task);
            return msg;
        }
        case UNMARK: {
            String msg = formatMessage("OK, I've marked this task as not done yet:\n"
                    + INDENTATION + EXTRA_INDENTATION + task);
            return msg;
        }
        case FIND: {
            String msg;
            if (message.equals("")) {
                msg = formatMessage("Sorry, there are no matching tasks in your list");
            } else {
                msg = formatMessage("Here are the matching tasks in your list:\n"
                        + INDENTATION + message);
            }
            return msg;
        }
        case REMIND: {
            String msg;
            if (message.equals("")) {
                msg = formatMessage("You have no uncompleted deadline tasks!");
            } else {
                msg = formatMessage("Here are the uncompleted tasks in your list:\n"
                        + INDENTATION + message);
            }
            return msg;
        }
        default:
            return null;
        }
    }
}
