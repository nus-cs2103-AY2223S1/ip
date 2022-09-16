package duke;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private ArrayList<Task> tasks;

    private final String HORIZONTAL_LINE_BREAK = "-------------------------";

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> list) {
        this.tasks = list;
    }

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int getSize() {
        return tasks.size();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void markTask(int index) throws DukeException {
        int size = tasks.size();
        if (index >= size || index < 0) {
            throw new DukeException("The index of the task does not exists.");
        } else {
            Task completedTask = tasks.get(index);
            completedTask.markAsDone();
        }
    }

    public void unMarkTask(int index) throws DukeException{
        int size = tasks.size();
        if (index >= size || index < 0) {
            throw new DukeException("The index of the task does not exists.");
        } else {
            Task unfinishedTask = tasks.get(index);
            unfinishedTask.markAsNotDone();
        }
    }

    public Task deleteTask(int index) throws DukeException {
        int size = tasks.size();
        if (index >= size || index < 0) {
            throw new DukeException("The index of the task does not exists.");
        } else {
            Task toBeDeleted = tasks.get(index);
            tasks.remove(index);
            return toBeDeleted;
        }
    }
}
