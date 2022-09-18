package Duke;

import java.util.ArrayList;

public class TaskList {

    private  ArrayList<Task> taskList;

    public TaskList() {

        taskList = new ArrayList<Task>();
    }

    public void add(Task task) {

        taskList.add(task);
    }

    public int size() {

        return taskList.size();
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    public void remove(int index) {

        taskList.remove(index);
    }

    public ArrayList<Task> getList() {

        return this.taskList;
    }

    public TaskList findTasks(String keyword) {
        TaskList result = new TaskList();
        for (Task task : taskList) {
            if (task.getDescription().contains(keyword)) {
                result.add(task);
            }
        }
        return result;
    }
}
