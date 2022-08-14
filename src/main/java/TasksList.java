import java.util.List;
import java.util.ArrayList;

public class TasksList {
    private final List<String> tasksList;

    public TasksList() {
        this.tasksList = new ArrayList<>();
    }

    /**
     * Add to the list of tasks
     *
     * @param task The task to be added to the list
     */
    public void addToList(String task) {
        this.tasksList.add(task);
        System.out.println("added: " + task);
    }

    /**
     * Prints out the list of the history of tasks
     */
    public void listTasks() {
        System.out.println("Duke's Tasks:");
        for (int i = 0; i < tasksList.size(); i++) {
            String line = String.format("%d. %s", i + 1, tasksList.get(i));
            System.out.println(line);
        }
    }

}
