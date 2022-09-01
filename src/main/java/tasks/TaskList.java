package tasks;

import java.util.List;

public class TaskList {

    private List<Task> taskList;

    public TaskList(List<Task> initList) {
        taskList = initList;
    }

    public List<Task> getList() {
        return taskList;
    }

    public int getSize() {
        return taskList.size();
    }

    public void addTask(Task newTask) {
        taskList.add(newTask);
    }

    public Task deleteTask(int index) {
        if (index < 0 || index >= taskList.size()) {
            return null;
        }
        return taskList.remove(index);
    }

    public Task markTask(int index) {
        if (index < 0 || index >= taskList.size()) {
            return null;
        }
        Task taskToMark = taskList.get(index);
        taskToMark.mark();
        return taskToMark;
    }

    public Task unmarkTask(int index) {
        if (index < 0 || index >= taskList.size()) {
            return null;
        }
        Task taskToUnmark = taskList.get(index);
        taskToUnmark.unmark();
        return taskToUnmark;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Task t : taskList) {
            sb.append(i);
            sb.append(". ");
            sb.append(t);
            sb.append("\n");
            ++i;
        }
        return sb.toString();
    }

}
