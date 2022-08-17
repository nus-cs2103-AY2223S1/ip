import java.util.ArrayList;

public class TasksHandler {
    private ArrayList<Task> tasks;

    public TasksHandler() {
        tasks = new ArrayList<>();
    }

    /**
     * Add the new task to tasks.
     * if tasks is full, it returns nothing
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
        Console.log("added: " + tasks.get(tasks.size()-1));
    }

    public void addTaskProcess(Task task) {
        addTask(task);
        addTaskMessage();
    }

    /**
     * Displays all task stored in tasks.
     */
    public void printAllTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            Console.log(String.format("%d. %s", i + 1, tasks.get(i)));
        }
    }
}
