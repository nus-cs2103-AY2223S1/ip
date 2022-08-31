package ava;

import ava.processor.TaskList;
import ava.task.Task;

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
}
