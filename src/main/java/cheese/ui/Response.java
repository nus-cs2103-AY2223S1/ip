package cheese.ui;

import java.util.ArrayList;

import cheese.data.TaskList;
import cheese.task.Task;

/**
 * Contains the responses given by Cheese.
 */
public class Response {
    /**
     * Returns welcome message.
     *
     * @return Message to display to welcome user.
     */
    public static String getWelcomeMessage() {
        return "Woof! I'm Cheese, your puppy assistant.\n"
                + "What can I do for you?";
    }

    /**
     * Returns message after adding task to list.
     *
     * @param addedTask   Task that was added.
     * @param newListSize New task list size after adding task.
     * @return Message to display after adding task.
     */
    public static String getAddTaskMessage(Task addedTask, int newListSize) {
        return "Gotcha! I have a paw-fect memory!\n"
                + "  " + addedTask + "\n"
                + "You have " + newListSize + " task(s) in the list.";
    }

    /**
     * Returns message after deleting task from list.
     *
     * @param deletedTask       Task that was deleted.
     * @param remainingListSize New task list size after deleting task.
     * @return Message to display after deleting task.
     */
    public static String getDeleteTaskMessage(Task deletedTask, int remainingListSize) {
        return "Gotcha master! I'll forget about this task!\n"
                + "  " + deletedTask + "\n"
                + "You have " + remainingListSize + " task(s) remaining.";
    }

    /**
     * Returns message after marking task as complete.
     *
     * @param taskDone Task that was marked as complete.
     * @return Message to display after marking task as complete.
     */
    public static String getMarkTaskAsDoneMessage(Task taskDone) {
        return "Paw-some! Another task done!\n" + "  " + taskDone;
    }

    /**
     * Returns message after marking task as incomplete.
     *
     * @param taskNotDone Task that was marked as incomplete.
     * @return Message to display after marking task as incomplete.
     */
    public static String getMarkTaskAsNotDoneMessage(Task taskNotDone) {
        return "Okay master, I've marked this task as incomplete.\n"
                + "  " + taskNotDone;
    }

    /**
     * Returns message after snoozing task.
     *
     * @param taskSnoozed Task that was snoozed.
     * @return Message to display after snoozing task.
     */
    public static String getSnoozeTaskMessage(Task taskSnoozed) {
        return "Gotcha! I've snoozed this task by 1 day. Wanna go for a walk now??\n"
                + "  " + taskSnoozed;
    }

    /**
     * Returns message containing task list.
     *
     * @param taskList Task list to print.
     * @return Message to display list.
     */
    public static String getTaskListMessage(TaskList taskList) {
        return taskList.toString();
    }

    /**
     * Returns message containing search result.
     *
     * @param searchResult List of searched tasks to print.
     * @return Message to display search results.
     */
    public static String getSearchResultMessage(ArrayList<Task> searchResult) {
        String msg = "";
        for (int i = 0; i < searchResult.size(); i++) {
            msg += (i + 1) + ". " + searchResult.get(i) + "\n";
        }
        return msg.trim();
    }

    /**
     * Returns unknown command message.
     *
     * @return Message to display unknown command.
     */
    public static String getUnknownCommandMessage() {
        return "Sowwy master, my puppy brain doesn't understand";
    }

    /**
     * Returns goodbye message.
     *
     * @return Goodbye message.
     */
    public static String getGoodbyeMessage() {
        return "Going so soon? :') Bye";
    }
}
