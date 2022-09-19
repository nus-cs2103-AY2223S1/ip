package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains the task list.
 */
public class TaskList {

    /** List of tasks */
    private List<Task> list = new ArrayList<>();

    /**
     * Constructs a new task list.
     *
     * @param list of tasks.
     */
    public TaskList(List<Task> list) {
        this.list = list;
    }

    public Task getTask(int number) {
        int taskIndex = number - 1;
        assert taskIndex >= 0 : "task index should not be negative";
        return this.list.get(taskIndex);
    }

    /**
     * Deletes a task from the list.
     *
     * @param index of the task to be deleted.
     */
    public void deleteTask(int index) {
        int taskIndex = index - 1;
        assert taskIndex >= 0 : "task index should not be negative";
        Task task = this.list.get(taskIndex);
        this.list.remove(task);
    }

    /**
     * Adds a task to the list.
     *
     * @param task to be added to the list.
     */
    public void addTask(Task task) {
        this.list.add(task);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return total number of tasks.
     */
    public int getListSize() {
        return this.list.size();
    }

    /**
     * Returns a paragraph of numbered tasks in strings.
     *
     * @return numbered tasks in strings.
     */
    public String getAllTask() {
        return convertListToString(this.list);
    }

    /**
     * Filters tasks according to the keyword.
     *
     * @param keyword used to filter the list.
     * @return a paragraph of filtered numbered tasks in strings.
     */
    public String getTaskStringFiltered(String keyword) {
        List<Task> resultList = new ArrayList<>();
        for (Task task : this.list) {
            if (task.getName().contains(keyword)) {
                resultList.add(task);
            }
        }
        return convertListToString(resultList);
    }

    private String convertListToString(List<Task> resultList) {
        String text = "";
        for (int i = 0; i < resultList.size(); i++) {
            if (i == resultList.size() - 1) {
                text += i + 1 + ". " + resultList.get(i);
            } else {
                text += i + 1 + ". " + resultList.get(i) + "\n     ";
            }
        }

        return text;
    }
}