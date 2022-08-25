package duke.main;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTasks(Task task) {
        taskList.add(task);
    }

    public void deleteTasks(int index) {
        taskList.remove(index);
    }

    /**
     * Get a task object at a specified index within duke.main.TaskList
     *
     * @param index
     * @return duke.task.Task
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Get the length of the taskList
     *
     * @return
     */
    public int length() {
        return taskList.size();
    }

}
