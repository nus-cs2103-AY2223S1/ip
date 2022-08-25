import java.util.ArrayList;

/** Represents a list of tasks
 * @author Nam Minh Quan
 */
public class TaskList {
    private ArrayList<Task> tasks;
    protected int length;

    TaskList(ArrayList<Task> taskList) {
        this.tasks = taskList;
    }
    /** List out all tasks in the list
     */
    public void listTasks() {
        System.out.println("Here are the tasks in your list:\n");
        for (Task task : tasks) {
            int index = tasks.indexOf(task) + 1;
            System.out.println(index + ". " + task.toString());
        }
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public int getLength() {
        return this.tasks.size();
    }

    /** Adds a new task into the list
     * @param task the task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Mark a task in the list as done
     * @param index index of the task to be marked as done
     */
    public Task mark(int index) {
        Task temp = tasks.get(index-1);
        temp.mark();
        return temp;
    }
    /**
     * Mark a task in the list as not done
     * @param index index of the task to be unmarked
     */
    public Task unmark(int index) {
        Task temp = tasks.get(index-1);
        temp.unmark();
        return temp;
    }

    /**
     * Delete a task from the task list
     * @param index index of the task to be deleted
     */
    public Task delete(int index) {
        Task temp = tasks.get(index-1);
        tasks.remove(index-1);
        return temp;
    }
}
