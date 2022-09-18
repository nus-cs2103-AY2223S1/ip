package unc;

import unc.task.Deadline;
import unc.task.Event;
import unc.task.Task;
import unc.task.Todo;


/**
 * Handles input and output.
 */
public class Ui {

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
        StringBuilder s = new StringBuilder("Here are your tasks:");
        for (int i = 0; i < taskList.size(); i++) {
            s.append("\n").append(i + 1).append(". ").append(taskList.get(i));
        }
        s.append("\nYou have ").append(taskList.size()).append(" tasks on the list.");
        return s.toString();
    }

    /**
     * Prints out the added task and new size of list.
     *
     * @param taskList List.
     * @param todo Added task.
     */
    public String addTodo(TaskList taskList, Todo todo) {
        return ("Added: \n " + todo + "\nNow you have " + taskList.size()
                + " tasks on the list.");
    }

    /**
     * Prints out the added task and new size of list.
     *
     * @param taskList List.
     * @param event Added task.
     */
    public String addEvent(TaskList taskList, Event event) {
        return ("Added: \n " + event + "\nNow you have " + taskList.size()
                + " tasks on the list.");
    }

    /**
     * Prints out the added task and new size of list.
     *
     * @param taskList List.
     * @param deadline Added task.
     */
    public String addDeadline(TaskList taskList, Deadline deadline) {
        return ("Added: \n " + deadline + "\nNow you have " + taskList.size()
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
        if (taskList.size() == 0){
            return "No tasks with keyword were found.";
        } else {
            return displayList(taskList);
        }
    }
}
