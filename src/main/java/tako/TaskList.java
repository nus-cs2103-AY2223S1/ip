package tako;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import tako.task.Task;

/**
 * Tracks and operates on a task list.
 */
public class TaskList {
    private List<Task> tasks;
    private HashMap<String, List<Task>> descriptionToTaskMap;

    /**
     * Constructor for an empty task list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
        descriptionToTaskMap = new HashMap<>();
    }

    /**
     * Constructor for task list with existing tasks.
     *
     * @param tasks List of tasks to initialize with.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>();
        descriptionToTaskMap = new HashMap<>();
        for (Task task: tasks) {
            add(task);
        }
    }

    /**
     * Adds a task to the task list.
     *
     * @param task Task to add.
     */
    public void add(Task task) {
        this.tasks.add(task);
        String description = task.getDescription();
        String[] splitDescription = description.split(" ");
        for (String s: splitDescription) {
            if (!descriptionToTaskMap.containsKey(s)) {
                descriptionToTaskMap.put(s, new ArrayList<>());
            }
            List<Task> tasks = descriptionToTaskMap.get(s);
            assert tasks != null : "Tasks should be in hashMap.";
            tasks.add(task);
        }
    }

    /**
     * Marks a task.
     *
     * @param taskNumber Position of the task in the task list.
     * @throws TakoException If the task number is < 0 or
     *                       greater than the number of tasks in the task list.
     */
    public void mark(int taskNumber) throws TakoException {
        if (taskNumber < 0 || taskNumber > tasks.size() - 1) {
            throw new TakoException("The task number to mark does not exist.");
        }
        Task task = tasks.get(taskNumber);
        assert task != null : "Task should be in list.";
        task.markAsDone();
    }

    /**
     * Removes a task.
     *
     * @param taskNumber Position of the task in the task list.
     * @throws TakoException If the task number is < 0 or
     *                       greater than the number of tasks in the task list.
     */
    public Task remove(int taskNumber) throws TakoException {
        if (taskNumber < 0 || taskNumber > tasks.size() - 1) {
            throw new TakoException("The task number to delete does not exist.");
        }
        Task task = tasks.remove(taskNumber);
        assert task != null : "Task should be in list.";
        String description = task.getDescription();
        String[] splitDescription = description.split(" ");
        for (String s: splitDescription) {
            List<Task> tasks = descriptionToTaskMap.get(s);
            tasks.remove(task);
        }
        return task;
    }

    /**
     * Finds a task containing the keyword in their description.
     *
     * @param keyword Keyword in the description of tasks.
     * @return Task list with tasks containing the keyword in their descriptions.
     */
    public TaskList find(String keyword) {
        List<Task> tasks = descriptionToTaskMap.get(keyword);
        return tasks == null ? new TaskList() : new TaskList(tasks);
    }

    /**
     * Sorts the task list based on sort method and order.
     *
     * @param sortBy Valid sort method.
     * @param isAscending Boolean for sorted in ascending order.
     */
    public void sort(Sort sortBy, boolean isAscending) {
        if (sortBy == Sort.DATE) {
            Comparator<Task> dateComparator = Comparator.comparing(Task::getDateTime);
            tasks.sort(dateComparator);
        } else if (sortBy == Sort.ALPHABET) {
            Comparator<Task> alphabetComparator = Comparator.comparing(Task::getDescription);
            tasks.sort(alphabetComparator);
        } else {
            assert false : "Sort Method does not exist: " + sortBy;
        }

        if (!isAscending) {
            Collections.reverse(tasks);
        }
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public int getSize() {
        return tasks.size();
    }
}
