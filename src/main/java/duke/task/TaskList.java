package duke.task;

import duke.utilities.DukeException;

import java.util.ArrayList;

public class TaskList {
    /** Use an ArrayList Collection for handling the tasks. */
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public int getNumberOfTasks() {
        return this.tasks.size();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int index) throws DukeException {
        if (index == -1) {
            throw new DukeException("You must specify which task to delete!");
        }
        Task task = tasks.get(index - 1);
        tasks.remove(index - 1);
        return task;
    }

    public Task changeTaskStatus(int taskId, boolean isDone) throws DukeException {
        if (taskId == -1) {
            throw new DukeException("You must specify which task to mark or unmark!");
        }
        Task task = tasks.get(taskId - 1);
        task.setDoneStatus(isDone);
        return task;
    }

    /**
     * Finds the tasks that have descriptions that contain the target.
     *
     * @param target The target word that we want from the tasks.
     * @return Returns a new ArrayList of tasks containing the matching tasks.
     */
    public ArrayList<Task> findMatchingTasks(String target) {
        ArrayList<Task> filtered = new ArrayList<>();
        if (target.equals("")) {
            return this.tasks;
        }

        for (Task task : this.tasks) {
            if (task.getDescription().contains(target)) {
                filtered.add(task);
            }
        }

        return filtered;
    }
}
