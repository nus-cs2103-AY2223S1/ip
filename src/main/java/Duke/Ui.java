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
        sb = new StringBuilder();
        sb.append("Hello! I'm Duke\nHow may I help you?\nlist - lists out your tasks\nbye - quit\n"
                + "todo - todo <task>\ndeadline - deadline <task /by yyyy-mm-dd>\nevent - event <task /by yyyy-mm-dd>\n"
                + "delete - delete <index>\nmark - mark <index>\nunmark - unmark <index>\nfind - find <task>\n"
                + "undo - undo <function>");
        System.out.println("Hello! I'm Duke\nHow may I help you?\nlist - lists out your tasks\nbye - quit\n"
                + "todo - todo <task>\ndeadline - deadline <task /by yyyy-mm-dd>\nevent - event <task /by yyyy-mm-dd>\n"
                + "delete - delete <index>\nmark - mark <index>\nunmark - unmark <index>\nfind - find <task>\n"
                + "undo - undo <function>");
        return sb.toString();
    }

    /**
     * Reads user command.
     *
     * @return String of user command.
     */
    public String readCommand() {
        String fullCommand = sc.nextLine();
        return fullCommand;
    }

    /**
     * Prints straight line.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
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
        for (int index = 0; index < listOfTasks.getSize(); index++) {
            sb.append(index + 1 + ". " + listOfTasks.getTask(index).toString());
            sb.append(System.lineSeparator());
            System.out.println(index + 1 + ". " + listOfTasks.getTask(index).toString());
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
        sb = new StringBuilder();
        sb.append("Nice! I've marked this task as done:\n" + "["
                + listOfTasks.getTask(taskIndex).getStatusIcon() + "] "
                + listOfTasks.getTask(taskIndex).getDescription());
        System.out.println("Nice! I've marked this task as done:\n" + "["
                + listOfTasks.getTask(taskIndex).getStatusIcon() + "] "
                + listOfTasks.getTask(taskIndex).getDescription());
        return sb.toString();
    }

    /**
     * Displays task marked as not done confirmation.
     *
     * @param taskIndex   position of Task marked not done.
     * @param listOfTasks containing tasks stored by user.
     * @return a string of confirmation for tasks marked as not done.
     */
    public String showUnmarkedTask(int taskIndex, TaskList listOfTasks) {
        sb = new StringBuilder();
        sb.append("Ok, I've marked this task as not done yet:\n" + "["
                + listOfTasks.getTask(taskIndex).getStatusIcon() + "] "
                + listOfTasks.getTask(taskIndex).getDescription());
        System.out.println("Ok, I've marked this task as not done yet:\n" + "["
                + listOfTasks.getTask(taskIndex).getStatusIcon() + "] "
                + listOfTasks.getTask(taskIndex).getDescription());
        return sb.toString();
    }

    /**
     * Displays Todo task added to list of tasks.
     *
     * @param toDoTask    is the task to be added to list of tasks.
     * @param listOfTasks containing tasks stored by user.
     * @return a string of the Todo task added to list of tasks.
     */
    public String showToDoTask(Task toDoTask, TaskList listOfTasks) {
        sb = new StringBuilder();
        sb.append("Got it. I've added this task:\n" + toDoTask + "\nNow you have "
                + listOfTasks.getSize() + (listOfTasks.getSize() == 1 ? " task " : " tasks ") + "in the list.");
        System.out.println("Got it. I've added this task:\n" + toDoTask + "\nNow you have "
                + listOfTasks.getSize() + (listOfTasks.getSize() == 1 ? " task " : " tasks ") + "in the list.");
        return sb.toString();
    }

    /**
     * Displays Deadline task added to list of tasks.
     *
     * @param deadlineTask is the task to be added to list of tasks.
     * @param listOfTasks  containing tasks stored by user.
     * @return a string of the Deadline task added to list of tasks.
     */
    public String showDeadlineTask(Task deadlineTask, TaskList listOfTasks) {
        sb = new StringBuilder();
        sb.append("Got it. I've added this task:\n" + deadlineTask + "\nNow you have "
                + listOfTasks.getSize() + (listOfTasks.getSize() == 1 ? " task " : " tasks ") + "in the list.");
        System.out.println("Got it. I've added this task:\n" + deadlineTask + "\nNow you have "
                + listOfTasks.getSize() + (listOfTasks.getSize() == 1 ? " task " : " tasks ") + "in the list.");
        return sb.toString();
    }

    /**
     * Displays Event task added to list of tasks.
     *
     * @param eventTask   is the task to be added to list of tasks.
     * @param listOfTasks containing tasks stored by user.
     * @return a string of the Event task added to list of tasks.
     */
    public String showEventTask(Task eventTask, TaskList listOfTasks) {
        sb = new StringBuilder();
        sb.append("Got it. I've added this task:\n" + eventTask + "\nNow you have "
                + listOfTasks.getSize() + (listOfTasks.getSize() == 1 ? " task " : " tasks ") + "in the list.");
        System.out.println("Got it. I've added this task:\n" + eventTask + "\nNow you have "
                + listOfTasks.getSize() + (listOfTasks.getSize() == 1 ? " task " : " tasks ") + "in the list.");
        return sb.toString();
    }

    /**
     * Displays task deleted from the list of tasks.
     *
     * @param deletedTask is the task deleted from list of tasks.
     * @param listOfTasks containing tasks stored by user.
     * @return a string of the task deleted from the list of tasks.
     */
    public String showDeletedTask(Task deletedTask, TaskList listOfTasks) {
        sb = new StringBuilder();
        sb.append("Noted. I've removed this task:\n" + deletedTask + "\nNow you have "
                + listOfTasks.getSize() + (listOfTasks.getSize() == 1 ? " task " : " tasks ") + "in the list.");
        System.out.println("Noted. I've removed this task:\n" + deletedTask + "\nNow you have "
                + listOfTasks.getSize() + (listOfTasks.getSize() == 1 ? " task " : " tasks ") + "in the list.");
        return sb.toString();
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
        for (int index = 0; index < matchingTasks.getSize(); index++) {
            sb.append(index + 1 + ". " + matchingTasks.getTask(index).toString());
            sb.append(System.lineSeparator());
            System.out.println(index + 1 + ". " + matchingTasks.getTask(index).toString());
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
        sb = new StringBuilder();
        sb.append("Got it. I've added back this task:\n" + undoTask + "\nNow you have "
                + listOfTasks.getSize() + (listOfTasks.getSize() == 1 ? " task " : " tasks ") + "in the list.");
        System.out.println("Got it. I've added back this task:\n" + undoTask + "\nNow you have "
                + listOfTasks.getSize() + (listOfTasks.getSize() == 1 ? " task " : " tasks ") + "in the list.");
        return sb.toString();
    }

    /**
     * Prints error message to console.
     *
     * @param errorMessage error message String.
     * @return a string of error message.
     */
    public String showError(String errorMessage) {
        sb = new StringBuilder();
        sb.append(errorMessage);
        sb.append(System.lineSeparator());
        System.out.println(errorMessage);
        return sb.toString();
    }

    /**
     * Prints error in loading data message to console.
     */
    public String showLoadingError() {
        sb = new StringBuilder();
        sb.append("Unable to load data");
        sb.append(System.lineSeparator());
        System.out.println("Unable to load data");
        return sb.toString();
    }

    /**
     * Prints bye message to console.
     */
    public String showBye() {
        sb = new StringBuilder();
        sb.append("Bye. Hope to see you again soon!");
        sb.append(System.lineSeparator());
        System.out.println("Bye. Hope to see you again soon!");
        return sb.toString();
    }
}
