package duke.tasks;

import duke.exceptions.TaskNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

public class TaskList {

    private List<Task> list;

    public TaskList(List<Task> initList) {
        list = initList;
    }

    public List<Task> getList() {
        return list;
    }

    public int getSize() {
        return list.size();
    }

    public void addTask(Task newTask) {
        list.add(newTask);
    }

    public Task deleteTask(int index) throws TaskNotFoundException {
        if (index < 0 || index >= list.size()) {
            throw new TaskNotFoundException();
        }
        return list.remove(index);
    }

    public Task markTask(int index) throws TaskNotFoundException {
        if (index < 0 || index >= list.size()) {
            throw new TaskNotFoundException();
        }
        Task taskToMark = list.get(index);
        assert taskToMark != null;
        taskToMark.mark();
        return taskToMark;
    }

    public List<Task> find(String s) {
        return list.stream()
                .filter(task -> task.getName().contains(s))
                .collect(Collectors.toList());
    }

    public Task unmarkTask(int index) throws TaskNotFoundException {
        if (index < 0 || index >= list.size()) {
            throw new TaskNotFoundException();
        }
        Task taskToUnmark = list.get(index);
        assert taskToUnmark != null;
        taskToUnmark.unmark();
        return taskToUnmark;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Task t : list) {
            sb.append(i);
            sb.append(". ");
            sb.append(t);
            sb.append("\n");
            ++i;
        }
        return sb.toString();
    }

}
