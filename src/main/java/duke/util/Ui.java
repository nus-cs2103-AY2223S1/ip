package duke.util;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Prints output from Duke.
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
     */
    public void exit() {
        System.out.println("Goodbye! Hope to see you again soon!\n"
                + "--------------------------");
    }

    /**
     * Prints no such task error.
     */
    public void noSuchTaskError() {
        System.out.println("OOPS!!! Error: No Such duke.Task\n"
                + "--------------------------");
    }

    /**
     * Prints error message.
     */
    public void showError() {
        System.out.println("An error occurred.");
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
     */
    public void removeTask(String removedTask, int size) {
        System.out.println("Noted. I've removed this task:\n"
                + removedTask);
        System.out.println("Now you have " + size + " tasks in the list.\n"
                + "--------------------------");
    }

    /**
     * Prints the task added to the list and updates list count.
     * @param task task to be added.
     * @param size size of list after adding the task.
     */
    public void addedTask(String task, int size) {
        System.out.println("--------------------------\n"
                + "I've added to the list:\n" + task);
        System.out.println("Now you have " + size + " tasks in the list.\n"
                + "--------------------------");
    }

    /**
     * Prints out the list of tasks added.
     * @param list list of tasks.
     */
    public void getList(ArrayList<Task> list) {
        System.out.println("Here are the tasks in your list");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
        System.out.println("--------------------------");
    }

    /**
     * Prints the task to be mark or unmark.
     * @param mark check if mark or unmark.
     * @param task task to be edited.
     */
    public void markedTask(boolean mark, String task) {
        String output = mark ? "Nice! I've marked this task as done:" : "OK, I've marked this task as not done yet:";
        System.out.println(output);
        System.out.println(task);
        System.out.println("--------------------------");
    }
}
