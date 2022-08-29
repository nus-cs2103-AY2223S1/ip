package duke.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public Task getTask(int index) {
        return this.list.get(index);
    }

    public void add(Task task) {
        this.list.add(task);
    }

    public void remove(int index) {
        this.list.remove(index);
    }

    public void mark(int index) {
        this.list.get(index).mark();
    }

    public void unmark(int index) {
        this.list.get(index).unmark();
    }

    public void printList() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ". " + list.get(i));
        }
    }
}
