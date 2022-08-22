import java.util.ArrayList;

// operations to add/delete task in list
public class TaskList {
    private static final ArrayList<Task> TASKS = new ArrayList<>();

    public void addTask(Task task) {
        TASKS.add(task);
        System.out.printf("Got it. I've added this task:\n %s\n", task);
        showTasksCount();
    }

    public void deleteTask(int idx) {
        Task task = TASKS.get(idx - 1);
        TASKS.remove(task);
        System.out.printf("Noted. I've removed this task:\n %s\n", task);
        showTasksCount();
    }

    public void markAsDone(int idx) {
        TASKS.get(idx - 1).markAsDone();
    }

    public void markAsNotDone(int idx) {
        TASKS.get(idx - 1).markAsNotDone();
    }

    public void showTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < TASKS.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, TASKS.get(i));
        }
    }

    public void showTasksCount() {
        System.out.printf("Now you have %d tasks in the list.\n", TASKS.size());
    }

}
