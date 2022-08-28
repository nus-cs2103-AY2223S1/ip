package duke;

import java.time.LocalDate;
import java.util.ArrayList;

import duke.task.Task;



public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> fromStorage) {
        this.taskList = fromStorage;
    }

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public int getTasksNumber() {
        return taskList.size();
    }

    /**
     * Adds the duke.task.Task into the storage.
     * @param t Specified duke.task.Task.
     */
    public void addTask(Task t) {
        this.taskList.add(t);
    }

    /**
     * Deletes the specified task in the storage.
     * @param i Specified task.
     */
    public Task deleteTask(int i) throws DukeException {
        try {
            Task target = taskList.get(i - 1);
            taskList.remove(target);
            return target;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task not found.");
        }
    }

    public ArrayList<Task> getTasksOnDate(LocalDate date) {
        ArrayList<Task> temp = new ArrayList<>();
        for (Task t : taskList) {
            if (t.compareDate(date)) {
                temp.add(t);
            }
        }
        return temp;
    }

    /**
     * Mark the specified task in the storage as done.
     * @param i Specified task.
     */
    public Task markDone(int i) throws DukeException {
        try {
            Task target = taskList.get(i - 1);
            target.markDone();
            return target;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task not found.");
        }
    }

    /**
     * Mark the specified task in the storage as not done.
     * @param i Specified task
     */
    public Task unmarkDone(int i) throws DukeException {
        try {
            Task target = taskList.get(i - 1);
            target.unmarkDone();
            return target;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task not found");
        }
    }
}
