package duke.model;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Task getTask(int taskNumber) {
        return tasks.get(taskNumber - 1);
    }

    public void add(Task task) {
        tasks.add(task);
        Task.incrementNumOfTasks();
    }

    public Task delete(int taskNumber) {
        Task task = tasks.remove(taskNumber - 1);
        Task.decrementNumOfTasks();
        return task;
    }

    public void mark(int taskNumber) {
        tasks.get(taskNumber - 1).mark();
    }

    public void unmark(int taskNumber) {
        tasks.get(taskNumber - 1).unmark();
    }

    public String toStorage() {
        String res = "";
        for (int i = 0; i < tasks.size(); i++) {
            res += tasks.get(i).toStorage();
        }
        return res;
    }

    @Override
    public String toString() {
        String str = "";
        if (tasks.size() == 0) {
            str = "\tYou do not have any tasks!";
        } else {
            str = "\tHere are the tasks in your list:";
            for (int i = 0; i < tasks.size(); i++) {
                str += "\n\t" + (i + 1) + ". " + tasks.get(i);
            }
        }
        return str;
    }
}
