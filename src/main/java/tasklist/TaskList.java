package tasklist;

import exception.DorisException;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        TaskList.tasks = tasks;
    }

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public static int size() {
        return tasks.size();
    }

    public static void add(Task task) {
        tasks.add(task);
    }

    public static Task get(int index) {
        return tasks.get(index);
    }

    public Task delete(int index) {
        Task removed = tasks.get(index);
        tasks.remove(index);
        return removed;
    }

    public Task mark(int index) {
        tasks.get(index).mark();
        return tasks.get(index);
    }

    public Task unmark(int index) {
        tasks.get(index).unmark();
        return tasks.get(index);
    }

    @Override
    public String toString() {
        String taskList = "";
        for (int i = 0; i <= tasks.size(); i++) {
            if (i == tasks.size() - 1) {
                taskList += i + 1 + ". " + tasks.get(i);
            } else {
                taskList += i + 1 + ". " + tasks.get(i) + "\n";
            }
        }
        return taskList;
    }

    public void list() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + ". " + tasks.get(i));
        }
    }
}
