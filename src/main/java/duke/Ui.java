package duke;

import duke.task.Task;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    private static final String INDENT = "     ";
    private static final String LINE = "    _______________________________________________________";

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
     * Returns a welcome message with instructions on how to use Duke.
     *
     * @return a welcome message.
     */
    public String welcomeMsg() {
        String msg = "Hello! I'm Duke\n\n     "
                + "I'm capable of doing the following:\n     "
                + "   todo <your task>\n     "
                + "   deadline <your deadline> /by <2022-01-02>\n     "
                + "   event <your event> /at <2022-01-02> <2359>\n     "
                + "   find <content>\n     "
                + "   bye (to exit the program)\n\n     "
                + "What can I do for you?";
        return msg;
    }

    /**
     * Returns an ending message with format when user exit Duke.
     *
     * @return an ending message.
     */
    public String endingMsg() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns a numbered task list with format.
     *
     * @param list to be printed.
     * @return strings representation of the lists.
     */
    public String taskListString(TaskList list) {
        return "Here are the tasks in your list:\n     " + list.getAllTask();
    }

    /**
     * Returns a message to indicate a task is done.
     *
     * @param task to be marked done.
     * @return a message to indicate a task is done.
     */
    public String taskMarkedMsgString(Task task) {
        return "Nice! I've marked this task as done:\n     "
                + task;
    }

    /**
     * Returns a message to indicate a task is not done yet.
     *
     * @param task to be marked un-done.
     * @return a message to indicate the task is not done yet.
     */
    public String taskUnmarkedMsgString(Task task) {
        return "OK, I've marked this task as not done yet:\n     "
                + task;
    }

    /**
     * Returns a message to indicate a task is deleted from the task list.
     *
     * @param task        to be deleted.
     * @param listOfTasks task list that the task will be removed from.
     * @return a messageto indicate a task is deleted from the task list.
     */
    public String deleteTaskMsgString(Task task, TaskList listOfTasks) {
        return "Noted. I've removed this task:\n     "
                + task
                + "\n     Now you have "
                + listOfTasks.getListSize()
                + " task(s) in the list.";
    }

    /**
     * Returns a message to indicate a task is added to the task list.
     *
     * @param task        to be added.
     * @param listOfTasks task list that the task will be added to.
     * @return a message to indicate a task is added to the task list.
     */
    public String addTaskMsgString(Task task, TaskList listOfTasks) {
        return "Got it, I've added this task:\n       "
                + task.toString()
                + "\n     Now you have "
                + listOfTasks.getListSize()
                + " task(s) in the list.";
    }

    /**
     * Returns a message to indicate loading error.
     *
     * @return string representation of loading error.
     */
    public String loadingErrorString() {
        return"There is some problem loading your task(s) ☹";
    }

    /**
     * Returns a message to indicate unknown commands
     *
     * @return a message to indicate unknown commands.
     */
    public String invalidCommandErrorString() {
        return "Invalid command!";
    }

    /**
     * Returns a message to indicate that there is missing description of a command.
     *
     * @param command with missing description.
     * @return a message to indicate missing decription.
     */
    public String showNoDescriptionError(String command) {
       return "☹ OOPS!!! The description of a "
                + command
                + " cannot be empty.";
    }

    /**
     * Returns a filtered numbered task list with format.
     *
     * @param filteredListString list of filtered tasks in strings.
     * @return filtered numbered task list with format.
     */
    public String printFilteredList(String filteredListString) {
        String text = "Here are the matching tasks in your list:\n     "
                + filteredListString;
        return text;
    }
}
