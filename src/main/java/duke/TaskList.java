package duke;

import java.util.ArrayList;

import duke.task.Task;

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
    public String addTask(Task task) throws DukeException {
        data.add(task);
        storage.appendFile(task);
        return "Got it. I've added this task:\n" + 
                " " + task + "\n" +
                "Now you have " + data.size() + " tasks in the list.\n";
    }

    public String markTask (int pos, boolean isDone) throws DukeException {
        Task task = data.get(pos);
        task.mark(isDone);
        storage.writeFile(pos, task);
        return (isDone ? "Nice! I've marked this task as done:\n " : "OK, I've marked this task as not done yet:\n ")
                + task + "\n";
    }
    
    /**
     * Removes the task from the list and from the data file. 
     * 
     * @param task The position of the task to be removed in the list.
     * @throws DukeException If data file cannot be accessed.
     */
    public String deleteTask(int pos) throws DukeException {
        Task task = data.get(pos);
        data.remove(pos);
        storage.writeFile(pos, null);
        return "Noted. I've removed this task:\n " 
                + task + "\n" +
                "Now you have " + data.size() + " tasks in the list.\n";
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