package duke.task;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void markDone(int index) {
        tasks.get(index).markDone();
    }

    public void unmarkDone(int index) {
        tasks.get(index).unmarkDone();
    }

    public Task removeTask(int index) {
        return tasks.remove(index);
    }

    public int getNumberOfTasks() {
        return tasks.size();
    }

    public String convertTaskToString(int index) {
        return tasks.get(index).toString();
    }

    public String convertTaskToMemoryString(int index) {
        return tasks.get(index).toMemoryString();
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < getNumberOfTasks(); i++) {
            String count = (i + 1) + ". ";
            if (i == 0) {
                str += count + tasks.get(i).toString();
            } else {
                str += "\n" + count + tasks.get(i).toString();
            }
        }
        return str;
    }
}
