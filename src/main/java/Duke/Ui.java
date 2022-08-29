package duke;

import java.util.Scanner;

/**
 * Ui object class.
 */
public class Ui {

    private Scanner sc;

    /**
     * Creates Ui object with a scanner.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints welcome message to console.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Duke\nHow may I help you?\n");
        System.out.println("list - lists out your tasks\nbye - quit\ntodo - todo task\n"
                + "deadline - deadline task /by yyyy-mm-dd\nevent - event task /by yyyy-mm-dd\n"
                + "delete - delete index\nmark - mark index\nunmark - mark index\nfind - find task");
    }

    /**
     * Reads user input command.
     *
     * @return String of user input command.
     */
    public String readCommand() {
        String fullCommand = sc.nextLine();
        return fullCommand;
    }

    /**
     * Prints line to console.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints list of tasks to console.
     *
     * @param listOfTasks containing tasks stored by user.
     */
    public void showTaskList(TaskList listOfTasks) {
        System.out.println("Here are the tasks in your list:");
        for (int index = 0; index < listOfTasks.getSize(); index++) {
            System.out.println(index + 1 + "." + listOfTasks.getTask(index).toString());
        }
    }

    /**
     * Prints task as done confirmation to console.
     *
     * @param taskIndex   position of Task marked done.
     * @param listOfTasks containing tasks stored by user.
     */
    public void showMarkedTask(int taskIndex, TaskList listOfTasks) {
        System.out.println("Nice! I've marked this task as done:\n" + "["
                + listOfTasks.getTask(taskIndex).getStatusIcon() + "] "
                + listOfTasks.getTask(taskIndex).getDescription());
    }

    /**
     * Prints task as not done confirmation to console.
     *
     * @param taskIndex   position of Task marked not done.
     * @param listOfTasks containing tasks stored by user.
     */
    public void showUnmarkedTask(int taskIndex, TaskList listOfTasks) {
        System.out.println("Ok, I've marked this task as not done yet:\n" + "["
                + listOfTasks.getTask(taskIndex).getStatusIcon() + "] "
                + listOfTasks.getTask(taskIndex).getDescription());
    }

    /**
     * Prints confirmation of Todo task added to list of tasks.
     *
     * @param toDoTask    is the task to be added to list of tasks.
     * @param listOfTasks containing tasks stored by user.
     */
    public void showToDoTask(Task toDoTask, TaskList listOfTasks) {
        System.out.println("Got it. I've added this task:\n" + toDoTask + "\nNow you have "
                + listOfTasks.getSize() + (listOfTasks.getSize() == 1 ? " task " : " tasks ") + "in the list.");
    }

    /**
     * Prints confirmation of Deadline task added to list of tasks.
     *
     * @param deadlineTask is the task to be added to list of tasks.
     * @param listOfTasks  containing tasks stored by user.
     */
    public void showDeadlineTask(Task deadlineTask, TaskList listOfTasks) {
        System.out.println("Got it. I've added this task:\n" + deadlineTask + "\nNow you have "
                + listOfTasks.getSize() + (listOfTasks.getSize() == 1 ? " task " : " tasks ") + "in the list.");
    }

    /**
     * Prints confirmation of Event task added to list of tasks.
     *
     * @param eventTask   is the task to be added to list of tasks.
     * @param listOfTasks containing tasks stored by user.
     */
    public void showEventTask(Task eventTask, TaskList listOfTasks) {
        System.out.println("Got it. I've added this task:\n" + eventTask + "\nNow you have "
                + listOfTasks.getSize() + (listOfTasks.getSize() == 1 ? " task " : " tasks ") + "in the list.");
    }

    /**
     * Prints confirmation of deleted task from list of tasks.
     *
     * @param deletedTask is the task deleted from list of tasks.
     * @param listOfTasks containing tasks stored by user.
     */
    public void showDeletedTask(Task deletedTask, TaskList listOfTasks) {
        System.out.println("Noted. I've removed this task:\n" + deletedTask + "\nNow you have "
                + listOfTasks.getSize() + (listOfTasks.getSize() == 1 ? " task " : " tasks ") + "in the list.");

    }

    /**
     * Prints list of matching tasks to console.
     *
     * @param matchingTasks is a list of tasks that matches the keyword.
     */
    public void showFindTask(TaskList matchingTasks) {
        System.out.println("Here are the matching tasks in your list:");
        for (int index = 0; index < matchingTasks.getSize(); index++) {
            System.out.println(index + 1 + "." + matchingTasks.getTask(index).toString());
        }
    }

    /**
     * Prints error message to console.
     *
     * @param errorMessage error message String.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Prints error in loading data message to console.
     */
    public void showLoadingError() {
        System.out.println("Unable to load data");
    }

    /**
     * Prints bye message to console.
     */
    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
