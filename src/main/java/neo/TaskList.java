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
        System.out.println("ok, I've deleted this take from array");
        System.out.println(arrayL.get(in-1).toString());
        arrayL.remove(in);
    }
}
