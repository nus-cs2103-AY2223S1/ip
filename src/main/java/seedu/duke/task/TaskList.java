package seedu.duke.task;

import seedu.duke.ui.Style;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Task getTask(int index) {
        return this.tasks.get(index - 1);
    }

    public Task addTask(Task task) {
        tasks.add(task);
        return task;
    }

    public Task deleteTask(int index) {
        Task task = getTask(index);
        tasks.remove(index - 1);
        return task;
    }

    public Task markTask(int index) {
        Task task = getTask(index);
        task.markAsDone();
        return task;
    }

    public Task unmarkTask(int index) {
        Task task = getTask(index);
        task.unmark();
        return task;
    }

    public void listTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(Style.INDENTATION + (i + 1)  + "." + getTask(i + 1));
        }
    }

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }


    public int getNumberOfTasks() {
        return tasks.size();
    }

}
