package duke.task;

import java.util.ArrayList;

/**
 * Represents a list of taskList in the application.
 */
public class TaskList {

    private final ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public int size() {
        return taskList.size();
    }

    public Task get(int i) {
        return taskList.get(i);
    }

    public void delete(int index) {
        taskList.remove(index);
    }

    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Returns a list of all taskList that contains the keyword
     *
     * @param keyword word that user wants to find
     * @return arraylist of taskList that contains keyword.
     */
    public TaskList findKeyWord(String keyword) {
        TaskList findList = new TaskList();
        for (Task task : taskList) {
            if (task.contains(keyword)) {
                findList.add(task);
            }
        }
        return findList;
    }

    /**
     * Returns String representation of task list in saved file format.
     *
     * @return String representation of task list in saved file format.
     */
    public String toSaveString() {
        StringBuilder output = new StringBuilder();
        for (Task task : taskList) {
            output.append(task.toSaveString()).append("\n");
        }
        return output.toString();
    }

    /**
     * Returns String representation of task list.
     *
     * @return String representation of task list.
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            output.append(i + 1).append(".");
            output.append(taskList.get(i).toString());
            output.append("\n");
        }
        return output.toString();
    }
}
