package duke;

import java.util.ArrayList;

/**
 * List to keep tasks.
 */
class TaskList {

    private ArrayList<Task> listOfTasks = new ArrayList<>();

    /**
     * Adds a task to the list.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        listOfTasks.add(task);
    }

    /**
     * Returns size of the list.
     *
     * @return Size of the list.
     */
    public int getSize() {
        return listOfTasks.size();
    }

    /**
     * Deletes a task at given position.
     *
     * @param i position of the task to be deleted.
     * @throws DukeException when the task position is non-existent.
     */
    public Task deleteTask(int i) throws DukeException {
        if (listOfTasks.size() <= i) {
            throw DukeException.IDTOOBIG;
        }
        Task task = listOfTasks.get(i);
        listOfTasks.remove(i);
        return task;
    }

    /**
     * Get the task at given position
     *
     * @param i position of the task.
     * @return the task at position.
     * @throws DukeException when the task position is non-existent.
     */
    public Task getTask(int i) throws DukeException {
        if (listOfTasks.size() <= i) {
            throw DukeException.IDTOOBIG;
        }

        return listOfTasks.get(i);
    }

    public TaskList searchByKeyword(String ...keys) {
        TaskList result = new TaskList();
        for (Task task : listOfTasks) {
            for (String key : keys) {
                if (task.hasKeyword(key)) {
                    result.addTask(task);
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < getSize(); i++) {
            res += (i + 1) + ". " + listOfTasks.get(i);
            if (i != getSize() - 1) {
                res += "\n";
            }
        }
        return res;
    }
}
