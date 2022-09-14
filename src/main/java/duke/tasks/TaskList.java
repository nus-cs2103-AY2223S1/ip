package duke.tasks;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public void add(Task task) {
        list.add(task);
    }

    public void delete(int index) {
        list.remove(index);
    }

    public Task get(int index) {
        return list.get(index);
    }

    public int size() {
        return list.size();
    }

    public ArrayList<Task> getList() {
        return list;
    }

    public String toString() {
        if (list.size() == 0) {
            return "You have no tasks in your list.";
        }
        String result = "Here are the tasks in your list:\n";
        for (int i = 0; i < list.size(); i++) {
            result += "\t" + (i + 1) + ". " + list.get(i) + "\n";
        }
        return result;
    }
}
