package duke.task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> listOfTasks;

    public TaskList() {
        this.listOfTasks = new ArrayList<>();
    }

    public Task getTask(int taskNum) {
        return listOfTasks.get(taskNum);
    }

    public ArrayList<Task> getListOfTasks() {
        return this.listOfTasks;
    }

    public void addTask(Task task) {
        listOfTasks.add(task);
    }

    public int getTaskSize() {
        return this.listOfTasks.size();
    }

    public void markTask(int TaskNum) throws IndexOutOfBoundsException {
        Task currTask = listOfTasks.get(TaskNum);
        currTask.markAsDone();
    }

    public void unmarkTask(int TaskNum) {
        Task currTask = listOfTasks.get(TaskNum);
        currTask.markAsNotDone();
    }

    public void deleteTask(int TaskNum) {
        listOfTasks.remove(TaskNum);
    }
}
