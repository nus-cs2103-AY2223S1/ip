package iana.tasks;

import java.util.ArrayList;
import iana.exception.IanaException;
import iana.main.Ui;
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

    /**
     * Find tasks with specified keyword.
     * @param keyword keyword of task.
     * @return a list of all tasks with the keyword.
     */
    public TaskList findKeyword(String keyword) {
        TaskList list = new TaskList();
        for (Task task : this.taskList) {
            if (task.containsKeyword(keyword)) {
                try {
                    list.add(task);
                } catch (IanaException e) {
                    break;
                }
            }
        }
        return list;
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
