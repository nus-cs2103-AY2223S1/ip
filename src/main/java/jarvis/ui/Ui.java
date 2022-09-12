package jarvis.ui;

import java.util.Scanner;

import jarvis.task.Task;
import jarvis.task.TaskList;

/**
 * Ui --- prints response based on user input.
 */
public class Ui {
    private final Scanner sc;

    /**
     * Constructor.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Returns goodbye message when application is terminated.
     *
     * @return goodbye message
     */
    public String showByeMessage() {
        return "Goodbye. You may now close the application.";
    }

    /**
     * Returns feedback message.
     *
     * @param message feedback message.
     */
    public String showMessage(String message) {
        return message;
    }

    /**
     * Returns task added message when a task has been added.
     *
     * @param task task to be added
     * @param taskListSize size of task list
     * @return task added message
     */
    public String showTaskAddedMessage(Task task, int taskListSize) {
        return String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.",
                task, taskListSize);
    }

    /**
     * Returns task deleted message when a task has been deleted.
     *
     * @param task task to be deleted
     * @param taskListSize size of task list
     * @return task deleted message
     */
    public String showTaskDeletedMessage(Task task, int taskListSize) {
        return String.format("Noted. I've removed this task:\n%s\nNow you have %d tasks in the list.",
                task, taskListSize);
    }

    /**
     * Returns marked task message when a task is marked.
     *
     * @param taskIndex index of task to be marked
     * @param tasks list of tasks
     * @return task deleted message
     */
    public String showTaskDoneMessage(int taskIndex, TaskList tasks) {
        return "Nice! I've marked this task as done:\n" + tasks.get(taskIndex);
    }

    /**
     * Returns unmarked task message when a task is unmarked.
     *
     * @param taskIndex index of task to be marked
     * @param tasks list of tasks
     * @return task deleted message
     */
    public String showTaskUndoneMessage(int taskIndex, TaskList tasks) {
        return "Okay, I've marked this task as not done yet:\n" + tasks.get(taskIndex);
    }
}
