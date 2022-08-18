import java.util.ArrayList;

/** Represents a list of tasks
 * @author Nam Minh Quan
 */
public class TaskList {
    private ArrayList<String> tasks = new ArrayList<>();

    /** List out all tasks in the list
     */
    public void listTasks() {
        for (String task : tasks) {
            int index = tasks.indexOf(task) + 1;
            System.out.println(index + ". " + task);
        }
    }

    /** Adds a new task into the list
     * @param task the task to be added
     */
    public void addTask(String task) {
        tasks.add(task);
        System.out.println("added: " + task);
    }
}
