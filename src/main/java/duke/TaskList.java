package duke;

import duke.task.Task;
import java.util.ArrayList;

/**
 * TaskList contains the task list.
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Constructor for a new list.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Constructor for a list with data from a save file.
     *
     * @param taskList List of string from data in a save file.
     */
    public TaskList(ArrayList<String> taskList) {
        this.list = new ArrayList<>();
        for (String task : taskList) {
            this.list.add(Task.load(task));
        }
    }

    public int getSize() {
        return this.list.size();
    }

    public Task getTask(int num) {
        return this.list.get(num);
    }

    public void addTask(Task task) {
        this.list.add(task);
    }

    public Task removeTask(int num) {
        return this.list.remove(num);
    }

    /**
     * Converts the list of tasks into a list of strings.
     *
     * @return A list consisting of the tasks in the taskList turned into their string form.
     */
    public ArrayList<String> save() {
        ArrayList<String> taskList = new ArrayList<>();
        for (Task task : list) {
            taskList.add(task.save());
        }
        return taskList;
    }
}
