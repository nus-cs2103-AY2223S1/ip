package duke;

import java.util.Scanner;

/**
 * Class to handle all inputs and outputs for user interaction
 */
public class Ui {

    enum Commands {
        DELETE,
        MARK,
        UNMARK,
        LIST,
        TASK,
        FIND
    }

    /**
     * Prints welcome message to screen
     */
    public String showWelcome() {
        return "Hello! I'm Jett\n"
                + "What can I do for you?";
    }

    /**
     * Prints goodbye message
     */
    public String showBye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints the corresponding output to user according to the command given
     * @param command command given by user
     * @param taskList current lists of tasks
     * @param index index of task that command should be carried out on (0 if not applicable)
     * @return message to be printed
     */
    public String getCorrectMessage(Commands command, TaskList taskList, int index) throws UnknownCommandException {
        switch (command) {
        case TASK:
            return "Got it. I've added this task:\n" + taskList.getByIndex(index)
                + "\nNow you have " + taskList.getSize() + " tasks in the list.";
        case LIST:
            return "Here are the tasks in your list:\n" + taskList.getTaskList();
        case MARK:
            return "Nice! I've marked this task as done:\n" + taskList.getByIndex(index);
        case DELETE:
            return "Noted. I've removed this task:\n" + taskList.getByIndex(index);
        case UNMARK:
            return "OK, I've marked this task as not done yet:\n" + taskList.getByIndex(index);
        case FIND:
            return "Here are the matching tasks in your list:\n" + taskList.getTaskList();
        default:
            throw new UnknownCommandException();
        }
    }

    /**
     * Prints corresponding error message
     * @param errorMessage datetime if DateTimeParseException thrown, error message itself otherwise
     * @return error message to be printed
     */
    public String getErrorMessage(String errorMessage) {
        if (errorMessage.equals("datetime")) {
            return "We do not recognise this date time format! Please enter in yyyy-mm-dd format!";
        } else {
            return errorMessage;
        }
    }

    /**
     * Reads command from user input
     * @return command entered by user
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

}
