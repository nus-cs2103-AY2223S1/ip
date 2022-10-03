package duke;

/**
 * Class which handles the interaction with the user.
 *
 */
public class Ui {

    /**
     * Returns all the tasks from the TaskList.
     *
     * @param taskList
     * @return all tasks
     */
    public String getTasks(TaskList taskList) {
        return taskList.toString();
    }

    /**
     * Returns message when a task is marked as done.
     *
     * @param task
     * @return message
     */
    public String markTask(Task task) {
        return String.format("Good one Jim! I've marked this task as done:\n%s", task.toString());
    }

    /**
     * Returns message when a task is marked as undone.
     *
     * @param task
     * @return message
     */
    public String unmarkTask(Task task) {
        return String.format("OK Jim, I've marked this task as not done yet:\n%s", task.toString());
    }

    /**
     * Returns a message after user exits program.
     *
     * @return message
     */
    public String bye() {
        return "Bye Jim!";
    }

    /**
     * Returns a message after a new task is added.
     *
     * @param task
     * @param taskList
     * @return message
     */
    public String taskAdded(Task task, TaskList taskList) {
        return String.format("added: %s\nNow you have %d tasks in the list", task.toString(), taskList.size());
    }

    /**
     * Returns a message after a task is deleted.
     *
     * @param task
     * @param taskList
     * @return message
     */
    public String taskDeleted(Task task, TaskList taskList) {
        return String.format("Noted Jim. I've removed this task:\n%s\nNow you have %d tasks in the list",
                task.toString(), taskList.size());
    }

    /**
     * Looks for tasks which contain the keyword and returns them in String format
     *
     * @param keyword
     * @param tasks
     * @return tasks which contain the keyword
     */
    public String getMatchingTasks(String keyword, TaskList tasks) {
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.getTask(i).getName().contains(keyword)) {
                str.append(tasks.getTask(i));
            }
        }
        return str.toString();
    }
}
