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
    
    public TaskList(ArrayList<Task> data) {
        this.data = data;
    }
    /**
     * Adds the task to the list and to the data file. 
     * 
     * @param task The task to be added.
     * @throws DukeException If data file cannot be accessed.
     */
    public String addTask(Task task) throws DukeException {
        data.add(task);
        storage.appendFile(task, data.size());
        return getAddTaskString(task);
    }

    private String getAddTaskString(Task task) {
        return "Got it. I've added this task:\n" +
                " " + task + "\n" +
                "Now you have " + data.size() + " tasks in the list.\n";
    }

    public String markTask (int pos, boolean isDone) throws DukeException {
        Task task = data.get(pos);
        task.mark(isDone);
        storage.updateFile(this);
        return getMarkTaskString(isDone, task);
    }

    private String getMarkTaskString(boolean isDone, Task task) {
        return (isDone ? "Nice! I've marked this task as done:\n " : "OK, I've marked this task as not done yet:\n ")
                + task + "\n";
    }

    /**
     * Removes the task from the list and from the data file. 
     * 
     * @param pos The position of the task to be removed in the list.
     * @throws DukeException If data file cannot be accessed.
     */
    public String deleteTask(int pos) throws DukeException {
        Task task = data.get(pos);
        data.remove(pos);
        storage.updateFile(this);
        return getDeleteTaskString(task);
    }

    private String getDeleteTaskString(Task task) {
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


    /**
     * Sorts a copy of the list of tasks in chronological order of deadlines.
     * DeadlineTasks are placed above all other Tasks with no deadlines.
     * All other Tasks' relative positions to one another remain.
     * Original list of tasks is not modified, and subsequent commands like delete 
     * or mark have to be done in relation to the original list. 
     *
     * @return String representation of sorted list.
     */
    public String sort() {
        ArrayList<Task> copy = new ArrayList<>(data);
        copy.sort(null);
        TaskList sortedTaskList = new TaskList(copy);
        return "Sorted in chronological order of deadlines\n" + sortedTaskList;
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