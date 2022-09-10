package duke;

import duke.task.Task;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

/**
 * Ui acts as the control center for interacting with the user.
 * Printing messages, errors and reading user input are done in this class.
 */
public class Ui {
    /**
     * Shows unknown message.
     *
     * @return unknown message
     */
    public String showUnknownMessage() {
        return ("I'm sorry, "
                + "but I don't know what that means :-(");
    }

    /**
     * Shows add messages.
     *
     * @param task Task added
     * @param size Size of TaskList
     * @return add message
     */
    public String showAddMessage(Task task, int size) {
        String plural = size == 1
                ? "task"
                : "tasks";
        return "Got it. I've added this task:\n "
                + task + "\nNow you have "
                + size + " " + plural + " in the list.";
    }

    /**
     * Shows delete messages.
     *
     * @param task Task that is going to be deleted
     * @param size Size of TaskList
     * @return delete message
     */
    public String showDeleteMessage(Task task, int size) {
        String info = task.toString();
        String plural = size - 1 <= 1
                ? "task"
                : "tasks";
        return "Noted. I've removed this task:\n  "
                + info + "\n Now you have " + size
                + " " + plural + " in the list";
    }

    /**
     * Shows exit messages when the program is going to end.
     *
     * @return exit message
     */
    public static String showExitMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Shows header of list command.
     *
     * @return header of list command
     */
    public String showList() {
        return ("Your List :");
    }

    /**
     * Shows the details of task.
     *
     * @param index Index of task
     * @param task Task to print
     * @return task details
     */
    public String showTask(int index, Task task) {
        assert(index > 0);
        return (index + "." + task.toString());
    }

    /**
     * Shows the details of taskList.
     *
     * @param taskList TaskList to print
     * @return string of taskList
     */
    public String showTaskList(TaskList taskList) {
        assert(taskList != null);
        String strOfTasks = "";
        for (int i = 0; i < taskList.getSize(); i++) {
            String taskString = showTask(i + 1, taskList.getTask(i)) + "\n";
            strOfTasks += taskString;
        }
        return strOfTasks;
    }

    /**
     * Shows mark messages.
     *
     * @param task Task marked as done
     * @return mark message
     */
    public String showMarkMessage(Task task) {
        return "Nice! I've marked this task as done:\n  " + task.toString();
    }

    /**
     * Shows unmark messages.
     *
     * @param task Task unMarked as done.
     * @return unmark message
     */
    public String showUnmarkMessage(Task task) {
        return "OK, I've marked this task as not done yet:\n  " + task.toString();
    }

    /**
     * Shows find message when tasks are found.
     *
     * @return task found message
     */
    public String showFindMessage() {
        return ("Here are the matching tasks in your list:\n");
    }

    /**
     * Shows find message when no task is found.
     *
     * @return task not found message
     */
    public String showFindEmptyMessage() {
        return ("There are no matching task in your list\n");
    }

    /**
     * Shows snooze message when task is snoozed.
     *
     * @param task task to snooze
     * @return snooze message
     */
    public String showSnoozeMessage(Task task) {
        return "OK, I've snoozed this task:\n  " + task.toString();
    }

    /**
     * Shows invalid index message.
     *
     * @return invalid index message
     */
    public String showInvalidIndexMessage() {
        return "Invalid index! \nPlease enter a valid index.";
    }

    /**
     * Solution to delay exiting the program below is adapted from
     * https://stackoverflow.com/questions/27334455/how-to-close-a-stage-after-a-certain-amount-of-time-javafx.
     * Exits the program after 2.5 seconds.
     */
    public static void exitProgram() {
        PauseTransition delay = new PauseTransition(Duration.seconds(2.5));
        delay.setOnFinished(event -> javafx.application.Platform.exit());
        delay.play();
    }

    /**
     * @return invalid Snoozed Task Message
     */
    public String invalidSnoozeTaskMessage() {
        return "You can only snooze DEADLINES and EVENTS only!!!";
    }
}
