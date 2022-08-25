package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor for duke.TaskList, which stores task entered by user in duke.Duke
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }


    public TaskList(ArrayList<Task> taskList) {
        this.tasks = taskList;
    }


    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Function to add task to taskList
     * @param task task to be added to taskList
     */
    public void addToTaskList(Task task) {
        this.tasks.add(task);
    }

    /**
     * Function to remove task from taskList
     * @param taskIndex index of task to be removed
     */
    public void removeFromTaskList(int taskIndex) {
        this.tasks.remove(taskIndex - 1);
    }

    /**
     * Function to return size of taskList
     * @return size of taskList
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Function to get text in taskList at specified index
     * @param index index of task in taskList
     * @return text in taskList at specified index
     */
    public Task getTask(int index) {
        return this.tasks.get(index - 1);
    }

}
