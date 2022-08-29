package tako;

import java.util.Scanner;

import tako.task.Task;

/**
 * Deals with user interaction.
 */
public class Ui {
    private String response;

    /**
     * Informs the user that their task is added.
     *
     * @param task Task to add.
     * @param taskCount Total number of tasks in the task list.
     */
    public void showAdd(Task task, int taskCount) {
        response = String.format(
                "added: %s\nTotal tasks: %d",task ,taskCount);
    }

    /**
     * Informs the user that their task is deleted.
     *
     * @param task Task to delete.
     * @param taskCount Total number of tasks in the task list.
     */
    public void showDelete(Task task, int taskCount) {
        response = String.format(
                "deleted: %s\nTotal tasks: %d",task ,taskCount);
    }

    /**
     * Informs the user that an error has occurred.
     *
     * @param message Error message.
     */
    public void showError(String message) {
        response = "An error has occurred.\n" + message;
    }

    /**
     * Informs the user on the tasks that have been found.
     *
     * @param tasks Task list containing the tasks that were found.
     */
    public void showFind(TaskList tasks) {
        if (tasks.getSize() == 0) {
            response = "There are no matching tasks in your list.";
        } else {
            showList(tasks);
            response = "Here are the matching tasks in your list:\n" + response;
        }
    }

    /**
     * Shows all the tasks in the task list.
     *
     * @param tasks Task list to show.
     */
    public void showList(TaskList tasks) {
        response = "";
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.get(i);
            response = String.format("%s%d.%s\n", response, i + 1, task);
        }
    }

    /**
     * Informs the user that a loading error has occurred.
     */
    public void showLoadingError() {
        response = "Tasks failed to load." + "A new task list will be used instead.";
    }

    /**
     * Informs the user that their task is marked.
     *
     * @param task Task to mark.
     */
    public void showMark(Task task) {
        response = "marked: " + task;
    }

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        response = "Hello! I'm Tako.\n" + "What do you want?";
    }

    public String getResponse() {
        return response;
    }
}
