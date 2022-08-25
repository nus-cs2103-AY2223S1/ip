package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    protected TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void add(Task t) {
        taskList.add(t);
    }

    public void remove(int index) {
        if (index > taskList.size()) {
            throw new IndexOutOfBoundsException();
        }
        taskList.remove(index);
    }

    public int getSize() {
        return taskList.size();
    }

    public Task get(int index) {
        if (index > taskList.size()) {
            throw new IndexOutOfBoundsException();
        }
        return taskList.get(index);
    }

    public void mark(int index) {
        if (index > taskList.size()) {
            throw new IndexOutOfBoundsException();
        }
        taskList.get(index).markDone();
    }

    public void unmark(int index) {
        if (index > taskList.size()) {
            throw new IndexOutOfBoundsException();
        }
        taskList.get(index).markUndone();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            sb.append(i + 1);
            sb.append(".");
            sb.append(taskList.get(i).toString());
        }
        return sb.toString();
    }
}
