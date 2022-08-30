package iana.tasks;

import java.util.ArrayList;
import iana.exception.IanaException;
import iana.tasks.TaskList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public int size() {
        return this.taskList.size();
    }

    public void add(Task newTask) throws IanaException {
        this.taskList.add(newTask);
    }

    public Task delete(int taskNumber) throws IanaException {
        if (taskNumber > this.taskList.size()) {
            throw new IanaException("Oops!! This task number is invalid. Try to delete another task! xx");
        }
        Task deleted = this.taskList.remove(taskNumber);
        return deleted;
    }

    public void mark(int taskNumber) {
        this.taskList.get(taskNumber).toggleComplete(true);
    }

    public void unmark(int taskNumber) {
        this.taskList.get(taskNumber).toggleComplete(false);
    }

    public String printTaskString(int taskNumber) {
        return this.taskList.get(taskNumber).toString();
    }

    public String toFileData() {
        String fileString = "";
        for (Task task : this.taskList) {
            fileString = fileString + task.toFileData() + "\n";
        }
        return fileString;
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < this.taskList.size(); i++) {
            Task task = this.taskList.get(i);
            str = str + String.format("\t   %d. %s", i + 1, task.toString()) + "\n";
        }
        return str;
    }
}
