package duke.task;

import duke.common.DukeException;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int size() {
        return tasks.size();
    }

    public void addItem(Task task) {
        tasks.add(task);
    }

    public Task deleteItem(int index) throws DukeException{
        if (index > tasks.size() || index < 0) {
            throw new DukeException("Please select a valid item");
        }
        Task taskToDelete = tasks.get(index - 1);
        tasks.remove(index - 1);
        return taskToDelete;
    }

    public Task markItem(int index) throws DukeException{
        if (index > tasks.size() || index < 0) {
            throw new DukeException("Please select a valid item");
        }
        Task taskToMark = tasks.get(index - 1);
        taskToMark.isDone = true;
        return taskToMark;
    }

    public Task unmarkItem(int index) throws DukeException {
        if (index > tasks.size() || index < 0) {
            throw new DukeException("Please select a valid item");
        }
        Task taskToUnmark = tasks.get(index - 1);
        taskToUnmark.isDone = false;
        return taskToUnmark;
    }

    public String encode() {
        StringBuilder encodedTaskList = new StringBuilder();
        for (Task task : tasks) {
            encodedTaskList.append(task.encode()).append("\n");
        }
        return encodedTaskList.toString();
    }

    public String toString() {
        StringBuilder taskString = new StringBuilder();
        for (int i = 0; i < tasks.size(); i ++) {
            taskString.append((i + 1) + ". " + tasks.get(i) + "\n");
        }
        return taskString.toString();
    }
}
