package duke;

import java.util.List;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.stream.Collectors;

public class TaskList {
    private final List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        tasks = new ArrayList<>();
    }

    protected Task markTask(int index) {
        Task performedTask = tasks.get(index).performTask();
        tasks.set(index, performedTask);
        return performedTask;
    }

    protected Task unmarkTask(int index) {
        Task undidTask = tasks.get(index).undoTask();
        tasks.set(index, undidTask);
        return undidTask;
    }

    protected void add(Task newTask) {
        tasks.add(newTask);
    }

    protected Task delete(int index) {
        return tasks.remove(index);
    }

    protected int getSize() {
        return tasks.size();
    }

    protected String enumerateList() {
        ListIterator<Task> it = tasks.listIterator();
        StringBuilder output = new StringBuilder();
        while (it.hasNext()) {
            output.append(it.nextIndex() + 1).append(" ").append(it.next()).append("\n");
        }
        return output.toString();
    }

    protected String find(String term) {
        List<Task> filteredTaskList = tasks.stream()
                .filter(task -> task.getDesc().contains(term))
                .collect(Collectors.toList());
        return new TaskList(filteredTaskList).enumerateList();
    }
}
