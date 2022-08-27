package Duke;
import java.util.ArrayList;

/**
 * Class of a list of tasks
 */
public class TaskList {
    private final ArrayList<Task> list;

    /**
     * Constructor of taskList that initialised a new list
     */
    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    /**
     * Constructor of task list
     *
     * @param list of tasks
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * @return Arraylist of type task
     */
    public ArrayList<Task> listTasks() {
        return list;
    }

    /**
     * @param i index of task in ArrayList
     * @return Task stored
     */
    public Task get(int i) {
        return list.get(i);
    }

    /**
     * @return int of size of ArrayList
     */
    public int size() {
        return list.size();
    }

    /**
     * @param task to be added to ArrayList
     */
    public void add(Task task) {
        list.add(task);
    }

    /**
     * @param i index of task to be removed from ArrayList
     */
    public void remove(int i) {
        list.remove(i);
    }
}
