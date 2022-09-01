package ted.ui;

/**
 * Represents the user interaction feature of the bot. A <code>Ui</code> object reads the user input
 * and responds to the user.
 */
public class Ui {
    /**
     * Returns farewell message to user.
     *
     * @return farewell message string.
     */
    public String showExit() {
        return "Goodbye! Have a pleasant day |._.|";
    }

    /**
     * Returns response to user command to delete task.
     *
     * @param task task user deletes.
     * @param size size of tasklist.
     * @return delete response string.
     */
    public String deleteResponse(String task, int size) {
        return "Done! Task deleted:\n" + task + "\nremaining task count: " + size + "\n";
    }

    /**
     * Returns response to user command to mark task.
     *
     * @param task task user marks.
     * @return mark response string.
     */
    public String markResponse(String task) {
        return "Great! Task done:\n" + task + "\n";
    }

    /**
     * Returns response to user command to unmark task.
     *
     * @param task task user unmarks.
     * @return unmark response string.
     */
    public String unmarkResponse(String task) {
        return "Aw :( Task undone:\n" + task + "\n";
    }

    /**
     * Returns response to user command to add task.
     *
     * @param task task user adds.
     * @param size size of tasklist.
     * @return add response string.
     */
    public String addResponse(String task, int size) {
        return "added to tasklist:\n" + task + "\ntask count: " + size + "\n";
    }

    /**
     * Returns response to user command to list tasks.
     *
     * @param tasks string of tasks in task list.
     * @return list response string.
     */
    public String listResponse(String tasks) {
        return "Your tasklist:\n" + tasks;
    }

    /**
     * Returns response to user command to find tasks.
     *
     * @param tasks tasks that match keyword in task list.
     * @return find response string.
     */
    public String findResponse(String tasks) {
        return "Here are the matching tasks in your list:\n" + tasks;
    }
}
