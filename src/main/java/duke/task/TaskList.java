package duke.task;

import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> tasks = new ArrayList<>();

    public int taskCount() {
        return tasks.size();
    }
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void removeTask(Task t) {
        this.tasks.remove(t);
    }

    public Task getTaskAtIndex(int index) throws IndexOutOfBoundsException {
        return this.tasks.get(index);
    }

    public String listTasks() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i <= taskCount(); i++) {
            stringBuilder.append("  ").append(i).append(". ");
            stringBuilder.append(tasks.get(i - 1));
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

}
