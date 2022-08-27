package tasklist;

import exception.FredException;

import task.Task;

import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public int size() {
        return taskList.size();
    }

    public Task getTask(int index) throws FredException {
        if (index > taskList.size()) {
            throw new FredException("Your list has only " + taskList.size() + " items!");
        } else if (index <= 0) {
            throw new FredException("Input an integer that is greater than 0!");
        }

        return taskList.get(index - 1);
    }

    public String list() throws FredException {
        if (taskList.size() == 0) {
            throw new FredException("There are no items in your list!");
        }

        String list = "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.size(); i++) {
            list += (i + 1) + "." + taskList.get(i) + "\n";
        }
        return list;
    }

    public void mark(int index) throws FredException {
        if (index > taskList.size()) {
            throw new FredException("Your list has only " + taskList.size() + " items!");
        } else if (index <= 0) {
            throw new FredException("Input an integer that is greater than 0!");
        }

        taskList.get(index - 1).setStatus(true);
    }

    public void unmark(int index) throws FredException {
        if (index > taskList.size()) {
            throw new FredException("Your list has only " + taskList.size() + " items!");
        } else if (index <= 0) {
            throw new FredException("Input an integer that is greater than 0!");
        }

        taskList.get(index - 1).setStatus(false);
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public void delete(int index) throws FredException {
        if (index > taskList.size()) {
            throw new FredException("Your list has only " + taskList.size() + " items!");
        } else if (index <= 0) {
            throw new FredException("Input an integer that is greater than 0!");
        }

        taskList.remove(index - 1);
    }

}
