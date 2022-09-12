package seedu.duke.operations;

import seedu.duke.task.Task;

import java.util.Scanner;

/**
 * Ui is the class that contains the messages that Duke will output
 * to the command line in response to user inputs. It contains a
 * Scanner as well such that it can accept user inputs.
 */
public class Ui {
    private final Scanner cmdReader;
    private String messageOutputs = "";

    /**
     * Returns Ui instance.
     */
    public Ui() {
        this.cmdReader = new Scanner(System.in);
    }

    /**
     * Returns user input into Ui.
     *
     * @return  User input from the command line
     */
    public String readCommand() {
        return cmdReader.nextLine();
    }

    public void appendMessage(String message) {
        messageOutputs += message + "\n";
    }

    public String returnMessages() {
        String output = messageOutputs.substring(0, messageOutputs.length() - 1);
        messageOutputs = "";
        return output;
    }

    /**
     * Prints welcome message when initializing Duke.
     */
    public String getWelcomeMessage() {
        return "Hello! I'm duke.Duke\nWhat can I do for you?";
    }

    /**
     * Prints line breaks.
     */
    public String getLineBreak() {
        return "_".repeat(100);
    }

    /**
     * Prints exit message when user closes Duke.
     */
    public String getExitMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Message printed when there are no task in task-list.
     */
    public String getNoTaskMessage() {
        return "It appears you have no tasks right now,\nwould you like to add some?";
    }

    /**
     * Message printed when adding a new task to task-list.
     */
    public String getNewTaskMessage() {
        return "Got it. I've added this task:";
    }

    /**
     * Message printed when marking an unmarked task.
     */
    public String getMarkedTaskMessage() {
        return "Nice! I've marked this task as done:";
    }

    /**
     * Message printed when unmarking a marked task.
     */
    public String getUnmarkedTaskMessage() {
        return "OK, I've marked this task as not done yet:";
    }

    /**
     * Message printed when marking a marked task.
     */
    public String getAlreadyMarkedTaskMessage() {
        return "This task is already marked:";
    }

    /**
     * Message printed when unmarking an unmarked task.
     */
    public String getAlreadyUnmarkedTaskMessage() {
        return "This task is already unmarked:";
    }

    /**
     * Message printed when removing a task.
     *
     * @param task  Task to be removed
     */
    public String getRemovedTaskMessage(Task task) {
        return "Noted. I've removed this task:" + task.toString();
    }

    /**
     * Message printed when matching tasks are found.
     */
    public String getSearchTaskMessage() {
        return "Here are the matching tasks in your list:";
    }

    /**
     * Message printed when no matching task found.
     */
    public String getNoMatchingTaskMessage() {
        return "Sorry! I did not find any matching task.";
    }

    /**
     * Prints the error message into the command line.
     *
     * @param errorMessage  Error message returned by Duke
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Returns an error message for general invalid inputs in the form of a String.
     *
     * @return  Invalid input error message
     */
    public String getInvalidInputMessage() {
        return "The input is invalid, please try again.";
    }

    /**
     * Returns an error message for task-list operation inputs without index in
     * the form of a String.
     *
     * @return Invalid input error message
     */
    public String getNoIndexProvidedErrorMessage() {
        return "Please provide the index of he relevant task after the command.";
    }

    /**
     * Returns an error message for task-list operation inputs with index out of
     * range of the task-list in the form of a String.
     *
     * @return Invalid input error message
     */
    public String getTaskListIndexErrorMessage() {
        return "It appears there is no such task. Please try again";
    }

    /**
     * Returns an error message for task making operation inputs without description
     * in the form of a String.
     *
     * @return Invalid input error message
     */
    public String getNoDescriptionErrorMessage() {
        return "The description of the task cannot be empty.";
    }

    /**
     * Returns an error message for task making operation inputs without time
     * provided (In the case of time sensitive tasks) in the form of a String.
     *
     * @return Invalid input error message
     */
    public String getNoTimeErrorMessage() {
        return "Please provide the relevant time for this type of task,"
                + "by typing \"/\" followed by the time.";
    }

    /**
     * Returns an error message for task making operation inputs with invalid
     * datetime format in the form of a String.
     *
     * @return Invalid input error message
     */
    public String getInvalidTimeFormatErrorMessage() {
        return "Invalid date provided.\nPlease format the date in YYYY-MM-DD.";
    }

    /**
     * Returns an error message when find Command has an empty String as a
     * key word.
     *
     * @return  Invalid input error message
     */
    public String getEmptyKeywordErrorMessage() {
        return "Please input a key word after the \"find\" command.";
    }

    /**
     * Prints a loading error message to command line.
     */
    public void showLoadingError() {
        System.out.println("There appears to be an issue retrieving your previous records");
    }

    public String getClashingEventErrorMessage(Task task, Task clashingTask) {
        return String.format("Sorry, it appears that your new Task clashes with an existing Task."
                + "\n Existing Task: %s"
                + "\n New Task:      %s"
                , task, clashingTask);
    }

    public String getHelpMessage() {
        return "Hello, thank you for using Duke chat-bot."
                + "\nBelow are a list of commands:"
                + "\n"
                + "\ntodo [Task description]"
                + "\nThis creates a new todo task in your list of tasks."
                + "\n"
                + "\ndeadline [description] /[due date]"
                + "\nThis creates a new task with deadline in your list of tasks."
                + "\n"
                + "\nevent [description] /[event date]"
                + "\nThis creates a new event with its date in your list of tasks."
                + "\n"
                + "\nlist"
                + "\nThis displays all your current tasks."
                + "\n"
                + "\nmark [index]"
                + "\nThis marks the task with the given index."
                + "\n"
                + "\nunmark [index]"
                + "\nThis unmarks the task with the given index."
                + "\n"
                + "\ndelete [index]"
                + "\nThis deletes the task with the given index."
                + "\n"
                + "\nfind [keyword]"
                + "\nThis searches the tasks with the provided keyword."
                + "\n"
                + "\nbye"
                + "\nThis closes the application."
                + "\n"
                + "\nhelp"
                + "\nThis displays the command guide.";
    }
}
