package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructor for duke.TaskList, which stores task entered by user in duke.Duke
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }


    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }


    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Function to add task to taskList
     * @param task task to be added to taskList
     */
    public void addToTaskList(Task task) {
        this.taskList.add(task);
    }

    /**
     * Function to remove task from taskList
     * @param taskIndex index of task to be removed
     */
    public void removeFromTaskList(int taskIndex) {
        this.taskList.remove(taskIndex - 1);
    }

    /**
     * Function to return size of taskList
     * @return size of taskList
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Function to get text in taskList at specified index
     * @param index index of task in taskList
     * @return text in taskList at specified index
     */
    public Task getTask(int index) {
        return this.taskList.get(index - 1);
    }

    /**
     * Returns tasks that contain search keyword.
     * @param searchKeyword search keyword for task.
     * @return TaskList with tasks that contain search keyword.
     */
    public TaskList findTasks(String searchKeyword) {
        TaskList foundTasks = new TaskList(new ArrayList<Task>());
        for (Task task : taskList) {
            if (task.toString().contains(searchKeyword)) {
                foundTasks.addToTaskList(task);
            }
        }
        return foundTasks;
    }

}
