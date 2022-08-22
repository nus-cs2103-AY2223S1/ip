package henry;

import java.util.List;

public class TaskList {

    private List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public String deleteTask(int index) throws IndexOutOfBoundsException {
        return tasks.remove(index).toString();
    }

    public String markTask(String command) throws IndexOutOfBoundsException, NumberFormatException {
        int index = Integer.parseInt(command.split(" ")[1]);
        tasks.get(index).setComplete(true);
        return tasks.get(index).toString();
    }

    public String unmarkTask(String command) throws IndexOutOfBoundsException, NumberFormatException {
        int index = Integer.parseInt(command.split(" ")[1]);
        tasks.get(index).setComplete(false);
        return tasks.get(index).toString();
    }

    public String addTask(Task task) {
        tasks.add(task);
        return task.toString();
    }

    public int size() {
        return tasks.size();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        for (int i = 1; i <= tasks.size(); i++) {
            sb.append(" ").append(i).append(". ").append(tasks.get(i - 1)).append("\n");
        }
        return sb.toString();
    }

    public String toSimpleString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= tasks.size(); i++) {
            sb.append(tasks.get(i - 1).toSimpleString()).append("\n");
        }
        return sb.toString().trim();
    }
}
