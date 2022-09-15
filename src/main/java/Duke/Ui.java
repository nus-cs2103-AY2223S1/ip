package duke;

import java.util.Scanner;

/**
 * Ui object class.
 */
public class Ui {

    private Scanner sc;
    private StringBuilder sb;

    /**
     * Creates Ui object with a scanner.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Greets the user with name and functions.
     *
     * @return a string of duke's welcome message.
     */
    public String showWelcome() {
        return "Hello! I'm Duke\nHow may I help you?\nlist - lists out your tasks\nbye - quit\n"
                + "todo - todo <task>\ndeadline - deadline <task /by yyyy-mm-dd>\nevent - event <task /by yyyy-mm-dd>\n"
                + "delete - delete <index>\nmark - mark <index>\nunmark - unmark <index>\nfind - find <task>\n"
                + "undo - undo <function>";
    }

    /**
     * Displays list of tasks stored by user.
     *
     * @param listOfTasks containing tasks stored by user.
     * @return a string of list of tasks stored by user.
     */
    public String showTaskList(TaskList listOfTasks) {
        sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int index = 1; index <= listOfTasks.getSize(); index++) {
            sb.append(String.format("%d. %s\n", index, listOfTasks.getTask(index - 1)));
        }
        return sb.toString();
    }

    /**
     * Displays task marked as done confirmation.
     *
     * @param taskIndex   position of Task marked done.
     * @param listOfTasks containing tasks stored by user.
     * @return a string of confirmation for tasks marked as done.
     */
    public String showMarkedTask(int taskIndex, TaskList listOfTasks) {
        return "Nice! I've marked this task as done:\n" + "["
                + listOfTasks.getTask(taskIndex).getStatusIcon() + "] "
                + listOfTasks.getTask(taskIndex).getDescription();
    }

    /**
     * Displays task marked as not done confirmation.
     *
     * @param taskIndex   position of Task marked not done.
     * @param listOfTasks containing tasks stored by user.
     * @return a string of confirmation for tasks marked as not done.
     */
    public String showUnmarkedTask(int taskIndex, TaskList listOfTasks) {
        return "Ok, I've marked this task as not done yet:\n" + "["
                + listOfTasks.getTask(taskIndex).getStatusIcon() + "] "
                + listOfTasks.getTask(taskIndex).getDescription();
    }

    /**
     * Displays Todo task added to list of tasks.
     *
     * @param toDoTask    is the task to be added to list of tasks.
     * @param listOfTasks containing tasks stored by user.
     * @return a string of the Todo task added to list of tasks.
     */
    public String showToDoTask(Task toDoTask, TaskList listOfTasks) {
        return "Got it. I've added this task:\n" + toDoTask + "\nNow you have "
                + listOfTasks.getSize() + (listOfTasks.getSize() == 1 ? " task " : " tasks ") + "in the list.";
    }

    /**
     * Displays Deadline task added to list of tasks.
     *
     * @param deadlineTask is the task to be added to list of tasks.
     * @param listOfTasks  containing tasks stored by user.
     * @return a string of the Deadline task added to list of tasks.
     */
    public String showDeadlineTask(Task deadlineTask, TaskList listOfTasks) {
        return "Got it. I've added this task:\n" + deadlineTask + "\nNow you have "
                + listOfTasks.getSize() + (listOfTasks.getSize() == 1 ? " task " : " tasks ") + "in the list.";
    }

    /**
     * Displays Event task added to list of tasks.
     *
     * @param eventTask   is the task to be added to list of tasks.
     * @param listOfTasks containing tasks stored by user.
     * @return a string of the Event task added to list of tasks.
     */
    public String showEventTask(Task eventTask, TaskList listOfTasks) {
        return "Got it. I've added this task:\n" + eventTask + "\nNow you have "
                + listOfTasks.getSize() + (listOfTasks.getSize() == 1 ? " task " : " tasks ") + "in the list.";
    }

    /**
     * Displays task deleted from the list of tasks.
     *
     * @param deletedTask is the task deleted from list of tasks.
     * @param listOfTasks containing tasks stored by user.
     * @return a string of the task deleted from the list of tasks.
     */
    public String showDeletedTask(Task deletedTask, TaskList listOfTasks) {
        return "Noted. I've removed this task:\n" + deletedTask + "\nNow you have "
                + listOfTasks.getSize() + (listOfTasks.getSize() == 1 ? " task " : " tasks ") + "in the list.";
    }

    /**
     * Displays the matching tasks.
     *
     * @param matchingTasks is a list of tasks that matches the keyword.
     * @return a string of list of matching tasks.
     */
    public String showFindTask(TaskList matchingTasks) {
        sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        for (int index = 1; index <= matchingTasks.getSize(); index++) {
            sb.append(String.format("%d. %s\n", index, matchingTasks.getTask(index - 1)));
        }
        return sb.toString();
    }

    /**
     * Displays task added to list of tasks after undo.
     *
     * @param undoTask    is the task to be added back to list of tasks.
     * @param listOfTasks containing tasks stored by user.
     * @return a string of the task added to list of tasks.
     */
    public String showUndoDeletedTask(Task undoTask, TaskList listOfTasks) {
        return "Got it. I've added back this task:\n" + undoTask + "\nNow you have "
                + listOfTasks.getSize() + (listOfTasks.getSize() == 1 ? " task " : " tasks ") + "in the list.";
    }

    /**
     * Prints error message to console.
     *
     * @param errorMessage error message String.
     * @return a string of error message.
     */
    public String showError(String errorMessage) {
        return errorMessage;
    }

    /**
     * Prints error in loading data message to console.
     */
    public String showLoadingError() {
        return "Unable to load data\n";
    }

    /**
     * Prints bye message to console.
     */
    public String showBye() {
        return "Bye. Hope to see you again soon!\n";
    }
}
