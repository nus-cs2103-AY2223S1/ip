package roger.tasks;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import roger.storage.StorageParser;


/**
 * Encapsulates the list of tasks in a Roger program.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Create an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Create a TaskList, and initialise it with a list of tasks.
     *
     * @param tasks The tasks to be added.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Add a task.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Delete a task.
     *
     * @param taskNum The taskNum of the task to be deleted.
     * @return The deleted task.
     */
    public Task delete(int taskNum) {
        return this.tasks.remove(taskNum - 1);
    }

    /**
     * Get a task from its taskNum.
     *
     * @param taskNum The taskNum of the task to be retrieved.
     * @return The desired task.
     */
    public Task get(int taskNum) {
        return this.tasks.get(taskNum - 1);
    }

    /**
     * Get the number of tasks.
     *
     * @return The number of tasks in the TaskList.
     */
    public int getLength() {
        return this.tasks.size();
    }

    /**
     * Mark a task as done.
     *
     * @param taskNum The taskNum of the task to be marked.
     */
    public void markAsDone(int taskNum) {
        this.get(taskNum).markAsDone();
    }

    /**
     * Unmark a task as done.
     *
     * @param taskNum The taskNum of the task to be unmarked.
     */
    public void unmarkAsDone(int taskNum) {
        this.get(taskNum).unmarkAsDone();
    }

    /**
     * Checks if the TaskList is empty.
     *
     * @return true only if the TaskList is empty.
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    /**
     * Returns the taskStrings of the tasks.
     *
     * @return A list of the taskStrings of the tasks.
     */
    public List<String> toTaskStrings() {
        return this.tasks.stream()
                .map(StorageParser::toTaskString)
                .collect(Collectors.toList());
    }

    /**
     * Returns the string representations of the tasks.
     *
     * @return A list of the string representations of the tasks.
     */
    public List<String> toDisplayStrings() {
        return this.tasks.stream()
                .map(Task::toString)
                .collect(Collectors.toList());
    }

    /**
     * Filter the tasks by date.
     *
     * @param date The date to filter on.
     * @return A list of tasks that occur on that date.
     */
    public List<Task> filter(LocalDate date) {
        List<Task> filtered = new ArrayList<>();

        for (int i = 0; i < this.tasks.size(); ++i) {
            Task task = this.tasks.get(i - 1);
            if (task instanceof Event) {
                Event event = (Event) task;
                if (event.isOnDate(date)) {
                    filtered.add(event);
                }
            } else if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.isOnDate(date)) {
                    filtered.add(deadline);
                }
            }
        }

        return filtered;
    }

    /**
     * Filters the tasks based on whether the query string appeears in the task name.
     *
     * @param query The query string to filter on.
     * @return A list of tasks whose name contains the query string.
     */
    public List<Task> search(String query) {
        return this.tasks.stream()
                .filter(task -> task.getName().contains(query))
                .collect(Collectors.toList());
    }

    /**
     * Returns the taskNum of the task if it is in the list, or -1 otherwise.
     *
     * @param task The task to get the taskNum for.
     * @return The taskNum of the task, or -1 if task is not in list.
     */
    public int getTaskNum(Task task) {
        int taskIndex = this.tasks.indexOf(task);
        return taskIndex == -1 ? -1 : taskIndex + 1;
    }
}
