package duke.main;

import java.util.ArrayList;

import duke.task.Task;


/**
 * Represents a tasklist that is used to store all tasks that is input into duke.main.Duke
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
     * Adds tasks into tasklist
     *
     * @param task
     */
    public void addTasks(Task task) {
        taskList.add(task);
    }

    /**
     * Removes tasks from tasklist
     *
     * @param index
     */
    public void deleteTasks(int index) {
        taskList.remove(index);
    }

    /**
     * Returns tasks found in the tasklist
     *
     * @param index
     * @return Task
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Returns the length of the taskList
     *
     * @return int
     */
    public int length() {
        return taskList.size();
    }

}
