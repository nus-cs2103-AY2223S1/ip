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
     * Prints line for aesthetic purposes
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints welcome message to screen
     */
    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm Jett");
        System.out.println("What can I do for you?");
        showLine();
    }

    /**
     * Prints goodbye message
     */
    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }

    /**
     * Prints the corresponding output to user according to the command given
     * @param command command given by user
     * @param taskList current lists of tasks
     * @param index index of task that command should be carried out on (0 if not applicable)
     */
    public void printCorrectMessage(Commands command, TaskList taskList, int index) {
        switch (command) {
        case TASK:
            System.out.println("Got it. I've added this task:");
            taskList.printByIndex(index);
            System.out.println("Now you have " + taskList.getSize() + " tasks in the list.");
            break;
        case LIST:
            System.out.println("Here are the tasks in your list:");
            taskList.printTaskList();
            break;
        case MARK:
            System.out.println("Nice! I've marked this task as done:");
            taskList.printByIndex(index);
            break;
        case DELETE:
            System.out.println("Noted. I've removed this task:");
            taskList.printByIndex(index);
            break;
        case UNMARK:
            System.out.println("OK, I've marked this task as not done yet:");
            taskList.printByIndex(index);
            break;
        default:
            break;
        }
    }

    /**
     * Prints corresponding error message
     * @param errorMessage datetime if DateTimeParseException thrown, error message itself otherwise
     */
    public void printErrorMessage(String errorMessage) {
        if (errorMessage.equals("datetime")) {
            System.out.println("We do not recognise this date time format! Please enter in yyyy-mm-dd format!");
        } else {
            System.out.println(errorMessage);
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
