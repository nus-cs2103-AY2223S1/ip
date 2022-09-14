package Duke.TaskList;

import Duke.Exception.DukeException;
import Duke.Task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> taskList;

    public TaskList() {
        this(new ArrayList<>());
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public List<Task> getTaskList() {
        return this.taskList;
    }

    public void addTask(Task newTask) {
        taskList.add(newTask);
    }

    public Task markTask(int index) throws DukeException {
        if (index < 0 || index >= taskList.size()) {
            throw DukeException.IndexOutOfBoundsException(index);
        }
        Task task = taskList.get(index);
        task.markAsDone();
        return task;
    }

    public Task unmarkTask(int index) throws DukeException {
        if (index < 0 || index >= taskList.size()) {
            throw DukeException.IndexOutOfBoundsException(index);
        }
        Task task = taskList.get(index);
        task.markAsUndone();
        return task;
    }

    public Task deleteTask(int index) throws DukeException{
        if (index < 0 || index >= taskList.size()) {
            throw DukeException.IndexOutOfBoundsException(index);
        }
        return taskList.remove(index);
    }

    public String getStorageString() {
        StringBuilder out = new StringBuilder();
        for (Task task : taskList) {
            out.append(task.getStorageString());
            out.append("\n");
        }
        return out.toString();
    }

    public Task[] findTasks(String keyword) {
        List<Task> foundTasks = new ArrayList<>();
        for (Task task : taskList) {
            boolean hasKeyword = task.hasKeyword(keyword);
            if (hasKeyword) {
                foundTasks.add(task);
            }
        }
        return foundTasks.toArray(new Task[0]);
    }
}
