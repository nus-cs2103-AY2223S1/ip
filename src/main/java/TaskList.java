import java.util.ArrayList;

// operations to add/delete task in list
public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this(new ArrayList<>());
    }

    public void addTask(Task task) {
        tasks.add(task);
        System.out.printf("Got it. I've added this task:\n %s\n", task);
        showTasksCount();
    }

    public void deleteTask(int idx) {
        Task task = tasks.get(idx - 1);
        tasks.remove(task);
        System.out.printf("Noted. I've removed this task:\n %s\n", task);
        showTasksCount();
    }

    public void markAsDone(int idx) {
        tasks.get(idx - 1).markAsDone();
    }

    public void markAsNotDone(int idx) {
        tasks.get(idx - 1).markAsNotDone();
    }

    public void showTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, tasks.get(i));
        }
    }

    public void showTasksCount() {
        System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Task task : tasks) {
            // format is such that
            // <taskName> <isDone> <description> <time>
            str.append(task.getCsvString()).append("\n");
        }
        return str.toString();
    }
}
