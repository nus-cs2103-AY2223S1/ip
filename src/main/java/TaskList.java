import java.lang.reflect.Array;
import java.util.ArrayList;

public class TaskList {
    //Message from Task
    private static final String LIST_MESSAGE = "Here are the tasks in your list:";
    private static final String MARKED_MESSAGE = "Nice! I've marked this task as done:";
    private static final String UNMARKED_MESSAGE = "OK, I've marked this task as not done yet:";

    private ArrayList<Task> tasks;

    /**
     * Constructor with task initialised as ArrayList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Add the new task to tasks.
     *
     * @param task task to be added to tasks
     */
    private void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Displays the newly added task to tasks.
     */
    private void addTaskMessage() {
        IOHelper.print("added: " + tasks.get(tasks.size()-1));
    }

    /**
     * Combine both addTask and addTaskMessage into one process
     *
     * @param text new task to be added
     */
    public void addTaskProcess(String text) {
        Task task = new Task(text);
        addTask(task);
        addTaskMessage();
    }

    /**
     * Displays all task stored in tasks.
     */
    public void printAllTasks() {
        IOHelper.print(LIST_MESSAGE);
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            IOHelper.print(String.format("%d.[%s] %s", i + 1, task.getStatusIcon(), task));
        }
    }

    /**
     * Update the task when command mark/unmark is given
     * @param index task to be updated
     */
    public void updateTask(int index, String command) {
        if (outOfRange(index)) {
            return;
        }
        Task task = tasks.get(index);
        if (command.equals("mark")) {
            task.mark();
            IOHelper.print(MARKED_MESSAGE);
        } else {
            task.unmark();
            IOHelper.print(UNMARKED_MESSAGE);
        }
        IOHelper.print(String.format("[%s] %s", task.getStatusIcon(), task));
    }

    /**
     * Check to make sure the index given by user is not out of range
     * @param index given by user in console
     * @return true if index is out of range else false
     */
    public boolean outOfRange(int index) {
        try {
            tasks.get(index);
            return false;
        } catch (IndexOutOfBoundsException e) {
            IOHelper.print("Index given out of range");
            return true;
        }
    }
}