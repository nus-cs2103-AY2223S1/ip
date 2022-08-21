package duke;

import java.util.ArrayList;

import duke.task.Task;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> taskList) {
        this.tasks = taskList;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public int size() {
        return tasks.size();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void remove(int taskId) {
        tasks.remove(taskId);
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void printList() {
        if (tasks == null || tasks.size() <= 0) {
            System.out.println("No tasks assigned yet.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + getTask(i));
            }
        }
    }
}
