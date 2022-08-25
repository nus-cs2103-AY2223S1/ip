package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> storedTasks) {
        this.list = storedTasks;
    }

    public void addTask(Task task) {
        this.list.add(task);
    }

    public int size() {
        return this.list.size();
    }

    public Task get(int i) {
        return this.list.get(i);
    }

    public Task delete(int i) throws DukeException {
        int size = this.size();
        if (size == 0) {
            throw new DukeException("☹ OOPS!!! You do not have any task right now.");
        }
        if (i < 0 || i >= size) {
            throw new DukeException("☹ OOPS!!! Please enter a valid task number.");
        } else {
            return list.remove(i);
        }
    }

    public void mark(int i) throws DukeException {
        int size = this.size();
        if (size == 0) {
            throw new DukeException("☹ OOPS!!! You do not have any task right now.");
        }
        if (i < 0 || i >= size) {
            throw new DukeException("☹ OOPS!!! Please enter a valid task number.");
        } else {
            Task curTask = this.get(i);
            curTask.markAsDone();
        }
    }

    public void unmark(int i) throws DukeException {
        int size = this.size();
        if (size == 0) {
            throw new DukeException("☹ OOPS!!! You do not have any task right now.");
        }
        if (i < 0 || i >= size) {
            throw new DukeException("☹ OOPS!!! Please enter a valid task number.");
        } else {
            Task curTask = this.get(i);
            curTask.markAsUndone();
        }
    }

}
