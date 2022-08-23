package duke.task;

import duke.Task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> list) {
        this.taskList = list;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public int numberOfTasks() {
        return taskList.size();
    }

    public String tasksLeft() {
        return "\nNow you have " + taskList.size() + " tasks in the list.";
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void deleteTask(int index) {
        taskList.remove(index);
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

}
