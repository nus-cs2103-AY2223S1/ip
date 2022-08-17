import java.util.LinkedList;

/**
 * A task list is used to store tasks.
 */
public class TaskList {
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Constructor for a task list.
     */
    public TaskList() {
        this.tasks = new LinkedList<>();
    }

    /**
     * Lists all the tasks entered thus far by the user.
     * Will print 'No tasks' if no tasks are found.
     */
    public String listTasks() {
        String taskList = "";
        int count = 0;
        for (Task task : tasks) {
            count++;
            taskList += String.format("\n%d. %s", count, task);
        }
        return (count != 0 ? "Here are the tasks in your list:\n"
                + taskList.substring(1) : "No tasks");
    }

    /**
     * Checks that the specified task is a task that exists.
     *
     * @param i The task number of the task to be verified
     * @return True if the task exists, false otherwise
     */
    public boolean isValidTask(int i) throws DukeException {
        boolean isValid = i <= tasks.size();
        if (!isValid) throw new DukeException("Hm... Duke can't find this task.");
        return true;
    }

    /**
     * Marks the specified task number as done, if it exists.
     *
     * @param i The task number to be marked as done
     */
    public String markTaskDone(int i) throws DukeException {
        isValidTask(i);
        Task task = tasks.get(i - 1);
        task.markTaskAsDone();
        return (String.format("Nice! I've marked this task as done:\n %s", task));
    }

    /**
     * Marks the specified task number as not done, if it exists.
     *
     * @param i The task number to be marked as not done
     * @throws DukeException An exception is thrown when the specified task does not exist
     */
    public String markTaskNotDone(int i) throws DukeException {
        isValidTask(i);
        Task task = tasks.get(i - 1);
        task.markTaskAsUndone();
        return (String.format("OK, I've marked this task as not done yet:\n %s", task));
    }

    /**
     * Adds a specified task to the task list.
     *
     * @param task The task to be added to the task list
     * @return String representation of task completion, displays task added and number
     *         of tasks in the task list
     */
    public String addTask(Task task) {
        tasks.add(task);
        return (String.format("Got it. I've added this task:\n" +
                "  %s\nNow you have %d tasks in the list.", task, tasks.size()));
    }

    /**
     * Deletes a specified task from the task list.
     *
     * @param i The task number of the task to be deleted from the task list
     * @return String representation of the task deletion, displays task removed and
     *         the number of remaining tasks in the task list
     * @throws DukeException Exception thrown when the specified task does not exist
     */
    public String deleteTask(int i) throws DukeException {
        isValidTask(i);
        Task task = tasks.remove(i - 1);
        return (String.format("Noted. I've removed this task:\n" +
                "  %s\nNow you have %d tasks in the list.", task, tasks.size()));
    }
}
