import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }
    public int size() {
        return taskList.size();
    }
    public Task getTask(int index) {
        return taskList.get(index);
    }
    public void addTask(Task task) {
        taskList.add(task);
    }
    public Task deleteTask(int num) throws DukeException {
        if (num > taskList.size() || num <= 0) {
            throw new DukeException("");
        }
        return taskList.remove(num - 1);
    }

    public void mark(int number) throws DukeException {
        if (number > taskList.size() || number <= 0) {
            throw new DukeException("");
        }
        taskList.get(number - 1).setDone();
    }

    public void unMark(int number) throws DukeException {
        if (number > taskList.size() || number <= 0) {
            throw new DukeException("");
        }
        taskList.get(number - 1).setUnDone();
    }
    public String taskListToText() {
        StringBuilder lines = new StringBuilder();
        for (Task task : taskList) {
            lines.append(task.toLine()).append("\n");
        }

        return lines.toString();
    }
}
