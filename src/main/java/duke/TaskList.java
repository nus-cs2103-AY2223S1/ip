package duke;

import duke.task.Task;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> data;
    private Storage storage;
    private UI ui;

    public TaskList(Storage storage, UI ui) throws DukeException {
        this.data = storage.load();
        this.storage = storage;
        this.ui = ui;
    }

    public void addTask(Task task) throws DukeException {
        data.add(task);
        storage.appendFile(task);
        ui.addTask(task, data.size());
    }

    public void markTask (int pos, boolean isDone) throws DukeException {
        Task task = data.get(pos);
        task.mark(isDone);
        storage.writeFile(pos, task);
        ui.markTask(task, isDone);
    }
    
    public void deleteTask(int pos) throws DukeException {
        Task task = data.get(pos);
        data.remove(pos);
        storage.writeFile(pos, null);
        ui.deleteTask(task, data.size());
    }
    
    public String findTasks(String keyword) {
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
        int counter = 0;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).containsKeyword(keyword)) {
                sb.append(counter + 1).append(".").append(data.get(i).toString()).append("\n");
                counter++;
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < data.size(); i++) {
            result.append(i + 1).append(".").append(data.get(i)).append("\n");
        }
        return result.toString();
    }
}
// must wait for data to be saved before entering another command