import java.util.List;
import java.util.ArrayList;

/**
 * Stores a list of tasks entered by the user.
 */
public class TaskList {
    private final List<Task> tasks;

    TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Returns the number of tasks in this task list.
     *
     * @return The number of tasks in this task list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Adds the given task to the task list.
     *
     * @param task the task to be added.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Marks the task at the specific position as done.
     *
     * @param pos The position of the task in the list.
     * @return The marked task.
     */
    public Task mark(int pos) {
        Task task = this.tasks.get(pos);
        task.markDone();
        return task;
    }

    /**
     * Marks the task at the specific position as not done.
     *
     * @param pos The position of the task in the list.
     * @return The marked task.
     */
    public Task unmark(int pos) {
        Task task = this.tasks.get(pos);
        task.markUndone();
        return task;
    }

    /**
     * Removes the task at the specific position in the list.
     *
     * @param pos The position of the task in the list.
     * @return The removed task.
     */
    public Task remove(int pos) {
        Task task = this.tasks.remove(pos);
        return task;
    }

    /**
     * Returns an encoded String representation of this TaskList.
     *
     * @return An encoded String representation of this TaskList.
     */
    public List<String> encode() {
        List<String> encodedTasks = new ArrayList<>();
        for (Task task : tasks) {
            encodedTasks.add(task.encode());
        }
        return encodedTasks;
    }

    /**
     * Returns a decoded TaskList, to be used by Duke.
     *
     * @param encodedTasks the encoded text used to store the TaskList.
     * @return a TaskList based on the encoded text.
     */
    public static TaskList decode(List<String> encodedTasks) throws DwukeException {
        TaskList decodedTasks = new TaskList();
        for (String s : encodedTasks) {
            Character taskType = s.charAt(0);
            String content = s.substring(2);

            switch(taskType) {
                case Todo.SYMBOL:
                    decodedTasks.add(Todo.decode(content));
                    break;
                case Deadline.SYMBOL:
                    decodedTasks.add(Deadline.decode(content));
                    break;
                case Event.SYMBOL:
                    decodedTasks.add(Event.decode(content));
                    break;
                default:
                    break;
            }
        }
        return decodedTasks;
    }

    /**
     * Returns the String representation of all tasks in the list.
     *
     * @return A String representing all tasks in the list.
     */
    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < this.tasks.size(); i++) {
            Task task = this.tasks.get(i);
            int taskNum = i + 1;
            result += taskNum + "." + task;

            if (i != this.tasks.size() - 1) {
                result += "\n";
            }
        }
        return result;
    }
}
