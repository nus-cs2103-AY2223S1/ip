package duke;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import duke.models.Task;



/**
 * Contains the task list and has operations to add/delete tasks in the list
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Initializes an empty duke.TaskList
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Initializes a taskList that contains existing tasks that have been saved
     * @param taskList List of existing tasks to be loaded
     */
    public TaskList(List<Task> taskList) {
        this.tasks = taskList;
    }

    public void addTask(Task t) {
        tasks.add(t);
    }

    public void deleteTask(int index) {
        this.tasks.remove(index - 1);
    }

    public Task getTask(int index) {
        return this.tasks.get(index - 1);
    }

    public String getAllTasks() {
        String result = "";
        ListIterator<Task> listIterator = tasks.listIterator();
        while (listIterator.hasNext()) {
            Task t = listIterator.next();
            result += Constants.INDENT + listIterator.nextIndex()
                    + ". " + t + "\n";
        }
        return result;
    }

    /**
     * Searches the list if the description matches the query string
     * @param query query string
     * @return
     */
    public List<Task> findTask(String query) {
        List<Task> result = new ArrayList<>();
        Iterator<Task> iterator = this.tasks.iterator();
        while (iterator.hasNext()) {
            Task t = iterator.next();
            System.out.println(t);
            if (t.getDescription().contains(query)) {
                result.add(t);
            }
        }
        return result;
    }

    public int getSize() {
        return tasks.size();
    }

}
