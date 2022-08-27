package cheese.data;

import java.util.ArrayList;
import cheese.exception.CheeseException;
import cheese.task.Task;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public Task getTask(int taskIndex) throws CheeseException {
        validateTaskIndexInRange(taskIndex);
        return tasks.get(taskIndex);
    }

    public Task delete(int taskIndex) throws CheeseException {
        Task task = getTask(taskIndex);
        tasks.remove(task);
        return task;
    }

    public Task add(Task task) {
        tasks.add(task);
        return task;
    }

    public Task markTaskAsDone(int taskIndex) throws CheeseException {
        Task task = getTask(taskIndex);
        task.setDone();
        return task;
    }

    public Task markTaskAsNotDone(int taskIndex) throws CheeseException {
        Task task = getTask(taskIndex);
        task.setNotDone();
        return task;
    }

    public int getSize() {
        return tasks.size();
    }

    public String toFileString() {
        String fileString = "";
        for (Task task : tasks) {
            fileString += task.toFileString();
            fileString += System.lineSeparator();
        }
        return fileString;
    }

    @Override
    public String toString() {
        String tasksString = "";
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            int displayIndex = i + 1;
            tasksString += displayIndex + ". " + task;
            tasksString += System.lineSeparator();
        }
        return tasksString.trim();
    }

    private void validateTaskIndexInRange(int taskIndex) throws CheeseException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new CheeseException("Item number is not in list range.");
        }
    }
}
