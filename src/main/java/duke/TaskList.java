package duke;

import java.util.List;

public class TaskList {
    private final static String MESSAGE_INVALID_TASK_NUMBER = "That is not a valid task number!";
    private List<Task> tasks;

    public TaskList(Storage storage) {
        tasks = storage.readDataFile();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task task to add
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Marks a task as complete or incomplete.
     *
     * @param displayedIndex task number displayed in UI
     * @param done           whether task is completed or not
     * @return deleted task
     * @throws DukeException
     */
    public Task markTask(int displayedIndex, boolean done) throws DukeException {
        try {
            Task task = tasks.get(displayedIndex - 1);
            if (done) {
                task.mark();
            } else {
                task.unmark();
            }
            return task;
        } catch (Exception e) {
            throw new DukeException(MESSAGE_INVALID_TASK_NUMBER);
        }
    }

    /**
     * Deletes a task from the list.
     *
     * @param displayedIndex task number displayed in UI
     * @return deleted task
     * @throws DukeException
     */
    public Task deleteTask(int displayedIndex) throws DukeException {
        try {
            Task task = tasks.remove(displayedIndex - 1);
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(MESSAGE_INVALID_TASK_NUMBER);
        }
    }

    /**
     * Returns number of tasks in the task list
     *
     * @return number of tasks in the task list
     */
    public int getNumberOfTasks() {
        return tasks.size();
    }

    /**
     * Searches for tasks that match a given search string.
     *
     * @param searchString search string
     * @return message containing list of matching tasks
     */
    public String searchTasks(String searchString) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        for (Task task : tasks) {
            if (task.description.contains(searchString)) {
                sb.append(String.format("%s\n", task));
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        if (tasks.size() == 0) {
            return "You currently have no tasks.";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                int displayIndex = i + 1;
                String taskString = tasks.get(i).toString();
                sb.append(String.format("%d. %s\n", displayIndex, taskString));
            }
            return sb.toString();
        }
    }
}
