package duke.tasklist;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import duke.domain.task.Task;
import duke.domain.task.TaskIndex;
import duke.enums.SortTaskEnum;
import duke.exceptions.TaskNotFoundException;

/**
 * The type Task list.
 */
public class TaskList {

    private final Set<Task> taskSet;
    private List<Task> taskList;

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
     * @param taskIndex
     *            Find the task in the list
     * @return A task object
     * @throws TaskNotFoundException
     *             When the task in the given index does not exist
     */
    public Task markTask(TaskIndex taskIndex) throws TaskNotFoundException {
        try {
            Task curr = taskList.get(taskIndex.getZeroBased());
            curr.setComplete();
            return curr;
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFoundException();
        }
    }

    /**
     * The unmarkTask function marks a task as incomplete.
     *
     * @param taskIndex
     *            Find the task in the list
     * @return The task that was un-marked
     * @throws TaskNotFoundException
     *             When the task in the given index does not exist
     */
    public Task unmarkTask(TaskIndex taskIndex) throws TaskNotFoundException {
        try {
            Task curr = taskList.get(taskIndex.getZeroBased());
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
     * @param taskIndex
     *            Find the task to be deleted
     * @return The task that was deleted
     * @throws TaskNotFoundException
     *             When the task in the given index does not exist
     */
    public Task deleteTask(TaskIndex taskIndex) throws TaskNotFoundException {
        try {
            Task curr = taskList.get(taskIndex.getZeroBased());
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
        return this.taskList
                .stream()
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
        return convertTaskListToString(getTaskList());
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
                this.taskList
                        .stream()
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
        List<Task> filteredTasks = this.taskList
                .stream()
                .filter(task -> task.isAfter(dateTime)).sorted().collect(Collectors.toList());
        return convertTaskListToString(filteredTasks);
    }

    /**
     * The searchTasks function searches the task list for tasks that contain the
     * search term.
     *
     * @param searchTerms
     *            The search terms
     * @return A string
     */
    public String searchTasks(String... searchTerms) {
        List<Task> filteredTasks = this.taskList
                .stream()
                .filter(task -> task.textContains(searchTerms))
                .collect(Collectors.toList());
        return convertTaskListToString(filteredTasks);
    }

    /**
     * The sortTaskList function sorts the taskList in ascending order.
     *
     * @param direction
     *            Determine whether the sort is ascending or descending
     */
    public void sortTaskList(SortTaskEnum direction) {
        List<Task> sortedTaskList = new ArrayList<>(this.taskList);
        Collections.sort(sortedTaskList);
        if (direction == SortTaskEnum.DESC) {
            Collections.reverse(sortedTaskList);
        }
        setTaskList(sortedTaskList);
    }

    /**
     * The setTaskList function sets the taskList field of this class to the given
     * list.
     *
     * @param taskList
     *            Set the tasklist field
     */
    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * The getTaskList function returns the list of tasks.
     *
     * @return A list of tasks
     */
    public List<Task> getTaskList() {
        return taskList;
    }
}
