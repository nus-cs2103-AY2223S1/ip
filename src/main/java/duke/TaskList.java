package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList list) {
        tasks = list;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public List<String> convertToStringList() {
        return tasks.stream().map(task -> Objects.toString(task)).collect(Collectors.toList());
    }

    public Task remove(int taskNum) {
        return tasks.remove(taskNum);
    }

    public int getSize() {
        return tasks.size();
    }

    public void markDone(int index) {
        tasks.get(index - 1).markAsDone();
    }

    public void unmark(int index) {
        tasks.get(index - 1).unmark();
    }

    public TaskList find(String task) {
        return new TaskList((ArrayList) tasks.stream().filter(s -> s.toString().contains(task)).collect(Collectors.<Task>toList()));
    }

    public void update(int index, Task task) {
        tasks.set(index - 1, task);
    }

    public String getTaskDescription(int index) {
        return tasks.get(index - 1).description;
    }

}
