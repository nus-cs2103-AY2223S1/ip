package neo;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to keep tasks in array list.
 */
public class TaskList {
    List<Task> arrayL;

    public TaskList() {
        arrayL = new ArrayList<Task>();
    }

    /**
     * Adds task to ArrayList.
     *
     * @param t task
     */
    public void addTask(Task t) {
        this.arrayL.add(t);
    }

    public int size() {
        return arrayL.size();
    }

    /**
     * Gets task from ArrayList.
     *
     * @param i Task number integer.
     * @return Task
     */
    public Task getTask(int i) {
        return arrayL.get(i);
    }

    /**
     * Removes task from ArrayList.
     *
     * @param in Task number integer.
     */
    public void delete(int in) {
        System.out.println("ok, I've deleted this task from array");
        System.out.println(arrayL.get(in).toString());
        arrayL.remove(in);
    }
}
