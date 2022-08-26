package duke.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this(new ArrayList<>());
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    public void remove(int index) {
        taskList.remove(index);
    }

    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    public int size() {
        return taskList.size();
    }

    public TaskList findTask(String description) {
        ArrayList<Task> list = new ArrayList<>();

        for (Task task : taskList) {
            if (task.getDescription().contains(description)) {
                list.add(task);
            }
        }

        return new TaskList(list);
    }
}
