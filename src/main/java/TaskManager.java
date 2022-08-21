import java.io.FileWriter;
import java.util.ArrayList;

/**
 * Represents a Class that manages tasks.
 *
 * @author Khor Jun Wei
 * @version CS2103T AY22/23 Sem 1
 */
public class TaskManager {

    /**
     * Represents a file writer.
     */
    private final FileWriter fw;

    /**
     * Represents an array list of tasks.
     */
    private final ArrayList<Task> arr;

    /**
     * Represents an indentation for replies.
     */
    private static final String INDENTATION = "     ";

    /**
     * Constructor for Task Manager.
     * @param fw file writer
     */
    public TaskManager(FileWriter fw) {
        this.arr = new ArrayList<>(100);
        this.fw = fw;
    }

    /**
     * Crafts a list of tasks.
     *
     * @return String describing the list
     */
    public String craftList() {
        int length = arr.size();
        String result = "Here are the task(s) in your list:";
        for (int x = 0; x < length; x++) {
            Task task = arr.get(x);
            if (task == null) {
                break;
            } else {
                result += "\n" + INDENTATION + (x + 1) + "." + task;
            }
        }
        return result;
    }

    /**
     * Crafts a message to be added into the file
     *
     * @return String representing the message
     */
    public String craftTextMessage() {
        int length = arr.size();
        String result = "";
        for (int x = 0; x < length; x++) {
            Task task = arr.get(x);
            if (task == null) {
                break;
            } else {
                if (x == 0) {
                    result += task.textFileMessage();
                } else {
                    result += "\n" + task.textFileMessage();
                }
            }
        }
        return result;
    }

    /**
     * Adds task into array of tasks.
     * @param task given task
     */
    public void addTask(Task task) {
        arr.add(task);
    }

    /**
     * Removes a task.
     * @param location where the task is located
     * @return Task
     */
    public Task removeTask(int location) {
        Task task = arr.get(location);
        arr.remove(location);
        return task;
    }

    /**
     * Represents the number of task is the task list.
     * @return number of tasks
     */
    public int numOfTasks() {
        return arr.size();
    }

    /**
     * Marks a task as completed.
     * @param location where the task is located
     * @return Task
     */
    public Task markTaskComplete(int location) {
        Task task = arr.get(location);
        task.markComplete();
        return task;
    }

    /**
     * Marks a task as incomplete.
     * @param location where the task is located
     * @return Task
     */
    public Task markTaskIncomplete(int location) {
        Task task = arr.get(location);
        task.markIncomplete();
        return task;
    }
}
