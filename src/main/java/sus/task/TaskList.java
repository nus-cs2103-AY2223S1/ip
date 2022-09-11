package sus.task;

import java.util.List;
import java.util.stream.Collectors;

import sus.DukeException;
import sus.common.Messages;
import sus.common.Utils;

/**
 * Represents the task list and methods to manage the task list.
 */
public class TaskList {

    private final List<Task> tasks;

    /**
     * Constructor for a new Task List.
     *
     * @param tasks list of tasks if preloaded
     */
    public TaskList(List<Task> tasks) {
        assert tasks != null;
        this.tasks = tasks;
    }

    public int getNumberOfTasks() {
        return tasks.size();
    }

    /**
     * Creates a new to-do and add it to the task list.
     *
     * @param description description of the to-do
     */
    public Task addTodo(String description) {
        Task task = new Todo(description);
        tasks.add(task);
        return task;
    }

    /**
     * Creates a new deadline and add it to the task list.
     *
     * @param description description of the deadline
     * @param dueDate date deadline is to be completed by
     */
    public Task addDeadline(String description, String dueDate) throws DukeException {
        Task task = new Deadline(description, Utils.parseDate(dueDate));
        tasks.add(task);
        return task;
    }

    /**
     * Creates a new event and add it to the task list.
     *
     * @param description description of the event
     * @param timeFrame time frame of the event
     */
    public Task addEvent(String description, String timeFrame) throws DukeException {
        Task task = new Event(description, Utils.parseDate(timeFrame));
        tasks.add(task);
        return task;
    }

    /**
     * Delete a task from the task list.
     *
     * @param inputIndex visible index of the task
     */
    public Task deleteTask(int inputIndex) throws DukeException {
        int targetIndex = checkIndex(inputIndex);
        return tasks.remove(targetIndex);
    }

    /**
     * Mark a task of the task list as done.
     *
     * @param inputIndex visible index of the task
     */
    public Task markTask(int inputIndex) throws DukeException {
        Task targetTask = tasks.get(checkIndex(inputIndex));
        if (targetTask.isDone()) {
            throw new DukeException(Messages.MESSAGE_TASK_ALREADY_MARKED);
        }
        targetTask.setDone(true);
        return targetTask;
    }

    /**
     * Unmark a task of the task list (not done).
     *
     * @param inputIndex visible index of the task
     */
    public Task unmarkTask(int inputIndex) throws DukeException {
        Task targetTask = tasks.get(checkIndex(inputIndex));
        if (!targetTask.isDone()) {
            throw new DukeException(Messages.MESSAGE_TASK_ALREADY_UNMARKED);
        }
        targetTask.setDone(false);
        return targetTask;
    }

    /**
     * Update a task with a new description.
     *
     * @param inputIndex index of the task to be updated
     * @param inputDescription description to update task with
     * @return updated Task
     */
    public Task updateTask(int inputIndex, String inputDescription) throws DukeException {
        Task targetTask = tasks.get(checkIndex(inputIndex));
        targetTask.setDescription(inputDescription);
        return targetTask;
    }

    /**
     * Finds tasks in the task list with a keyword.
     */
    public List<Task> findTasks(String keyword) {
        // Use of Streams
        return tasks.stream()
                .filter(t -> String.valueOf(t).contains(keyword))
                .collect(Collectors.toList());
    }

    /**
     * Convert the given list of tasks into a string to further process.
     */
    public static String convertListOfTasksToString(List<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(String.format("%d.%s\n\t", i + 1, tasks.get(i)));
        }
        return sb.toString().stripTrailing();
    }

    /**
     * Converts the list of tasks to a string to be saved.
     */
    public String encodeToString() {
        StringBuilder sb = new StringBuilder();
        for (Task t : tasks) {
            sb.append(t.encodeToString()).append("\n");
        }
        return sb.toString();
    }

    private int checkIndex(int inputIndex) throws DukeException {
        int actualListIndex = inputIndex - 1;
        if (actualListIndex >= tasks.size() || actualListIndex < 0) {
            throw new DukeException(Messages.MESSAGE_INVALID_NUMBER);
        }
        return actualListIndex;
    }

    @Override
    public String toString() {
        return TaskList.convertListOfTasksToString(tasks);
    }

}
