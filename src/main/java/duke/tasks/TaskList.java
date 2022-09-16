package duke.tasks;

import duke.exceptions.TaskNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

public class TaskList {

    private List<Task> list;

    public TaskList(List<Task> initList) {
        list = initList;
    }

    /**
     * Returns the list of Tasks.
     * @return The list of Tasks.
     */
    public List<Task> getList() {
        return list;
    }

    /**
     * Returns the size of the list of Tasks.
     * @return The number of Tasks.
     */
    public int getSize() {
        return list.size();
    }

    /**
     * Adds a given Task to the list.
     * @param newTask The Task to be added.
     */
    public void addTask(Task newTask) {
        list.add(newTask);
    }

    /**
     * Deletes the Task at the given index.
     * @param index The index of the Task to be deleted.
     * @return The deleted Task.
     * @throws TaskNotFoundException
     */
    public Task deleteTask(int index) throws TaskNotFoundException {
        if (index < 0 || index >= list.size()) {
            throw new TaskNotFoundException();
        }
        return list.remove(index);
    }

    /**
     * Marks the Task at the given index.
     * @param index The index of the Task to be marked.
     * @return The marked Task.
     * @throws TaskNotFoundException if there is no task corresponding to the index.
     */
    public Task markTask(int index) throws TaskNotFoundException {
        if (index < 0 || index >= list.size()) {
            throw new TaskNotFoundException();
        }
        Task taskToMark = list.get(index);
        assert taskToMark != null;
        taskToMark.mark();
        return taskToMark;
    }

    /**
     * Unmarks the Task at the given index.
     * @param index The index of the Task to be unmarked.
     * @return The unmarked Task.
     * @throws TaskNotFoundException if there is no task corresponding to the index.
     */
    public Task unmarkTask(int index) throws TaskNotFoundException {
        if (index < 0 || index >= list.size()) {
            throw new TaskNotFoundException();
        }
        Task taskToUnmark = list.get(index);
        assert taskToUnmark != null;
        taskToUnmark.unmark();
        return taskToUnmark;
    }

    /**
     * Finds the list of Tasks whose description matches the given string.
     * @param s The search string.
     * @return The list of matching Tasks.
     */
    public List<Task> find(String s) {
        return list.stream()
                .filter(task -> task.getName().contains(s))
                .collect(Collectors.toList());
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
