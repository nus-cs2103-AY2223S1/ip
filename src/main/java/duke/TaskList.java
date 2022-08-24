package duke;
import java.util.ArrayList;

/**
 *  A class which encapsulates the task list and operations on the list.
 *  @author  Chen Guanzhou
 *  @version v1
 */
public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Removes the specified task from the task list.
     * @param remove The task to be removed.
     */
    public void removeTask(Task remove) {
        this.taskList.remove(remove);
    }

    /**
     * Adds a task to the end of the task list.
     * @param t The task to be added.
     */
    public void addTask(Task t) {
        this.taskList.add(t);
    }

    /**
     * A method to find the length of the current task list.
     * @return The length of the task list.
     */
    public int getLength() {
        return taskList.size();
    }

    /**
     * A method to get the task at a specified index.
     * @param index index we want to get our task
     * @return the task retrieved at the index
     */
    public Task getTaskAt(int index) {
        return taskList.get(index);
    }
}
