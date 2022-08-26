package piggy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
}
