package duke.tasklist;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import duke.domain.Task;
import duke.exceptions.TaskNotFoundException;

/**
 * The type Task list.
 */
public class TaskList {

    private final Set<Task> taskSet;
    private final List<Task> taskList;

    /**
     * Instantiates a new Task list.
     *
     * @param initTaskList
     *            Initial Task List
     */
    public TaskList(List<Task> initTaskList) {
        this.taskList = initTaskList;
        this.taskSet = new HashSet<>();
    }

    /**
     * Marks a task as complete.
     *
     * @param idx
     *            Find the task in the list
     * @return A task object
     * @throws TaskNotFoundException
     *             When the task in the given index does not exist
     */
    public Task markTask(int idx) throws TaskNotFoundException {
        try {
            Task curr = taskList.get(idx);
            curr.setComplete();
            return curr;
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFoundException();
        }
    }

    /**
     * The unmarkTask function marks a task as incomplete.
     *
     * @param idx
     *            Find the task in the list
     * @return The task that was un-marked
     * @throws TaskNotFoundException
     *             When the task in the given index does not exist
     */
    public Task unmarkTask(int idx) throws TaskNotFoundException {
        try {
            Task curr = taskList.get(idx);
            curr.setIncomplete();
            return curr;
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFoundException();
        }
    }

    /**
     * The addTask function adds a new task to the list of tasks.
     *
     * @param newTask
     *            Pass the task object to be added to the tasklist
     */
    public void addTask(Task newTask) {
        boolean addFlag = taskSet.add(newTask);
        if (addFlag) {
            taskList.add(newTask);
        }
    }

    /**
     * The deleteTask function removes the task at index idx from the list of tasks.
     *
     * @param idx
     *            Find the task to be deleted
     * @return The task that was deleted
     * @throws TaskNotFoundException
     *             When the task in the given index does not exist
     */
    public Task deleteTask(int idx) throws TaskNotFoundException {
        try {
            Task curr = taskList.get(idx);
            taskList.remove(curr);
            taskSet.remove(curr);
            return curr;
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFoundException();
        }
    }

    /**
     * Export task list list.
     *
     * @return the list
     */
    public List<String> exportTaskList() {
        return this.taskList.stream()
                .map(Task::exportString)
                .collect(Collectors.toList());
    }

    /**
     * The convertTaskListToString function takes a list of tasks and converts it to
     * a string.
     *
     * @param tasks
     *            List of tasks
     * @return A string representation of the list of tasks
     */
    public String convertTaskListToString(List<Task> tasks) {
        String result = "";
        int counter = 1;
        for (Task task : tasks) {
            result = String.format("%s%d%s%s%s", result, counter, ": ", task, "\n");
            counter++;
        }
        return result;
    }

    /**
     * The outputTasksString function returns a string representation of the task
     * list.
     *
     * @return A string representation of the task list
     */
    public String outputTasksString() {
        return convertTaskListToString(this.taskList);
    }

    /**
     * The outputTasksBeforeString function takes a LocalDateTime dateTime as an
     * argument and returns
     * a String containing the tasks that are due before the given dateTime.
     *
     * @param dateTime
     *            Filter out tasks that are before the datetime parameter
     * @return A string of tasks that are before a given date
     */
    public String outputTasksBeforeString(LocalDateTime dateTime) {
        return convertTaskListToString(
                this.taskList.stream()
                        .filter(task -> task.isBefore(dateTime))
                        .collect(Collectors.toList()));
    }

    /**
     * The outputTasksAfterString function takes a LocalDateTime dateTime as an
     * argument and returns
     * a String containing the tasks that are due after the given dateTime.
     *
     * @param dateTime
     *            Filter the tasks that are after a certain date
     * @return A string containing the tasks that are after the datetime
     */
    public String outputTasksAfterString(LocalDateTime dateTime) {
        return convertTaskListToString(
                this.taskList.stream()
                        .filter(task -> task.isAfter(dateTime))
                        .collect(Collectors.toList()));
    }

    /**
     * The searchTasks function searches the task list for tasks that contain the
     * search term.
     *
     * @param searchTerm
     *            Search for a specific task
     * @return A string
     */
    public String searchTasks(String searchTerm) {
        return convertTaskListToString(
                this.taskList.stream()
                        .filter(task -> task.textContains(searchTerm))
                        .collect(Collectors.toList()));
    }
}
