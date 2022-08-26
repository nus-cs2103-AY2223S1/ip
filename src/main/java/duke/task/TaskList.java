package duke.task;

import java.util.List;

import duke.exceptions.NoSuchTaskException;

public class TaskList {
    private List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getAll() {
        return this.tasks;
    }

    public int getNumTasks() {
        return this.tasks.size();
    }

    private String getNumTasksAsString() {
        return String.format("Now you have %d tasks in the list.", this.getNumTasks());
    }

    public void store(Task task) {
        this.tasks.add(task);
    }

    public Task get(int index) throws NoSuchTaskException {
        try {
            return this.tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchTaskException(this.getNumTasks(), index + 1);
        }
    }

    public Task delete(int index) throws NoSuchTaskException {
        Task task = this.get(index);
        this.tasks.remove(index);
        return task;
    }
}
