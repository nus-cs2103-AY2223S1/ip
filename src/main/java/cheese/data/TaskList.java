package cheese.data;

import java.util.ArrayList;

import cheese.exception.CheeseException;
import cheese.task.Task;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public Task getTask(int taskIndex) throws CheeseException {
        validateTaskIndexInRange(taskIndex);
        return taskList.get(taskIndex);
    }

    public Task delete(int taskIndex) throws CheeseException {
        Task task = getTask(taskIndex);
        taskList.remove(task);
        return task;
    }

    public Task add(Task task) {
        taskList.add(task);
        return task;
    }

    public Task markTaskAsDone(int taskIndex) throws CheeseException {
        Task task = getTask(taskIndex);
        task.markAsDone();
        return task;
    }

    public Task markTaskAsNotDone(int taskIndex) throws CheeseException {
        Task task = getTask(taskIndex);
        task.markAsNotDone();
        return task;
    }

    public int getSize() {
        return taskList.size();
    }

    public String toFileString() {
        String fileString = "";
        for (Task task : taskList) {
            fileString += task.toFileString();
            fileString += System.lineSeparator();
        }
        return fileString;
    }

    @Override
    public String toString() {
        String taskListString = "";
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            int displayIndex = i + 1;
            taskListString += displayIndex + ". " + task;
            taskListString += System.lineSeparator();
        }
        return taskListString.trim();
    }

    private void validateTaskIndexInRange(int taskIndex) throws CheeseException {
        if (taskIndex < 0 || taskIndex >= taskList.size()) {
            throw new CheeseException("Item number is not in list range.");
        }
    }
}
