package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public String list() {
        StringBuilder listOfTasks = new StringBuilder();
        for (Task task : taskList) {
            listOfTasks.append(task);
            listOfTasks.append("\n");
        }
        int last = listOfTasks.lastIndexOf("\n");
        listOfTasks.deleteCharAt(last);
        return listOfTasks.toString();
    }

    public int size() {
        return taskList.size();
    }

    public Task mark(int taskNum) throws IndexOutOfBoundsException {
        try {
            Task task = taskList.get(taskNum - 1);
            task.markComplete();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("No such task!");
        }
    }

    public Task unmark(int taskNum) throws IndexOutOfBoundsException {
        try {
            Task task = taskList.get(taskNum - 1);
            task.markIncomplete();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("No such task!");
        }
    }

    public Task delete(int taskNum) throws IndexOutOfBoundsException {
        try {
            return taskList.remove(taskNum - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("No such task!");
        }
    }


}










