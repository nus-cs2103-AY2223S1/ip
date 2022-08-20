package duke.task;

import duke.common.DukeException;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void addItem(Task item) {
        tasks.add(item);
    }

    public Task markItem(int index) throws DukeException {
        try {
            tasks.get(index).setDone(true);
            return tasks.get(index);
        } catch (IndexOutOfBoundsException exception) {
            throw new DukeException("☹ OOPS!!! No such task exists :(");
        }
    }

    public Task unMarkItem(int index) throws DukeException {
        try {
            tasks.get(index).setDone(false);
            return tasks.get(index);
        } catch (IndexOutOfBoundsException exception) {
            throw new DukeException("☹ OOPS!!! No such task exists :(");
        }
    }

    public Task deleteItem(int index) throws DukeException {
        try {
            Task task = tasks.get(index);
            tasks.remove(index);
            return task;
        } catch (IndexOutOfBoundsException exception) {
            throw new DukeException("☹ OOPS!!! No such task exists :(");
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tasks.size(); i += 1) {
            stringBuilder.append((i + 1) + ". " + tasks.get(i) + "\n");
        }
        return stringBuilder.toString();
    }
}
