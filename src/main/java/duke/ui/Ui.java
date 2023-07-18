package duke.ui;

import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskList;

/**
 * The Ui class handles the interactions with the user.
 */
public class Ui {
    private static final String LINE = "    _________________________________";
    private static final String INDENTATION = "     ";
    private Scanner sc = new Scanner(System.in);

    /**
     * Reads the command entered by the user.
     *
     * @return Scanner for the next line.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints the message in format.
     *
     * @param message String representation of message.
     * @return Message in format.
     */
    public String printMessage(String message) {
        return LINE + "\n" + message + "\n" + LINE + "\n";
    }

    /**
     * Creates an indentation on the message.
     *
     * @param message String representation of message.
     * @return Indented message.
     */
    public String makeIndent(String message) {
        return INDENTATION + message;
    }

    /**
     * Prints the greeting message.
     *
     * @return String representation of the greeting message.
     */
    public String displayGreeting() {
        return printMessage(makeIndent("Hi, how are you doing?! I'm JRH2000\n")
                + makeIndent("How can I help you?\n"));
    }

    /**
     * Prints the bye message.
     *
     * @return String representation of the bye message.
     */
    public String displayBye() {
        return printMessage(makeIndent("Sigh...abandoned again. See you again next time :("));
    }

    /**
     * Prints the marked task.
     *
     * @param markedTask The marked task.
     * @return String representation of the marked task.
     */
    public String displayMark(Task markedTask) {
        return printMessage(makeIndent("Alright then! This task is marked as done: \n")
                + makeIndent(markedTask.toString()) + "\n");
    }

    /**
     * Prints the unmarked task.
     *
     * @param unmarkedTask The unmarked task.
     * @return String representation of the unmarked task.
     */
    public String displayUnmark(Task unmarkedTask) {
        return printMessage(makeIndent("Oh OK, this task is now marked as not done yet: \n")
                + makeIndent(unmarkedTask.toString()) + "\n");
    }

    /**
     * Prints the deleted task.
     *
     * @param deletedTask The deleted task.
     * @return String representation of the deleted task.
     */
    public String displayDelete(Task deletedTask) {
        return printMessage(makeIndent("Fine. I've removed this task: \n")
                + makeIndent(deletedTask.toString()) + "\n");
    }

    /**
     * Prints the added task.
     *
     * @param addedTask The added task.
     * @param size The size of the TaskList.
     * @return String representation of the added task.
     */
    public String displayAdd(Task addedTask, int size) {
        return printMessage(makeIndent("Sure thing! I've added this task: \n")
                + makeIndent(addedTask.toString()) + "\n"
                + makeIndent("Now you have " + size + " task(s) in the list.\n"));
    }

    /**
     * Prints the matched task.
     *
     * @param matchedTasks The matched task.
     * @return String representation of the matched task.
     */
    public String displayMatch(ArrayList<Task> matchedTasks) {
        int size = matchedTasks.size();

        String list = "These tasks matches your keyword: \n";

        for (int i = 0; i < size; i++) {
            list = list + makeIndent((i + 1) + ". " + matchedTasks.get(i).toString() + "\n");
        }

        return printMessage(list);
    }

    /**
     * Prints the TaskList.
     *
     * @param tasks The TaskList.
     * @return String representation of the TaskList.
     */
    public String displayList(TaskList tasks) {
        int size = tasks.getSize();

        String list = "";

        for (int i = 0; i < size; i++) {
            list = list + makeIndent((i + 1) + ". " + tasks.getTask(i).toString() + "\n");
        }

        return printMessage(list);
    }

    /**
     * Prints the reminder for tasks to be completed soon.
     *
     * @param remindedTasks The tasks to be reminded.
     * @return String representation of the task to be reminded.
     */
    public String displayReminder(ArrayList<Task> remindedTasks) {
        int size = remindedTasks.size();

        String list = "These tasks have to be completed soon: \n";

        for (int i = 0; i < size; i++) {
            list = list + makeIndent((i + 1) + ". " + remindedTasks.get(i).toString() + "\n");
        }

        return printMessage(list);
    }

    /**
     * Prints the error message.
     *
     * @param errMsg The error message.
     * @return String representation of the error message.
     */
    public String showError(String errMsg) {
        return makeIndent(errMsg);
    }

    /**
     * Prints the loading error message.
     *
     * @return String representation of the loading error message.
     */
    public String showLoadingError() {
        return showError("There are no data about tasks found!");
    }
}
