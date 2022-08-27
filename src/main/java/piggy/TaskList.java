package piggy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import piggy.task.Task;

class TaskList implements Iterable<Task> {
    private List<Task> tasks;

    TaskList(List<Task> tasks) {
        if (tasks != null) {
            this.tasks = tasks;
        } else {
            this.tasks = new ArrayList<>();
        }
    }

    void add(Task task) {
        tasks.add(task);
    }

    Task remove(int index) {
        return tasks.remove(index);
    }

    Task get(int index) {
        return tasks.get(index);
    }

    int size() {
        return tasks.size();
    }

    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }

    /**
     * Returns a List of the Tasks.
     *
     * @return A list of tasks.
     */
    public List<Task> toList() {
        return new ArrayList<>(tasks);
    }

    /**
     * Returns a list of tasks whose description contains the keyword.
     *
     * @param keyword The keyword to find.
     * @return The list of tasks that match.
     */
    public List<Task> find(String keyword) {
        return tasks.stream().filter(task -> task.getDescription().contains(keyword)).collect(Collectors.toList());
    }
}
