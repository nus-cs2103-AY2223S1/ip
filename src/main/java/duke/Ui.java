package duke;

import java.util.ArrayList;

import duke.task.Task;




/**
 * The Ui class deals with interactions with users.
 */
public class Ui {


    private String description;

    /**
     * Display description for GUI.
     *
     * @return String that represents GUI description.
     */
    public String displayDescription() {
        assert description != null : "Ui::displayDescription has null description";
        return description;
    }


    /**
     * Show the Duke's welcome message to the user.
     */
    public void showWelcome() {

        String logo = "Mr. Slave";
        logo = "Hello from\n" + logo + "\n";
        logo = logo + "WHY ARE YOU BACK AGAIN ?_?";
        description = logo;
    }

    /**
     * Show loading error to the user.
     */
    public void showLoadingError() {
        description = "File not found.";
    }


    /**
     * Show a message that a specified task has been deleted from the list.
     *
     * @param t The specified task deleted.
     * @param taskList The task list the specified task is deleted from.
     */
    public void showDelete(Task t, TaskList taskList) {
        String s = "";
        s = "Noted. I've removed this task:\n";
        s = s + t.toString() + "\n";
        if (taskList.getTasksNumber() == 1) {
            s = s + "Now you have 1 task in the list.\n";
        } else {
            s = s + "Now you have " + taskList.getTasksNumber() + " tasks in the list.\n";
        }
        description = s;

    }

    /**
     * Show a message that a specified task has been added to the list.
     *
     * @param t The specified task to be added.
     * @param taskList The task list the specified task is added to.
     */
    public void showAddTask(Task t, TaskList taskList) {
        String s = "";
        s = "Got it. I've added this task:\n";
        s = s + t.toString() + "\n";
        if (taskList.getTasksNumber() == 1) {
            s = s + "Now you have 1 task in the list.\n";
        } else {
            s = s + "Now you have " + taskList.getTasksNumber() + " tasks in the list.\n";
        }
        description = s;
    }

    /**
     * Show a message that a specified task has been marked done.
     *
     * @param t The specified task to be marked done.
     */
    public void showMark(Task t) {
        String s = "Nice! I've marked this task as done:\n";
        s = s + t + "\n";
        description = s;
    }

    /**
     * Show a message that a specified task has been marked undone.
     *
     * @param t The specified task to be marked undone.
     */
    public void showUnmark(Task t) {
        String s = "Ok, I've marked this task as not done yet:\n";
        s = s + t + "\n";
        description = s;
    }

    /**
     * Show all the tasks in the task list.
     *
     * @param taskList The specified task list.
     */
    public void showTasks(ArrayList<Task> taskList) {
        String s = "";
        s = "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.size(); i++) {
            Task currTask = taskList.get(i);
            s = s + (i + 1) + "." + currTask.toString() + "\n";
        }
        description = s;
    }

    /**
     * Show all tasks on a specified date.
     *
     * @param onDateTasks ArrayList that represents all tasks on date.
     */
    public void showGetDate(ArrayList<Task> onDateTasks) {
        String s = "";
        for (Task t : onDateTasks) {
            s = s + t + "\n";
        }
        int count = onDateTasks.size();
        if (count == 0) {
            s = s + "YAY! You have no deadlines/events on this day.";
        } else if (onDateTasks.size() == 1) {
            s = s + "Shag man. You have " + count + " deadline/event on this day.";
        } else {
            s = s + "Shag man. You have " + count + " deadlines/events on this day.";
        }
        description = s;
    }

    /**
     * Show all tasks found with specified keyword.
     *
     * @param foundTasks ArrayList that represents tasks containing specified keyword.
     */
    public void showFoundTasks(ArrayList<Task> foundTasks) {
        String s = "";
        int count = foundTasks.size();
        if (count == 0) {
            s = "There is no matching task in your list.\n";
        } else {
            s = "Here are the matching tasks in your list:\n";
        }

        for (int i = 0; i < count; i++) {
            s = s + (i + 1) + "." + foundTasks.get(i) + "\n";
        }
        description = s;
    }

    /**
     * Show error message.
     *
     * @param error The description for the error.
     */
    public void showError(String error) {
        description = "OOPS!!!" + error;
    }

    public void showPriority(Task t, String priority) {
        description = t + " has been marked as " + priority.toUpperCase() + " priority.";
    }

    /**
     * Show bye message to the user.
     */
    public void showBye() {
        description = "Bye, hope to see you again next time!";
    }

}
