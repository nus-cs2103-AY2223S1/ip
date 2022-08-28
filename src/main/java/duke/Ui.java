package duke;

import duke.task.Task;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    private static String INDENT = "     ";
    private static String LINE = "    _______________________________________________________";

    public Ui() {
    }

    /**
     * Prints text with a specific format.
     *
     * @param text to be printed with format.
     */
    private void printWithFormat(String text) {
        System.out.println(LINE
                + "\n"
                + INDENT
                + text
                + "\n"
                + LINE);
    }

    /**
     * Prints a welcome message with instructions on how to use Duke.
     */
    public void printWelcomeMsg() {
        String welcomeMsg = "Hello! I'm Duke\n\n     "
                + "I'm capable of doing the following:\n     "
                + "   todo <your task>\n     "
                + "   deadline <your deadline> /by <2022-01-02>\n     "
                + "   event <your event> /at <2022-01-02> <2359>\n     "
                + "   find <content>\n     "
                + "   bye (to exit the program)\n\n     "
                + "What can I do for you?";
        printWithFormat(welcomeMsg);
    }

    /**
     * Prints an ending message with format when user exit Duke.
     */
    public void printEndingMsg() {
        String endingMsg = "Bye. Hope to see you again soon!";
        printWithFormat(endingMsg);
    }

    /**
     * Prints a numbered task list with format.
     *
     * @param list to be printed.
     */
    public void printTaskList(TaskList list) {
        String text = "Here are the tasks in your list:\n     " + list.getAllTask();
        printWithFormat(text);
    }

    /**
     * Prints a message to indicate a task is done.
     *
     * @param task to be marked done.
     * */
    public void printTaskMarkedMsg(Task task) {
        String markedMsg = "Nice! I've marked this task as done:\n     "
                + task;
        printWithFormat(markedMsg);
    }

    /**
     * Prints a message to indicate a task is not done yet.
     * @param task to be marked un-done.
     */
    public void printTaskUnmarkedMsg(Task task) {
        String unmarkedMsg = "OK, I've marked this task as not done yet:\n     "
                + task;
        printWithFormat(unmarkedMsg);
    }

    /**
     * Prints a message to indicate a task is deleted from the task list.
     *
     * @param task to be deleted.
     * @param listOfTasks task list that the task will be removed from.
     */
    public void printDeleteTaskMsg(Task task, TaskList listOfTasks) {
        printWithFormat("Noted. I've removed this task:\n     "
                + task
                +"\n     Now you have "
                + listOfTasks.getListSize()
                + " task(s) in the list.");
    }

    /**
     * Prints a message to indicate a task is added to the task list.
     *
     * @param task to be added.
     * @param listOfTasks task list that the task will be added to.
     */
    public void printAddTaskMsg(Task task, TaskList listOfTasks) {
        printWithFormat("Got it, I've added this task:\n       "
                + task.toString()
                + "\n     Now you have "
                + listOfTasks.getListSize()
                + " task(s) in the list.");
    }

    /**
     * Prints a message to indicate loading error.
     */
    public void showLoadingError() {
        printWithFormat("There is some problem loading your task(s) ☹");
    }

    /**
     * Prints a message to indicate unknown commands
     */
    public void showInvalidCommandError() {
        printWithFormat("Invalid command!");
    }

    /**
     * Prints a message to indicate that missing description of a command.
     *
     * @param command with missing description.
     */
    public void showNoDescriptionError(String command) {
        printWithFormat("☹ OOPS!!! The description of a "
                + command
                + " cannot be empty.");
    }

    /**
     * Prints a filtered numbered task list with format.
     *
     * @param filteredListString list of filtered tasks in strings.
     */
    public void printFilteredList(String filteredListString) {
        String text = "Here are the matching tasks in your list:\n     "
                + filteredListString;
        printWithFormat(text);
    }
<<<<<<< HEAD
}
=======
}
