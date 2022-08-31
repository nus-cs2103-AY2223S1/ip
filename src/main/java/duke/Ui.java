package duke;

import java.util.Scanner;

import duke.task.Task;

/**
 * Provides methods to support the user interface.
 */
public class Ui {
    public static final String LOADING_ERROR_MESSAGE = "Task list is empty.";
    public static final String GREETING_MESSAGE = "Hello! I'm RatatouilleBot~\n"
            + "I follow instructions and do what you say.\n"
            + "What can I do for you?\n";
    /**
     * Constructs an Ui instance without initiating any parameter.
     */
    public Ui() {
    }

    /**
     * Prints loading error when Duke fails to load.
     */
    public void showLoadingError() {
        System.out.println(LOADING_ERROR_MESSAGE);
    }

    /**
     * Prints greeting message when Duke is initialised.
     */
    public String getGreetingMessage() {
        return GREETING_MESSAGE;
    }

    /**
     * Returns input read by Scanner.
     *
     * @return input as a String.
     */
    public String readCommand() {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        return s;
    }

    /**
     * Return Task in the form of formatted String for better visualisation.
     *
     * @param task task that needs to be formatted.
     * @return formatted String of Task.
     */
    public String beautyWrapTask(Task task) {
        String taskType = task.getTaskType();
        String preposition = "";
        if (task.getTime() != null && task.getDate() != null) {
            if (taskType == "E") {
                preposition = "(at: ";
            } else {
                preposition = "(by: ";
            }
        }
        return "[" + task.getTaskType() + "][" + (task.isMarked() ? "X" : " ") + "] "
                + task.getTaskName() + preposition + task.getOutputDateAndTime();
    }

    /**
     * Prints the error message when exception is thrown.
     *
     * @param errorMessage String provided by the exception.
     */
    public void showError(String errorMessage) {
        System.out.println("Error message: " + errorMessage);
    }

    /**
     * Prints the TaskList consisting all the Tasks.
     *
     * @param taskList taskList consisting all the recorded Tasks.
     */
    public String getList(TaskList taskList) {
        String listOutput = "Here are the tasks in your list:\n";
        int index = 1;
        for (Task t : taskList.getList()) {
            listOutput += index + "." + this.beautyWrapTask(t) + "\n";
            index++;
        }
        return listOutput;
    }
}
