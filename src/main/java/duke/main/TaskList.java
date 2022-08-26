package duke.main;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents a tasklist that is used to store all tasks that is input into Duke
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructor for TaskList
     *
     * @param taskList
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * AddTasks method which adds a task into the TaskList
     *
     * @param task
     */
    public void addTasks(Task task) {
        taskList.add(task);
    }

    /**
     * deleteTasks method which deletes a task into the TaskList based on its index
     *
     * @param index
     */
    public void deleteTasks(int index) {
        taskList.remove(index);
    }

    /**
     * Get a task object at a specified index within duke.main.TaskList
     *
     * @param index
     * @return Task
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Get the length of the taskList
     *
     * @return int
     */
    public int length() {
        return taskList.size();
    }

}
