package duke.task;

import java.util.ArrayList;

import duke.exception.DukeException;

/**
 * Represents a list used to store tasks.
 */
public class List {

    private ArrayList<Task> taskList = new ArrayList<>();

    public List() {
    }

    public void addTask(Task command) {
        taskList.add(command);
    }

    public Task getTask(int taskNumber) throws DukeException {
        if (taskNumber < 0 || taskNumber > this.taskList.size() - 1) {
            throw new DukeException("You do not have that task. Try seeing your task list instead!\n");
        }
        return this.taskList.get(taskNumber);
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void deleteTask(int taskNumber) throws DukeException {
        if (taskNumber < 0 || taskNumber > this.taskList.size() - 1) {
            throw new DukeException("You cannot delete what you don't have from the beginning, auch!\n");
        }
        this.taskList.remove(taskNumber);
    }

    public int numberOfTasks() {
        return taskList.size();
    }

}
