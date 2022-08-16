import java.util.ArrayList;

/**
 * Class to represent the task lists.
 */
public class TaskList {
    private ArrayList<String> task;

    /**
     * The constructor for no input
     */
    public TaskList() {
        this.task = new ArrayList<String>();
    }

    /**
     * The method of add task
     * @param t
     */
    public void add(String t) {
        task.add(t);
    }

    /**
     * The method of size
     * @return
     */
    public int size() {
        return task.size();
    }

    /**
     * The method of get
     * @param i
     * @return Task object
     */
    public String get(int i) {
        return task.get(i);
    }

    /**
     * The method of getTask
     * @return Arraylist
     */
    public ArrayList<String> getTasks() {
        return this.task;
    }
}
