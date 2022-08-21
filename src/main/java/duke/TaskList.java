package duke;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

import static java.util.stream.Collectors.toCollection;

public class TaskList implements Iterable<Task> {
    protected ArrayList<Task> taskArrayList;

    public TaskList() {
        this.taskArrayList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskArrayList) {
        this.taskArrayList = taskArrayList;
    }

    public int taskCount() {
        return this.taskArrayList.size();
    }

    public void addTask(Task task) {
        this.taskArrayList.add(task);
    }

    public Task getTask(int index) throws DukeException {
        int numTasks = this.taskArrayList.size();

        if (numTasks == 0) {
            throw new DukeException("You don't have tasks.");
        }
        if (index < 1) {
            throw new DukeException("Task number should be at least 1.");
        }
        if (index > numTasks) {
            throw new DukeException(String.format("You only have %d tasks.", numTasks));
        }

        // The user gives 1-indexed numbers.
        return this.taskArrayList.get(index - 1);
    }

    public Task markTask(int index) throws DukeException {
        Task task = getTask(index);
        task.markDone();
        return task;
    }

    public Task unmarkTask(int index) throws DukeException {
        Task task = getTask(index);
        task.unmarkDone();
        return task;
    }

    public Task deleteTask(int index) throws DukeException {
        Task task = getTask(index);
        this.taskArrayList.remove(index - 1);
        return task;
    }

    public ArrayList<Task> getTasksOn(LocalDate date) {
        ArrayList<Task> filteredTaskList = this.taskArrayList.stream().
                filter(task -> task.isOn(date)).
                collect(toCollection(ArrayList::new));
        return filteredTaskList;
    }

    /**
     * Returns a filtered task list where description contains the search query.
     * @param searchQuery String to search for.
     * @return ArrayList<Task> of tasks matching the search description.
     */
    public ArrayList<Task> searchTasks(String searchQuery) {
        ArrayList<Task> filteredTaskList = this.taskArrayList.stream().
                filter(task -> task.descriptionIncludes(searchQuery)).
                collect(toCollection(ArrayList::new));
        return filteredTaskList;
    }

    @Override
    public Iterator<Task> iterator() {
        return this.taskArrayList.iterator();
    }

}
