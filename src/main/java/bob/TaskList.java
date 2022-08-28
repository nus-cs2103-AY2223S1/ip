package bob;

import java.util.ArrayList;
import java.time.LocalDate;

public class TaskList {

    private ArrayList<Task> tasks;
    private int length;

    public TaskList() {
        this.tasks = new ArrayList<>(100);
        this.length = 0;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
        this.length = this.length + 1;
    }

    public void removeTask(int index) {
        ArrayList<Task> temp = new ArrayList<>(100);
        Task removedTask = tasks.get(index - 1);
        for (int i = 0; i < this.length; i++) {
            if (i == (index - 1)) {
                continue;
            } else {
                temp.add(tasks.get(i));
            }
        }
        this.tasks = temp;
        this.length = this.length - 1;
    }

    public void markTask(int index, boolean marker) {
        this.tasks.get(index - 1).isDone = marker;
    }

    public TaskList filterTask(String dateString) {
        LocalDate date = LocalDate.parse(dateString);
        TaskList filteredTasks = new TaskList();
        for (Task task: tasks) {
            if (task instanceof Event && ((Event) task).at.equals(date)) {
                filteredTasks.addTask(task);
            } else if (task instanceof Deadline && ((Deadline) task).by.equals(date)) {
                filteredTasks.addTask(task);
            }
        }
        return filteredTasks;
    }

    public TaskList findTask(String searchWord) {
        TaskList foundTasks = new TaskList();
        for (Task task: tasks) {
            if (task.description.contains(searchWord)) {
                foundTasks.addTask(task);
            }
        }
        return foundTasks;
    }

    public Task getTask(int index) {
        return this.tasks.get(index - 1);
    }

    public int getLength() {
        return this.length;
    }
}
