package duke.task;

import java.util.ArrayList;

import duke.errors.DukeException;

public class TaskList {

    private ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        list = tasks;
    }

    public int size() {
        return list.size();
    }

    public void add(Task task) {
        list.add(task);
    }

    public Task deleteTask(int index) throws DukeException {
        try {
            return list.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Delete task list failed, check index boundary");
        }
    }

    public Task get(int index) throws DukeException {
        try {
            return list.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Get task list failed, check index boundary");
        }

    }

    public void mark(int index) throws DukeException {
        try {
            list.get(index - 1).finished();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("mark failed");
        }
    }

    public void unmark(int index) throws DukeException {
        try {
            list.get(index - 1).notFinished();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("unmark failed");
        }
    }

    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getDescription().contains(keyword)) {
                matchingTasks.add(list.get(i));
            }
        }
        return matchingTasks;
    }
}
