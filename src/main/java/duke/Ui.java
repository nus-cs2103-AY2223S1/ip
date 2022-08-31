package duke;

import duke.task.Task;

/**
 * Ui acts as the control center for interacting with the user.
 * Printing messages, errors and reading user input are done in this class.
 */
public class Ui {
    /**
     * Show unknown command messages
     */
    public String showUnknownMessage() {
        return ("I'm sorry, "
                + "but I don't know what that means :-(");
    }

    /**
     * Show add messages
     *
     * @param task Task added
     * @param size Size of TaskList
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
     * Show delete messages.
     *
     * @param task Task that is going to be deleted
     * @param size Size of TaskList
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
     * Show exit messages when the program is going to end.
     */
    public String showExitMessage() {
        return ("Bye. Hope to see you again soon!");
    }

    /**
     * Show header of list command.
     */
    public String showList() {
        return ("Your List :");
    }

    /**
     * Show the details of task.
     *
     * @param index Index of task
     * @param task Task to print
     */
    public String showTask(int index, Task task) {
        return (index + "." + task.toString());
    }

    /**
     * Show the details of taskList
     *
     * @param taskList TaskList to print
     * @return string of taskList
     */
    public String showTaskList(TaskList taskList) {
        String out = "";
        for (int i = 0; i < taskList.getSize(); i++) {
            out += showTask(i + 1, taskList.getTask(i));
            out += "\n";
        }
        return out;
    }

    /**
     * Show mark messages.
     *
     * @param task Task marked as done
     */
    public String showMarkMessage(Task task) {
        return "Nice! I've marked this task as done:\n  " + task.toString();
    }

    /**
     * Show unmark messages.
     *
     * @param task Task unMarked as done.
     */
    public String showUnmarkMessage(Task task) {
        return "OK, I've marked this task as not done yet:\n  " + task.toString();
    }

    /**
     * Show find message when tasks are found.
     */
    public String showFindMessage() {
        return ("Here are the matching tasks in your list:\n");
    }

    /**
     * Show find message when no task is found.
     */
    public String showFindEmptyMessage() {
        return ("There are no matching task in your list\n");
    }
}
