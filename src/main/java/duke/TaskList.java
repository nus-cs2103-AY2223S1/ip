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

    public TaskList(List<Task> list) {
        this.list = list;
    }

    public List<Task> getList() {
        return list;
    }

    public Task getTask(int number) {
        return this.list.get(number - 1);
    }

    /**
     * Deletes a task from the list.
     *
     * @param index of the task to be deleted.
     */
    public void deleteTask(int index) {
        Task task = this.list.get(index - 1);
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
        String text = "";

        for (int i = 0; i < list.size(); i++) {

            if (i == list.size() - 1) {
                text += i + 1 + ". " + list.get(i);
            } else {
                text += i + 1 + ". " + list.get(i) +"\n     ";
            }
        }

        return text;
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

        String text = "";

        for (int i = 0; i < resultList.size(); i++) {
            if (i == resultList.size() - 1) {
                text += i + 1 + ". " + resultList.get(i);
            } else {
                text += i + 1 + ". " + resultList.get(i) +"\n     ";
            }
        }

        return text;
    }
}
