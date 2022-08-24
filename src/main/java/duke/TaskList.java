package duke;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;
    private Storage storage;

    TaskList(Storage storage, Ui ui) {
        this.storage = storage;
        try {
            tasks = storage.loadTasks();
        } catch (Exception e) {
            ui.showError(e.getMessage());
            tasks = new ArrayList<>();
        }
    }

    public void mark(int index) throws DukeException {
        Task task = tasks.get(index);
        task.mark();
        storage.saveTasks(tasks);
    }

    public void unmark(int index) throws DukeException {
        Task task = tasks.get(index);
        task.unmark();
        storage.saveTasks(tasks);
    }

    public void add(Task task) throws DukeException {
        tasks.add(task);
        storage.saveTasks(tasks);
    }

    public void remove(int index) throws DukeException {
        tasks.remove(index);
        storage.saveTasks(tasks);
    }

    public int getSize() {
        return tasks.size();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    @Override
    public String toString() {
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            temp.append(i + 1);
            temp.append(".");
            temp.append(tasks.get(i));
            temp.append("\n");
        }
        return temp.toString();
    }
}
