package duke.util;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Prints output messages from Duke.
 *
 * @author Jicson Toh
 */
public class Ui {

    /**
     * Prints welcome message.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Botson\n"
                + "What can I help you with? :-)\n"
                + "--------------------------");
    }

    /**
     * Prints exit message.
     * @return exit message.
     */
    public String exit() {
        return ("Goodbye! Hope to see you again soon!");
    }

    /**
     * Prints no such task error.
     * @return returns no such task error.
     */
    public String noSuchTaskError() {
        return ("OOPS!!! Error: No Such Task :-(");
    }

    /**
     * Prints unknown error message.
     */
    public void showError() {
        System.out.println("An error occurred.");
        showLine();
    }

    /**
     * Prints error message.
     */
    public void showError(String error) {
        System.out.println(error);
        showLine();
    }

    /**
     * Prints cannot understand error message.
     */
    public void cannotUnderstandError() {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                + "--------------------------");
    }

    /**
     * Prints the removed task from the list and updates the list count.
     * @param removedTask task to be removed.
     * @param size size of list after task is remove.
     * @return returns the task removed.
     */
    public String showRemovedTask(String removedTask, int size) {
        return ("Noted. I've removed this task:\n"
                + removedTask + "\n"
                + "Now you have " + size + " tasks in the list.");
    }

    /**
     * Prints the task added to the list and updates list count.
     * @param task task to be added.
     * @param size size of list after adding the task.
     * @return returns the task added.
     */
    public String showAddedTask(String task, int size) {
        return ("I've added to the list:\n" + task + "\n"
            + "Now you have " + size + " tasks in the list.");
    }

    /**
     * Prints out the list of tasks added.
     * @param list list of tasks.
     * @return returns list of tasks.
     */
    public String getList(ArrayList<Task> list) {
        StringBuilder output = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < list.size(); i++) {
            String task = ((i + 1) + ". " + list.get(i));
            output.append(task);
            output.append("\n");
        }
        return output.toString();
    }

    /**
     * Prints the task to be mark or unmark.
     * @param mark check if mark or unmark.
     * @param task task to be edited.
     * @return returns the string of the marked task.
     */
    public String showMarkedTask(boolean mark, String task) {
        String output = mark ? "Nice! I've marked this task as done:\n"
                : "OK, I've marked this task as not done yet:\n";
        return (output + task);
    }

    /**
     * Prints the task found to match input keyword.
     * @param list list of tasks found.
     * @return returns the tasks found.
     */
    public String showFoundTasks(ArrayList<Task> list) {
        String output = getList(list);
        return ("I found some matching tasks!\n" + output);
    }

    /**
     * Prints the line.
     */
    public void showLine() {
        System.out.println("--------------------------");
    }
}
