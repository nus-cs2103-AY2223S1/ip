package ava;

import ava.command.Command;
import ava.task.Task;
import ava.util.TaskList;

/**
 * Class to represent the user interfaces.
 */
public class Ui {
    /**
     * Shows exit message at the end of the program.
     *
     * @return Message printed when task is added.
     */
    public String printExit() {
        return "Bye. Good luck on your tasks!\n *shutting down.....*";
    }

    /**
     * Shows the added task.
     *
     * @param tasks TaskList.
     * @param numOfTask Number of Tasks.
     * @return Message printed when task is added.
     */
    public String showAddOnTask(TaskList tasks, int numOfTask) {
        return "I've added this task:\n " + tasks.get(numOfTask)
                + "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Shows the deleted task.
     *
     * @param tasks Tasklist.
     * @param taskDeleted Deleted task.
     * @return Message printed when task is deleted.
     */
    public String showDeleteTask(TaskList tasks, Task taskDeleted) {
        return "Noted. I've removed this task:\n" + taskDeleted
                + "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Shows the marked done task.
     *
     * @param tasks TaskList.
     * @param num Index of the specified task.
     * @return Message printed when task is marked done.
     */
    public String showDoneTask(TaskList tasks, int num) {
        return "Nice job! I've marked this task as done:\n" + tasks.get(num);
    }

    /**
     * Shows the marked undone task.
     *
     * @param tasks TaskList.
     * @param num Index of the specified task.
     * @return Message printed when task is marked undone.
     */
    public String showUndoneTask(TaskList tasks, int num) {
        return "Awh I've marked this task as undone:\n" + tasks.get(num);
    }

    /**
     * Shows the list of tasks.
     *
     * @param tasks TaskList.
     * @return List of tasks
     */
    public String showTasks(TaskList tasks) {
        if (tasks.size() < 1) {
            return "No tasks yet!";
        } else {
            return Command.formatOutput("Here are the tasks in your list:", tasks.toStringArray())
                    + "\n\nJiayous!";
        }
    }

    /**
     * Shows the list of task(s) that match the specified predicate.
     *
     * @param tasks TaskList.
     * @param predicate The predicate.
     * @return List of task(s) matching the given predicate.
     */
    public String showFindTasks(TaskList tasks, String predicate) {
        return Command.formatOutput("Here are the matching tasks in your list:",
                tasks.filter((task) -> task.matchKeyword(predicate)).toStringArray());
    }

    /**
     * Shows the list of tasks, sorted by date.
     *
     * @param tasks TaskList.
     * @return List of tasks
     */
    public String showSortedTasks(TaskList tasks) {
        if (tasks.size() < 1) {
            return "No tasks yet!";
        } else {
            return Command.formatOutput("Here are the tasks ordered by date:", tasks.sort().toStringArray())
                    + "\n\nJiayous!";
        }
    }
}
