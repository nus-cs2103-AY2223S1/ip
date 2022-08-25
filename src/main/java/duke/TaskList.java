package duke;

import duke.models.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;


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
        this.tasks.remove(index);
    }

    public String getAllTasks() {
        String result = "";
        ListIterator<Task> listIterator = tasks.listIterator();
        while (listIterator.hasNext()) {
            Task t = listIterator.next();
            result += Constants.indent + listIterator.nextIndex() +
                    ". " + t + "\n";
        }
        return result;
    }

    public int getSize() {
        return tasks.size();
    }

}
