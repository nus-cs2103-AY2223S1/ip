import java.util.ArrayList;

/** Represents a list of tasks
 * @author Nam Minh Quan
 */
public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    /** List out all tasks in the list
     */
    public void listTasks() {
        for (Task task : tasks) {
            int index = tasks.indexOf(task) + 1;
            System.out.println(index + ". " + task.print());
        }
    }

    /** Adds a new task into the list
     * @param task the task to be added
     */
    public void addTask(String task) {
        Task newTask = new Task(task);
        tasks.add(newTask);
        System.out.println("added: " + task);
    }

    /**
     * Mark a task in the list as done
     * @param index
     */
    public void mark(int index) {
        Task temp = tasks.get(index-1);
        temp.mark();
        System.out.println("Nice! I've marked this task as done: \n" + temp.print());
    }
    public void unmark(int index) {
        Task temp = tasks.get(index-1);
        temp.unmark();
        System.out.println(" OK, I've marked this task as not done yet: \n" + temp.print());
    }
}
