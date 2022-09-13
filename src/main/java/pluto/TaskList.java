package pluto;

import java.util.ArrayList;

import pluto.task.Task;

/**
 * List of tasks added by the user.
 */
public class TaskList {
    /** List of tasks */
    private ArrayList<Task> missions;

    /**
     * Constructor that initializes global variables.
     */
    public TaskList() {
        this.missions = new ArrayList<>();
    }

    /**
     * Constructor that initializes global variables.
     * @param missions List of existing tasks.
     */
    public TaskList(ArrayList<Task> missions) {
        this.missions = missions;
    }

    /**
     * Adds a task to the task list.
     * @param t Task to be added.
     */
    public void addTask(Task t) {
        missions.add(t);
    }

    /**
     * Adds a task to the task list at a specific index.
     * @param idx Index at which task should be added.
     * @param t Task to be added.
     * @throws PlutoException If invalid idx.
     */
    public void addTask(int idx, Task t) throws PlutoException {
        try {
            missions.add(idx, t);
        } catch (IndexOutOfBoundsException e) {
            throw new PlutoException("OOPS!!! Valid index required.");
        }
    }

    /**
     * Deletes a task from the task list.
     * @param idx Index of task to be deleted.
     * @return Task that is deleted.
     * @throws PlutoException If invalid idx.
     */
    public Task deleteTask(int idx) throws PlutoException {
        try {
            return missions.remove(idx);
        } catch (IndexOutOfBoundsException e) {
            throw new PlutoException("OOPS!!! Valid index required.");
        }
    }

    /**
     * Returns the task at an index.
     * @param idx Index of the task to return.
     * @return Task at the index idx.
     * @throws PlutoException If invalid idx.
     */
    public Task getTask(int idx) throws PlutoException {
        try {
            return missions.get(idx);
        } catch (IndexOutOfBoundsException e) {
            throw new PlutoException("OOPS!!! Valid index required.");
        }
    }

    /**
     * Change the status of a task.
     * @param idx Index of the task whose status is to be changed.
     * @param isDone Status of the task after changing.
     * @throws PlutoException If invalid idx.
     */
    public void markTask(int idx, boolean isDone) throws PlutoException {
        try {
            if (isDone) {
                missions.get(idx).markAsDone();
            } else {
                missions.get(idx).markAsUndone();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new PlutoException("OOPS!!! Valid index required.");
        }
    }

    /**
     * Returns the size of the task list.
     * @return Number of tasks in the task list.
     */
    public int nTasks() {
        return missions.size();
    }

    /**
     * Returns tasklist of tasks containing the keyword.
     * @param keyword Keyword to filter tasklist.
     * @return Tasklist of filtered tasks.
     */
    public TaskList filter(String keyword) {
        TaskList filtered = new TaskList();
        String[] keywords = keyword.split("\\s+");
        missions.stream().filter(t -> t.contains(keywords)).forEach(filtered::addTask);
        return filtered;
    }

    @Override
    public String toString() {
        StringBuilder printTasks = new StringBuilder();
        for (int i = 0; i < missions.size(); i++) {
            String output = String.format("\t%d. %s\n", i + 1, missions.get(i).toString());
            printTasks.append(output);
        }
        return printTasks.toString();
    }
}
