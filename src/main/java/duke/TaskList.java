package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(String data) throws DukeException {
        this.taskList = new ArrayList<>();
        String[] taskFromDataStr = data.split("\n");
        for (String taskFromDatum : taskFromDataStr) {
            if (!taskFromDatum.equals("")) this.addTaskFromDataStr(taskFromDatum);
        }
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    private void addTaskFromDataStr(String task) throws DukeException {
        Task newTask = null;
        String[] taskSplit = task.split("\\|", 3);
        String taskType = taskSplit[0];
        boolean isDone = taskSplit[1].equals("1");
        String taskDesc = taskSplit[2];
        switch (taskType) {
            case "T":
                newTask = ToDo.ToDoFromData(taskDesc, isDone);
                break;
            case "D":
                newTask = Deadline.DeadlineFromFile(taskDesc, isDone);
                break;
            case "E":
                newTask = Event.EventFromFile(taskDesc, isDone);
                break;
        }
        taskList.add(newTask);
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

    @Override
    public String toString() {
        if (taskList.isEmpty()) return "List is empty";
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            result.append(i + 1).append(".").append(taskList.get(i)).append(i == taskList.size() - 1 ? "" : "\n");
        }
        return result.toString();
    }

    public String toFile() {
        if (taskList.isEmpty()) return "";
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            result.append(taskList.get(i).toFile()).append(i == taskList.size() - 1 ? "" : "\n");
        }
        return result.toString();
    }
}
