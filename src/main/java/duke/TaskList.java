package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public int getNumOfTask() {
        return taskList.size();
    }

    public void markTaskN(int n, boolean isDone) throws DukeException {
        try {
            this.taskList.get(n - 1).isDoneSetter(isDone);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The number you entered is not within the valid range.");
        }
    }

    public void deleteTaskN(int n) throws DukeException {
        try {
            this.taskList.remove(n - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The number you entered is not within the valid range.");
        }
    }

    public Task getTaskN(int n) throws DukeException {
        try {
            // start counting from 1
            return this.taskList.get(n - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The number you entered is not within the valid range.");
        }
    }

    public TaskList getTaskListWithKeyword(String keyword) {
       TaskList listWithKeyword = new TaskList();
        for (Task task : this.taskList) {
            if (task.containKeyword(keyword)) {
                listWithKeyword.addTask(task);
            }
        }
        return listWithKeyword;
    }

    @Override
    public String toString() {
        if (taskList.isEmpty()) return "List is empty";
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            result.append(i + 1).append(".").append(taskList.get(i)).append(i == taskList.size() - 1 ? "" : "\n");
        }
        return result.toString();
    }

    public String toStorageString() {
        if (taskList.isEmpty()) return "";
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            result.append(taskList.get(i).toStorageString()).append(i == taskList.size() - 1 ? "" : "\n");
        }
        return result.toString();
    }
}
