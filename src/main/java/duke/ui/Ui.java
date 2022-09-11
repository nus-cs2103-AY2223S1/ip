package duke.ui;

import duke.task.Task;
import duke.tasklist.TaskList;

/**
 * Represents a controller that handles output for a user.
 */
public class Ui {

    /**
     * Prints the concluding message to the user.
     */
    public String getByeMessage() {
        return "K finally, good riddance!";
    }

    /**
     * Prints the tasks stored inside the task list.
     *
     * @param list TaskList that contains tasks
     */
    public String getListMessage(TaskList list) {
        if (list.getSize() == 0) {
            return "pff there is nothing in your list";
        }

        String str = "Here are your dumb tasks in your list:\n";
        for (int i = 1; i < list.getSize() + 1; i++) {
            Task item = list.getTask(i);
            str += i + ". " + item.toString() + "\n";
        }
        return str;
    }

    /**
     * Prints the mark message to the user.
     *
     * @param task Task that was marked
     */
    public String getMarkMessage(Task task) {
        String str = "Took you long enough to complete this task:\n" + task.toString();
        return str;
    }

    /**
     * Prints the unmark message to the user.
     *
     * @param task Task that was unmarked
     */
    public String getUnmarkMessage(Task task) {
        String str = "Another task marked as not done?? Slow indeed\n" + task.toString();
        return str;
    }

    public String getAddTaskMessage(Task task, int size) {
        String str = "Fine, I'll add this task:\n\t" + task.toString() + "\nNow you have "
                + size + " tasks in the list...";
        return str;
    }

    public String getDeleteMessage(Task task, int size) {
        String str = "Ughh I'll remove this task:\n\t" + task.toString() + "\nNow you have "
                + size + " tasks in the list...";
        return str;
    }

    public String getUnknownMessage() {
        return "What are you saying??? Try again";
    }

    public String getErrorMessage(String str) {
        return str;
    }

    public String showLoadingError() {
        return "Error loading data from file";
    }

    public String getFindMessage(TaskList taskList) {
        if (taskList.getSize() == 0) {
            return "HAHA unlucky! There is no matching task in your list!";
        }

        String str = "Tsk! Be grateful I searched through your entire list to find these:\n";
        for (int i = 1; i < taskList.getSize() + 1; i++) {
            Task item = taskList.getTask(i);
            str += i + ". " + item.toString() + "\n";
        }
        return str;
    }

    public String getUpdateMessage(Task task) {
        return "Fickle-minded as usual. I've update this task:\n" + task.toString();
    }
}
