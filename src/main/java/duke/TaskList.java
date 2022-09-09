package duke;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Represents a list of tasks.
 */
public class TaskList {

    private final ArrayList<Task> taskList;

    /**
     * Creates a list of tasks with existing tasks.
     * @param taskList A list of tasks to initialise with.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Creates an empty list of tasks.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Adds a new task to the end of the list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Lists out all the tasks in the list, line by line, as a single string.
     *
     * @return A line by line string representation of every task in the list.
     */
    public String list() {
        if (taskList.size() == 0) {
            return "No tasks yet";
        }
        StringBuilder listOfTasks = new StringBuilder();
        for (Task task : taskList) {
            listOfTasks.append(task);
            listOfTasks.append("\n");
        }
        int last = listOfTasks.lastIndexOf("\n");
        listOfTasks.deleteCharAt(last);
        return listOfTasks.toString();
    }

    /**
     * Returns a Task specified by its position in the list.
     *
     * @param taskNum The index of the task to retrieve. The first task is 1.
     * @return The Task at the specified index.
     * @throws IndexOutOfBoundsException if there is no task at the input index.
     */
    public Task getTask(int taskNum) throws IndexOutOfBoundsException {
        try {
            return taskList.get(taskNum - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("No such task!");
        }
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Marks a specified task in the list as complete.
     *
     * @param taskNum The task to be marked. The first task is task 1.
     * @return The task that was marked.
     * @throws IndexOutOfBoundsException if taskNum is not a valid task number.
     */
    public Task mark(int taskNum) throws IndexOutOfBoundsException {
        try {
            Task task = taskList.get(taskNum - 1);
            task.markComplete();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("No such task!");
        }
    }

    /**
     * Marks a specified task in the list as incomplete.
     *
     * @param taskNum The task to be marked. The first task is task 1.
     * @return The task that was marked.
     * @throws IndexOutOfBoundsException if taskNum is not a valid task number.
     */
    public Task unmark(int taskNum) throws IndexOutOfBoundsException {
        try {
            Task task = taskList.get(taskNum - 1);
            task.markIncomplete();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("No such task!");
        }
    }

    /**
     * Deletes a specified task from the list.
     *
     * @param taskNum The task to be deleted. The first task is task 1.
     * @return The task that was deleted.
     * @throws IndexOutOfBoundsException if taskNum is not a valid task number.
     */
    public Task delete(int taskNum) throws IndexOutOfBoundsException {
        try {
            return taskList.remove(taskNum - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("No such task!");
        }
    }

    /**
     * Filters the task list to only contain tasks with the specified keyword in the task description.
     *
     * @param keyword The keyword to search for in the tasks.
     * @return A TaskList that contains only tasks that contain the keyword.
     */
    public TaskList filter(String keyword) {
        TaskList filteredTaskList = new TaskList();
        for (Task task : taskList) {
            if (task.getTaskDescription().contains(keyword)) {
                filteredTaskList.add(task);
            }
        }
        return filteredTaskList;
    }

}
