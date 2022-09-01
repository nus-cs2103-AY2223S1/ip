package duke;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import duke.task.Task;

/**
 * Ui deals with interactions with the user.
 */
public class Ui {

    /**
     * Prints the exit statement when the user inputs the exit command.
     */
    public String showBye() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.exit(0);
            }
        }, 2000);
        return "Goodbye! I hope to see you again soon.";
    }

    /**
     * Prints the task that is to be marked as done.
     *
     * @param task Task to be marked as done.
     * @return A string to confirm that the task has been marked as done.
     */
    public String showMark(Task task) {
        return "Task successfully completed!\n" + task;
    }

    /**
     * Prints the task that is to be marked as undone.
     *
     * @param task Task to be marked as undone
     * @return A string to confirm that the task has been marked as undone.
     */
    public String showUnmark(Task task) {
        return "Task incomplete.\n" + task;
    }

    /**
     * Prints the task that was newly added by the user.
     *
     * @param task Task to be added into the list.
     * @param size New number of tasks in the list.
     * @return A string to confirm that the new task has been added to the list.
     */
    public String showAdd(Task task, int size) {
        return "Successfully added new task:\n" + task + "\nYou have " + size + " task(s) in the list.";
    }

    /**
     * Prints the current list of tasks.
     *
     * @param taskList List of tasks.
     * @return A string to show the current list.
     */
    public String showList(TaskList taskList) {
        StringBuilder stringBuilder = new StringBuilder("Here are your tasks:\n");
        for (int i = 1; i <= taskList.getSize(); i++) {
            stringBuilder.append(i).append(". ").append(taskList.getTask(i - 1)).append("\n");
        }
        return stringBuilder.toString();
    }

    /**
     * Prints the task that was removed from the list.
     *
     * @param task Task that was removed.
     * @param size New number of tasks in the list.
     * @return A string showing the deleted task.
     */
    public String showDelete(Task task, int size) {
        return "Understood. I have removed this task:\n" + task + "\nYou have " + size + " task(s) in the list.";
    }

    /**
     * Prints list of tasks that occur on a particular date.
     *
     * @param tasks List of tasks that occur on that date.
     * @return The string that shows the list of tasks corresponding to that date.
     */
    public String showDate(ArrayList<Task> tasks) {
        if (tasks.size() != 0) {
            StringBuilder stringBuilder = new StringBuilder("Here are the task(s) on that date:\n");
            for (int i = 1; i <= tasks.size(); i++) {
                stringBuilder.append(i).append(". ").append(tasks.get(i - 1)).append("\n");
            }
            return stringBuilder.toString();
        } else {
            return "You have no tasks on that date.";
        }
    }

    /**
     * Prints error if no saved data is found.
     */
    public void showLoadingError() {
        System.out.println("No saved data found.");
    }

    /**
     * Prints error message when an invalid input is given.
     *
     * @param msg Error message to be printed.
     * @return The error message.
     */
    public String showError(String msg) {
        return msg;
    }

    /**
     * Prints list of tasks corresponding to the keyword.
     *
     * @param tasks List of tasks that correspond to that keyword.
     * @return A string that shows the list of tasks corresponding to that keyword.
     */
    public String showFind(ArrayList<Task> tasks) {
        if (tasks.size() != 0) {
            StringBuilder stringBuilder = new StringBuilder("Here are the matching task(s):\n");
            for (int i = 1; i <= tasks.size(); i++) {
                stringBuilder.append(i).append(". ").append(tasks.get(i - 1)).append("\n");
            }
            return stringBuilder.toString();
        } else {
            return "There are no tasks matching that word.";
        }
    }
}
