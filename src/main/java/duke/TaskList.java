package duke;

import duke.task.Task;

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
    /**
     * Adds the task to the list and to the data file. 
     * 
     * @param task The task to be added.
     * @throws DukeException If data file cannot be accessed.
     */
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
    
    /**
     * Removes the task from the list and from the data file. 
     * 
     * @param task The position of the task to be removed in the list.
     * @throws DukeException If data file cannot be accessed.
     */
    public void deleteTask(int pos) throws DukeException {
        Task task = data.get(pos);
        data.remove(pos);
        storage.writeFile(pos, null);
        ui.deleteTask(task, data.size());
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