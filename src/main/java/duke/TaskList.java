package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks = null;
    public TaskList(ArrayList<Task> tasks) throws DukeException {
        try {
            this.tasks = tasks;
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }

    public TaskList() {
        if (this.tasks == null) {
            ArrayList<Task> taskList = new ArrayList<>();
            this.tasks = taskList;
        }
    }

    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    public void delete(int number) {
        tasks.remove(number - 1);
    }

    public int totalSize() {
        return tasks.size();
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

}
