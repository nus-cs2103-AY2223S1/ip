import java.util.ArrayList;

/**
 * Object to represent a list of tasks created based on input and to print the list as output.
 *
 */
public class ToDoList {
    private Integer totalTasks = 0;
    private ArrayList<Task> complete = new ArrayList<>();
    private ArrayList<Task> incomplete = new ArrayList<>();
    private ArrayList<Task> allTaskList = new ArrayList<>();

    public Integer tasksLeft() {
        return incomplete.size();
    }

    public Integer tasksDone() {
        return complete.size();
    }

    public void addTask(String description) {
        totalTasks++;
        Task task = new Task(description);
        allTaskList.add(task);

        if (task.status()) {
            complete.add(task);
        } else {
            incomplete.add(task);
        }

        System.out.println("added: " + task.describe());
    }
    public void printList() {
        for(int i = 1; i <= totalTasks; i++) {
            System.out.println(i + ". " + allTaskList.get(i - 1) + "\n");
        }
    }
}
