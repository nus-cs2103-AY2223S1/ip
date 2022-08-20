package duke.command;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public ArrayList<Task> getTasks() {
        return this.taskList;
    }

    public void markAsDone(int taskNumber) {
        taskList.get(taskNumber - 1).markAsDone();
    }

    public void markNotDone(int taskNumber) {
        taskList.get(taskNumber - 1).markNotDone();
    }

    public void deleteTask(int taskNumber) {
        taskList.remove(taskNumber - 1);
    }

    public Task retrieveTask(int taskNumber) {
        return this.taskList.get(taskNumber - 1);
    }

    public int getListSize() {
        return taskList.size();
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public ArrayList<Task> searchMatches(String keyword) {
        ArrayList<Task> matchingList = new ArrayList<>();
        for (Task task: taskList) {
            String description = task.getDescription();
            if (description.contains(keyword)) {
                matchingList.add(task);
            }
        }
        return matchingList;
    }
}
