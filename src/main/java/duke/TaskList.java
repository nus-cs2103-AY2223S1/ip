package duke;
import java.time.LocalDateTime;
import java.util.ArrayList;
import duke.Task.Task;
import duke.Task.Deadline;
import duke.Task.Event;
public class TaskList {
    public ArrayList<Task> list;

    /**
     * A constructor to construct the taskList
     * @param list
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Adds a task to the list
     * @param task the task to be added
     */
    public void addTask(Task task) {
        this.list.add(task);
    }

    /**
     * Deletes the task at the specified index
     * @param index the index of the task to delete
     * @return the deleted task
     */
    public Task deleteTask(Integer index) {
        Task task = this.list.get(index);
        this.list.remove(index - 1);
        return task;
    }

    @Override
    public String toString() {
        String result = "";
        int length = this.list.size();
        for (int i = 0; i < length; i++) {
            Task curr = this.list.get(i);
            result +=String.format("%d. %s \n",i + 1, curr);
        }
        return result;
    }

    /**
     * gets the length of the list
     * @return the length of this list
     */
    public Integer length() {
        return this.list.size();
    }

    /**
     * Toggles the status of the task at a specific index
     * @param index the index of the task to toggle
     * @return the task that was specified
     */
    public Task toggleTaskStatus(Integer index) {
        Task task = this.list.get(index);
        task.toggleStatus();
        return task;
    }

    public TaskList filterTask(String input) {
        String comparator = "(.*)"+ input.replace(" ", "(.*)") + "(.*)";
        ArrayList<Task> copyOfTasks = new ArrayList<>(this.list);
        copyOfTasks.removeIf(task -> !task.toString().matches(comparator));
        return new TaskList(copyOfTasks);
    }
}
