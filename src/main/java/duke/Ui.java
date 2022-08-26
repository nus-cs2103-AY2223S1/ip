package duke;

import java.util.Scanner;

/**
 * The Ui Class encapsulates all the input and output interaction between Duke and user.
 */
public class Ui {
    private static final String WELCOME_MSG = "Hello! I'm Duke.\nWhat can I do for you?";
    private static final String GOODBYE_MSG = "Bye. Hope to see you again soon!";
    private final Scanner sc;

    /**
     * Initializes an instance of Ui.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Reads the command from a user.
     *
     * @return String representation of the command.
     */
    public String getCommand() {
        return sc.nextLine();
    }

    private void printMessage(String message) {
        String line = "____________________________________________________________";
        String res = line + "\n" + message + "\n" + line;
        res = res.replaceAll("(?m)^", "\t");
        System.out.println(res);
    }

    /**
     * Displays the welcome message.
     */
    public void printWelcome() {
        printMessage(WELCOME_MSG);
    }

    /**
     * Displays the goodbye message.
     */
    public void printGoodbye() {
        printMessage(GOODBYE_MSG);
    }

    /**
     * Displays the list of task in the taskList.
     *
     * @param taskList Tasks to be displayed.
     */
    public void printTaskList(String taskList) {

        printMessage("Here are the tasks in your list:\n" + taskList);
    }

    public void printMatchingTaskList(String taskList) {
        printMessage("Here are the matching tasks in your list:\n" + taskList);
    }

    /**
     * Displays the response after successfully adding a task to the list of task.
     *
     * @param taskDesc String representation of the task.
     * @param totalTask Number of saved task.
     */
    public void printAddTaskReply(String taskDesc, int totalTask) {
        printMessage("Got it. I've added this task:\n\t" + taskDesc
                + "\nNow you have " + totalTask + " tasks in the list.");
    }

    /**
     * Displays the response after successfully marking a task as done.
     *
     * @param taskDesc String representation of the task.
     */
    public void printMarkTaskReply(String taskDesc) {
        printMessage("Nice! I've marked this task as done:\n\t" + taskDesc);
    }

    /**
     * Displays the response after successfully marking a task as not done.
     *
     * @param taskDesc String representation of the task.
     */
    public void printUnmarkTaskReply(String taskDesc) {
        printMessage("OK, I've marked this task as not done yet:\n\t" + taskDesc);
    }

    /**
     * Displays the response after successfully deleting a task.
     *
     * @param taskDesc String representation of the task.
     */
    public void printDeleteTaskReply(String taskDesc, int totalTask) {
        printMessage("Noted. I've removed this task:\n\t" + taskDesc +
                "\nNow you have " + totalTask + " tasks in the list.");
    }

    /**
     * Displays the exception message to the user.
     *
     * @param e Exception to be displayed to user.
     */
    public void printException(Exception e) {
        printMessage("OOPS! " + e.getMessage());
    }
}
