package duke;

import java.util.ArrayList;

/**
 * The class that represents the list of tasks inputted by the user.
 */
public class TaskList {
    private final ArrayList<Task> taskList;

    /**
     * The constructor of the TaskList class.
     * @param taskList A list of tasks.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Gets the size of the task list.
     * @return Size of task list.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Gets the task from the task list with the given index.
     * @param index Index of the task wanted.
     * @return Task with the index.
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Add a task into the task list.
     * @param task The task that is being added.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes a task from the task list.
     * @param num The number of the task that is being deleted.
     * @return The task that was deleted.
     * @throws DukeException If num > taskList.size() || num <= 0
     */
    public Task deleteTask(int num) throws DukeException {
        if (num > taskList.size() || num <= 0) {
            throw new DukeException("");
        }
        return taskList.remove(num - 1);
    }

    /**
     * Marks the task as done.
     * @param number The number of the task that is being marked.
     * @throws DukeException If number > taskList.size() || number <= 0
     */
    public void mark(int number) throws DukeException {
        if (number > taskList.size() || number <= 0) {
            throw new DukeException("");
        }
        taskList.get(number - 1).setDone();
    }

    /**
     * Marks the task as not done.
     * @param number The number of the task that is being unmarked.
     * @throws DukeException If number > taskList.size() || number <= 0
     */
    public void unMark(int number) throws DukeException {
        if (number > taskList.size() || number <= 0) {
            throw new DukeException("");
        }
        taskList.get(number - 1).setUnDone();
    }

    /**
     * Converts the taskList to a string that can be inputted into the text file.
     * @return String of tasks
     */
    public String taskListToText() {
        StringBuilder lines = new StringBuilder();
        for (Task task : taskList) {
            lines.append(task.toLine()).append("\n");
        }
        return lines.toString();
    }

    /**
     * Returns a list of tasks that contains the keyword.
     * @param keyWord Keyword that is being searched for.
     * @return A taskList containing tasks that contains the keyword.
     * @throws DukeException If the new taskList is empty.
     */
    public TaskList find(String keyWord) throws DukeException {
        ArrayList<Task> containingKeyWordList = new ArrayList<>();
        for (Task t : taskList) {
            if (t.contains(keyWord)) {
                containingKeyWordList.add(t);
            }
        }
        if (containingKeyWordList.isEmpty()) {
            throw new DukeException("");
        }
        return new TaskList(containingKeyWordList);
    }
}
