import java.util.List;
import java.util.ArrayList;

public class TasksList {
    private final List<Task> tasksList;

    public TasksList() {
        this.tasksList = new ArrayList<>();
    }

    /**
     * Add to the list of tasks
     *
     * @param description The task to be added to the list
     */
    public void addToList(String description) {
        Task newTask = new Task(description);
        this.tasksList.add(newTask);
        System.out.println("added: " + description);
    }

    /**
     * Prints out the list of the history of tasks
     */
    public void listTasks() {
        System.out.println("Duke's Tasks:");
        if (tasksList.size() == 0) {
            System.out.println("*No tasks! ^_^*");
            return;
        }
        for (int i = 0; i < tasksList.size(); i++) {
            String line = String.format("%d. %s", i + 1, tasksList.get(i));
            System.out.println(line);
        }
    }

    public void markTask(int id) {
        int len = this.tasksList.size();
        if (id <= 0 || id > len) {
            System.out.println("Invalid number!");
        } else {
            this.tasksList.get(id - 1).mark();
        }
    }

    public void unmarkTask(int id) {
        int len = this.tasksList.size();
        if (id <= 0 || id > len) {
            System.out.println("Invalid number!");
        } else {
            this.tasksList.get(id - 1).unmark();
        }
    }
}
