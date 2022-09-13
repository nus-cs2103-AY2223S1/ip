package unc;

import unc.task.Deadline;
import unc.task.Event;
import unc.task.Task;
import unc.task.Todo;


/**
 * Handles input and output.
 */
public class Ui {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Prints a preset welcome message.
     */
    public void showWelcome() {

        System.out.println("Hello from\n" + LOGO);
        System.out.println("Welcome to UNC\n");
    }

    /**
     * Stops reading inputs.
     * Prints a preset goodbye message.
     */
    public String goodbye() {
        return ("Bye");
    }

    /**
     * Prints the entire list.
     * One line for each task.
     *
     * @param taskList List.
     */
    public String displayList(TaskList taskList) {
        String s = "";
        for (int i = 0; i < taskList.size(); i++) {
            s = s + ("\n" + (i + 1) + ". " + taskList.get(i));
        }
        return s;
    }

    /**
     * Prints out the added task and new size of list.
     *
     * @param taskList List.
     * @param todo Added task.
     */
    public String addTodo(TaskList taskList, Todo todo) {
        return ("added: \n " + todo + "\nNow you have " + taskList.size()
                + " tasks on the list.");
    }

    /**
     * Prints out the added task and new size of list.
     *
     * @param taskList List.
     * @param event Added task.
     */
    public String addEvent(TaskList taskList, Event event) {
        return ("added: \n " + event + "\nNow you have " + taskList.size()
                + " tasks on the list.");
    }

    /**
     * Prints out the added task and new size of list.
     *
     * @param taskList List.
     * @param deadline Added task.
     */
    public String addDeadline(TaskList taskList, Deadline deadline) {
        return ("added: \n " + deadline + "\nNow you have " + taskList.size()
                + " tasks on the list.");
    }

    /**
     * Prints out the task that was marked.
     *
     * @param taskList List.
     * @param index The index of newly marked task.
     */
    public String mark(TaskList taskList, int index) {
        return ("Marked as done: \n" + taskList.get(index));
    }

    /**
     * Prints out the task that was unmarked.
     *
     * @param taskList List.
     * @param index The index of newly unmarked task.
     */
    public String unmark(TaskList taskList, int index) {
        return ("Marked as not done: \n" + taskList.get(index));
    }

    /**
     * Prints out the task that was removed.
     *
     * @param task The recently removed task.
     * @param size The new size of list.
     */
    public String delete(Task task, int size) {
        return ("Deleted: \n" + task + "\nNow you have " + size
                + " tasks on the list.");
    }

    public String displayFoundList(TaskList taskList) {
        return displayList(taskList);
    }
}
